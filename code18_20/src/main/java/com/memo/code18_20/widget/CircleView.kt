package com.memo.code18_20.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.memo.code18_20.R
import com.memo.core.tool.ext.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-03 16:08
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		color = Color.BLUE
	}
	private val radius = dimen(R.dimen.dp100)
	private val padding = dimen(R.dimen.dp20)

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		val size = (padding + radius).toInt() * 2
		setMeasuredDimension(size, size)
	}

	override fun onDraw(canvas: Canvas) {
		canvas.drawCircle(padding + radius, padding + radius, radius, paint)
	}
}