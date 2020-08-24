package com.memo.code13.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.memo.code13.R
import com.memo.core.tool.ext.dp2px
import com.memo.core.tool.ext.dp2pxf
import com.memo.core.tool.ext.sp2pxf
import com.memo.core.utils.ImageUtils
import com.memo.core.widget.BaseView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-24 14:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MultiTextView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseView(context, attrs, defStyleAttr) {

	private val source =
		"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."

	private val bitmap = ImageUtils.getBitmap(R.drawable.cake, 150.dp2px)

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

	private val fontMetrics = Paint.FontMetrics()

	private val imagePadding = 50.dp2pxf

	override fun initialize() {
		paint.textSize = 15.sp2pxf
	}

	@SuppressLint("NewApi")
	override fun startDraw(canvas: Canvas) {
		canvas.drawBitmap(bitmap, (width - bitmap.width).toFloat(), imagePadding, paint)

		paint.getFontMetrics(fontMetrics)
		val measuredWidth = floatArrayOf(0f)
		var start = 0
		var count: Int
		var lineOffset = -fontMetrics.top
		var lineMaxWidth: Float
		while (start < source.length) {
			lineMaxWidth = if (lineOffset + fontMetrics.bottom < imagePadding ||
				lineOffset + fontMetrics.top > imagePadding + bitmap.height
			) {
				width.toFloat()
			} else {
				width.toFloat() - bitmap.width
			}
			count = paint.breakText(source, start, source.length, true, lineMaxWidth, measuredWidth)
			canvas.drawText(source, start, start + count, 0f, lineOffset, paint)
			start += count
			lineOffset += paint.fontSpacing
		}
	}

}