package com.memo.core.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-24 14:31
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	override fun onDraw(canvas: Canvas?) {
		super.onDraw(canvas)
		canvas?.let {
			initDraw()
			startDraw(it)
		}
	}

	abstract fun initDraw()
	abstract fun startDraw(canvas: Canvas)
}