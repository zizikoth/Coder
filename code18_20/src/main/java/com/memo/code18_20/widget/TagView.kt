package com.memo.code18_20.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import com.memo.code18_20.R
import com.memo.core.tool.ext.dimen
import kotlin.random.Random

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-03 16:27
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TagView constructor(context: Context) : View(context) {

	init {
		layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
	}

	private val padding = dimen(R.dimen.dp15)
	private val round = dimen(R.dimen.dp5)

	private var no = "编号："
	private var color = "#"

	private val bounds = Rect()

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		textSize = dimen(R.dimen.sp15)
		textAlign = Paint.Align.CENTER
	}

	init {
		val length = Random.nextInt(1, 10)
		(0..length).forEach { _ ->
			no += Random.nextInt(0, 10).toString()
		}
		(0..3).forEach { _ ->
			color += Random.nextInt(10, 99).toString()
		}
	}

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec)
		paint.getTextBounds(no, 0, no.length, bounds)
		var width = bounds.width() + padding.toInt() * 2
		var height = bounds.height() + padding.toInt() * 2
		width = resolveSize(width, widthMeasureSpec)
		height = resolveSize(height, heightMeasureSpec)
		setMeasuredDimension(width, height)
	}

	override fun onDraw(canvas: Canvas) {
		paint.color = Color.parseColor(color)
		canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), round, round, paint)
		paint.color = Color.WHITE
		canvas.drawText(no, width / 2f, (height - bounds.top - bounds.bottom) / 2f, paint)
	}

}