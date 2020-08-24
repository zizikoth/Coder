package com.memo.code11.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.memo.core.tool.ext.dp2pxf
import java.lang.Math.sin
import kotlin.math.cos

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-18 17:59
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class PieView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

	// 扇形图百分比 4个
	var percents = arrayListOf<Float>()
		set(value) {
			var total = 0f
			value.forEach {
				total += it
			}
			if (value.size == 4 && total == 1f) {
				field = value
				invalidate()
			}
		}


	// 4种颜色
	private val percentColors = listOf(
		Color.BLACK, Color.GREEN, Color.BLUE, Color.RED
	)

	private var startAngle = -90f

	private val pieRadius = 150.dp2pxf

	private lateinit var rectF: RectF

	private val moveLength = 20.dp2pxf
	private var movePercent = 0f
		set(value) {
			field = value
			invalidate()
		}
	private val moveAnimator = ObjectAnimator.ofFloat(this, "movePercent", 0f, 1f)
		.setDuration(800)

	var selectPosition = -1
		set(value) {
			if (field != value && value >= 0 && value < percents.size) {
				field = value
				moveAnimator.start()
			}
		}

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		startAngle = -90f
		rectF = RectF(width / 2f - pieRadius, height / 2f - pieRadius, width / 2f + pieRadius, height / 2f + pieRadius)
	}

	override fun onDraw(canvas: Canvas) {
		if (percents.size != 4) return

		percents.forEachIndexed { index, percent ->
			val sweepAngle = percent * 360
			paint.color = percentColors[index]
			if (index == selectPosition) {
				val x = moveLength * movePercent * cos(Math.toRadians(startAngle + sweepAngle / 2.0)).toFloat()
				val y = moveLength * movePercent * sin(Math.toRadians(startAngle + sweepAngle / 2.0)).toFloat()
				canvas.withTranslation(x, y) {
					canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
				}
			} else {
				canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
			}
			startAngle += sweepAngle
		}
	}
}