package com.memo.code17.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.memo.code17.R
import com.memo.core.tool.ext.color
import org.jetbrains.anko.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-31 13:37
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MaterialEditText constructor(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

	private val TEXT_PADDING_VERTICAL = dimen(R.dimen.dp10)
	private val TEXT_PADDING_HORIZONTAL = dimen(R.dimen.dp5)

	/*** 提示文字 ***/
	private val HINT_TEXT_SIZE = dimen(R.dimen.dp12)
	private val hintPaint = Paint(Paint.ANTI_ALIAS_FLAG)
	private var hintLabelShown = false
	var useHintLabel = true
		set(value) {
			if (field != value) {
				field = value
				setPadding(
					paddingLeft,
					if (value) paddingTop + HINT_TEXT_SIZE + TEXT_PADDING_VERTICAL else paddingTop - HINT_TEXT_SIZE - TEXT_PADDING_VERTICAL,
					paddingRight,
					paddingBottom
				)
				invalidate()
			}
		}
	private val hintAnimator = ObjectAnimator.ofFloat(this, "hintDegree", 0f, 1f)
	private var hintDegree = 0f
		set(value) {
			field = value
			invalidate()
		}

	/*** 底部横线 ***/
	private val LINE_SIZE = dimen(R.dimen.dp1)
	private val LINE_COLOR = color(R.color.black)
	private val LINE_TEXT_PADDING = dimen(R.dimen.dp3)
	private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)

	/*** 底部提示 ***/
	var tipMessage = "Tips"
		set(value) {
			field = value
			invalidate()
		}
	private val TIP_TEXT_SIZE = dimen(R.dimen.dp10)
	private val tipPaint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val TIP_TEXT_COLOR = color(R.color.red)
	private val tipMetrics = Paint.FontMetrics()

	/*** 文字长度 ***/
	private var maxLength = Int.MAX_VALUE
	private var currentLength = 0
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}
	private val LENGTH_TEXT_SIZE = dimen(R.dimen.dp10)
	private val lengthPaint = Paint(Paint.ANTI_ALIAS_FLAG)

	init {
		val params = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
		maxLength = params.getInt(R.styleable.MaterialEditText_met_maxLength, Int.MAX_VALUE)
		useHintLabel = params.getBoolean(R.styleable.MaterialEditText_met_useTipLabel, useHintLabel)
		tipMessage = params.getString(R.styleable.MaterialEditText_met_tip) ?: tipMessage
		params.recycle()

		filters = arrayOf<InputFilter>(object : InputFilter.LengthFilter(maxLength) {})
		background = null
		setPadding(
			paddingLeft,
			if (useHintLabel) paddingTop + HINT_TEXT_SIZE + TEXT_PADDING_VERTICAL else paddingTop,
			paddingRight,
			paddingBottom + TIP_TEXT_SIZE + TEXT_PADDING_VERTICAL * 2
		)
		hintPaint.textSize = HINT_TEXT_SIZE.toFloat()
		linePaint.strokeWidth = LINE_SIZE.toFloat()
		linePaint.strokeCap = Paint.Cap.ROUND
		linePaint.color = LINE_COLOR
		tipPaint.textSize = TIP_TEXT_SIZE.toFloat()
		tipPaint.color = TIP_TEXT_COLOR
		lengthPaint.textSize = LENGTH_TEXT_SIZE.toFloat()
		lengthPaint.textAlign = Paint.Align.RIGHT
	}

	override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter)
		if (!hintLabelShown && !text.isNullOrEmpty()) {
			hintLabelShown = true
			hintAnimator.start()
		} else if (hintLabelShown && text.isNullOrEmpty()) {
			hintLabelShown = false
			hintAnimator.reverse()
		}
		currentLength = text?.length ?: 0
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		// HintLabel
		if (useHintLabel) {
			hintPaint.alpha = (hintDegree * 0xff).toInt()
			val hintBaseLine = (2 - hintDegree) * (HINT_TEXT_SIZE + TEXT_PADDING_VERTICAL)
			canvas.drawText(hint.toString(), TEXT_PADDING_HORIZONTAL.toFloat(), hintBaseLine, hintPaint)
		}

		// 底部横线
		canvas.drawLine(
			left + TEXT_PADDING_HORIZONTAL.toFloat(),
			(bottom - TEXT_PADDING_VERTICAL * 2 - TIP_TEXT_SIZE - LINE_TEXT_PADDING).toFloat(),
			right - TEXT_PADDING_HORIZONTAL.toFloat(),
			(bottom - TEXT_PADDING_VERTICAL * 2 - TIP_TEXT_SIZE - LINE_TEXT_PADDING).toFloat(),
			linePaint
		)

		// 错误提示
		paint.getFontMetrics(tipMetrics)
		canvas.drawText(
			tipMessage,
			TEXT_PADDING_HORIZONTAL.toFloat(),
			bottom - TEXT_PADDING_VERTICAL - tipMetrics.bottom,
			tipPaint
		)

		// 文字长度
		val lengthTip = "$currentLength/${if (maxLength == Int.MAX_VALUE) "∞" else maxLength}"
		canvas.drawText(
			lengthTip,
			right - TEXT_PADDING_HORIZONTAL.toFloat(),
			bottom - TEXT_PADDING_VERTICAL - tipMetrics.bottom,
			lengthPaint
		)
	}

}