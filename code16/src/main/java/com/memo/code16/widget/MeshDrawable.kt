package com.memo.code16.widget

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import com.memo.code16.R
import com.memo.core.tool.ext.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-31 10:04
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MeshDrawable : Drawable() {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val blankSize = dimen(R.dimen.dp50)

	override fun draw(canvas: Canvas) {
		val left = bounds.left.toFloat()
		val top = bounds.top.toFloat()
		val right = bounds.right.toFloat()
		val bottom = bounds.bottom.toFloat()
		var x = left
		while (x <= right) {
			canvas.drawLine(x, top, x, bottom, paint)
			x += blankSize

		}
		var y = top
		while (y <= bottom) {
			canvas.drawLine(left, y, right, y, paint)
			y += blankSize
		}
	}


	override fun setAlpha(alpha: Int) {
		paint.alpha = alpha
	}

	override fun getAlpha(): Int = paint.alpha

	override fun setColorFilter(colorFilter: ColorFilter?) {
		paint.colorFilter = colorFilter
	}

	override fun getColorFilter(): ColorFilter? = paint.colorFilter

	/**
	 * Return the opacity/transparency of this Drawable.  The returned value is
	 * one of the abstract format constants in
	 * [android.graphics.PixelFormat.UNKNOWN],
	 * [android.graphics.PixelFormat.TRANSLUCENT],
	 * [android.graphics.PixelFormat.TRANSPARENT],
	 * [android.graphics.PixelFormat.OPAQUE].
	 * @see android.graphics.PixelFormat
	 */
	override fun getOpacity(): Int {
		return when (paint.alpha) {
			0 -> PixelFormat.TRANSPARENT
			0xff -> PixelFormat.OPAQUE
			else -> PixelFormat.TRANSLUCENT
		}
	}
}