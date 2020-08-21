package com.memo.code12.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.memo.core.tool.ext.dp2px
import com.memo.core.tool.ext.dp2pxf

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-20 16:13
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class XFermodeView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
	private val radius = 50.dp2pxf()
	private val size = 150.dp2px()

	private val circleBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
	private val squareBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

	private lateinit var bounds: RectF

	init {
		val canvas = Canvas(circleBitmap)
		paint.color = Color.RED
		canvas.drawCircle(100.dp2pxf(), 50.dp2pxf(), radius, paint)
		canvas.setBitmap(squareBitmap)
		paint.color = Color.BLUE
		canvas.drawRect(0f, 50.dp2pxf(), 100.dp2pxf(), 150.dp2pxf(), paint)
	}

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		bounds = RectF(
			(width - 150.dp2pxf()) / 2, (height - 150.dp2pxf()) / 2,
			(width + 150.dp2pxf()) / 2, (height + 150.dp2pxf()) / 2
		)
	}

	override fun onDraw(canvas: Canvas) {
		val count = canvas.saveLayer(bounds, paint)
		canvas.drawBitmap(circleBitmap, (width - 150.dp2pxf()) / 2, (height - 150.dp2pxf()) / 2, paint)
		paint.xfermode = xfermode
		canvas.drawBitmap(squareBitmap, (width - 150.dp2pxf()) / 2, (height - 150.dp2pxf()) / 2, paint)
		paint.xfermode = null
		canvas.restoreToCount(count)
	}
}