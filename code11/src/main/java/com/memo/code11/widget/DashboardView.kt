package com.memo.code11.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.annotation.IntRange
import com.memo.core.tool.ext.dp2pxf
import kotlin.math.cos
import kotlin.math.sin

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-18 14:54
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DashboardView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	// 开始角度
	private val startAngle = 150f

	// 持续角度
	private val sweepAngle = 240f

	// 刻度宽度
	private val dashWidth = 2.dp2pxf

	// 刻度高度
	private val dashHeight = 10.dp2pxf

	// 刻度数量
	private val dashSize = 20

	// 仪表盘半径
	private val dashboardRadius = 150.dp2pxf

	// 中心圆点半径
	private val centerPointRadius = 8.dp2pxf

	// 指针长度
	private val pointerLength = 100.dp2pxf

	private lateinit var pathMeasure: PathMeasure
	private lateinit var path: Path
	private lateinit var rectF: RectF
	private lateinit var dash: Path
	private lateinit var dashPathEffect: PathDashPathEffect

	// 指针动画
	private val animator =
		ObjectAnimator.ofFloat(this, "speedPercent", 0f, 1f)
			.apply {
				interpolator = OvershootInterpolator()
				duration = 1000
			}

	// 指针动画百分比
	private var speedPercent = 0f
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	// 速度设置
	@IntRange(from = 0, to = 20)
	var speed: Int = 0
		set(value) {
			if (field != value) {
				field = value
				animator.start()
			}
		}

	// 仪表盘画笔
	private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		strokeWidth = 5.dp2pxf
		style = Paint.Style.STROKE
	}

	// 指针画笔
	private val pointerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		strokeWidth = centerPointRadius * 2 / 3
		strokeCap = Paint.Cap.ROUND
		style = Paint.Style.FILL
		color = Color.RED
	}

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		// 圆弧
		path = Path()
		rectF = RectF(
			width / 2f - dashboardRadius, height / 2f - dashboardRadius,
			width / 2f + dashboardRadius, height / 2f + dashboardRadius
		)
		path.addArc(rectF, startAngle, sweepAngle)

		// 计算虚线间隔
		pathMeasure = PathMeasure(path, false)
		val advance = (pathMeasure.length - dashWidth) / dashSize

		// 虚线
		dash = Path()
		dash.addRect(RectF(0f, 0f, dashWidth, dashHeight), Path.Direction.CW)
		dashPathEffect = PathDashPathEffect(dash, advance, 0f, PathDashPathEffect.Style.ROTATE)
	}

	override fun onDraw(canvas: Canvas) {
		// 画外弧
		paint.pathEffect = null
		canvas.drawPath(path, paint)
		// 画刻度
		paint.pathEffect = dashPathEffect
		canvas.drawPath(path, paint)
		// 画圆心小红点
		canvas.drawCircle(width / 2f, height / 2f, centerPointRadius, pointerPaint)
		// 画指针
		val markToRadians = Math.toRadians(speed * (sweepAngle / dashSize).toDouble() * speedPercent + startAngle)
		canvas.drawLine(
			width / 2f,
			height / 2f,
			(width / 2f + cos(markToRadians) * pointerLength).toFloat(),
			(height / 2f + sin(markToRadians) * pointerLength).toFloat(),
			pointerPaint
		)
	}


}