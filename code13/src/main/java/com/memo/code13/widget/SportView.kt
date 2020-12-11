package com.memo.code13.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.memo.code13.R
import com.memo.core.tool.ext.dp2pxf
import com.memo.core.tool.ext.sp2pxf

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-24 10:29
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SportView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

	private val roundWidth = 15.dp2pxf
	private val radius = 100.dp2pxf
	private val description = "abab"
	private val textBounds: Rect = Rect()
	private val textMetrics = Paint.FontMetrics()
	private lateinit var bounds: RectF

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		paint.run {
			style = Paint.Style.STROKE
			strokeWidth = roundWidth
			strokeCap = Paint.Cap.ROUND
		}
		linePaint.run {
			strokeWidth = 1f
			color = Color.BLACK
		}
		textPaint.run {
			textSize = 20.sp2pxf
			typeface = ResourcesCompat.getFont(context, R.font.zhenyan)
			color = Color.BLACK
			textAlign = Paint.Align.CENTER
		}
		bounds = RectF(width / 2f - radius, height / 2f - radius, width / 2f + radius, height / 2f + radius)
	}

	override fun onDraw(canvas: Canvas) {
		// 画底环
		paint.color = Color.LTGRAY
		canvas.drawArc(bounds, -90f, 360f, false, paint)
		// 画外环
		paint.color = Color.BLUE
		canvas.drawArc(bounds, -90f, 180f, false, paint)
		// 画字体
		// 这个高度如果字母变化可以会导致字符上下浮动 b->p
		textPaint.textAlign = Paint.Align.CENTER
		textPaint.getTextBounds(description, 0, description.length, textBounds)
		canvas.drawText(description, width / 2f, height / 2f - (textBounds.top + textBounds.bottom) / 2f, textPaint)
		// 这个高度在不同英文字母中偏移固定不会出现上下浮动问题
		textPaint.color = Color.RED
		textPaint.getFontMetrics(textMetrics)
		canvas.drawText(description, width / 2f, height / 2f - (textMetrics.ascent + textMetrics.descent) / 2f, textPaint)

		textPaint.textAlign = Paint.Align.LEFT
		// 左上
		canvas.drawText(description, 0f, -textMetrics.top, textPaint)
		// 左下
		canvas.drawText(description, 0f, height - textMetrics.bottom, textPaint)

		textPaint.textAlign = Paint.Align.RIGHT
		// 右上
		canvas.drawText(description, width.toFloat(), -textMetrics.top, textPaint)
		// 右下
		canvas.drawText(description, width.toFloat(), height - textMetrics.bottom, textPaint)

		// 画横纵线
		canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), linePaint)
		canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, linePaint)
	}


}
