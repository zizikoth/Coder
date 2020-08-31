package com.memo.code14.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.memo.code14.R
import com.memo.core.utils.ImageUtils
import org.jetbrains.anko.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-27 09:20
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ClipView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val imageSize = dimen(R.dimen.dp150)
	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val cake = ImageUtils.getBitmap(R.drawable.cake, imageSize)
	private val circlePath = Path()

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		circlePath.addCircle(width / 2f, height / 2f, imageSize / 2f, Path.Direction.CW)
	}

	override fun onDraw(canvas: Canvas) {
		canvas.clipPath(circlePath)
		canvas.drawBitmap(cake, (width - cake.width) / 2f, (height - cake.height) / 2f, paint)
	}
}