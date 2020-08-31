package com.memo.code15.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.memo.code15.R
import org.jetbrains.anko.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-27 15:51
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CircleView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

	var radius = dimen(R.dimen.dp150).toFloat()
		set(value) {
			field = value
			invalidate()
		}

	override fun onDraw(canvas: Canvas) {
		canvas.drawCircle(width / 2f, height / 2f, radius, paint)
	}

}