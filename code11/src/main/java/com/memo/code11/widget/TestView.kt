package com.memo.code11.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.memo.code11.R
import com.memo.core.tool.ext.dimen

/**
 * title:测试View
 * describe:
 *
 * @author memo
 * @date 2020-08-14 11:01
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TestView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val radius = dimen(R.dimen.dp100)

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

	private val path = Path()
	lateinit var pathMeasure: PathMeasure

	/**
	 * 由于View绘制多次 在改变的时候进行初始化
	 */
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		paint.apply {
			// 画笔颜色
			color = Color.BLACK
		}
		path.apply {
			reset()
			// 画一个圆 最后一个参数表示是顺时针（CW）还是逆时针（CCW）画
			addCircle(width / 2f, height / 2f, radius, Path.Direction.CCW)
			addCircle(width / 2f, height / 2f, radius * 1.5f, Path.Direction.CCW)
			// 画一个方
			addRect(width / 2f - radius, height / 2f, width / 2f + radius, height / 2f + 2 * radius, Path.Direction.CCW)
			// PathMeasure 路径测量 forceClosed 是否自动闭合
			pathMeasure = PathMeasure(path, false)


			// 多个图形重合 重合奇数次不填充 偶数次填充
			fillType = Path.FillType.EVEN_ODD
		}
	}

	override fun onDraw(canvas: Canvas) {
		// 画横线
		canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, paint)
		// 画竖线
		canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), paint)
		// 画一个圆
		canvas.drawPath(path, paint)
	}
}