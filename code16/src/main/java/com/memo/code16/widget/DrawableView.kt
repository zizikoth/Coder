package com.memo.code16.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.memo.code16.R
import com.memo.core.tool.ext.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-31 09:50
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DrawableView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val blankSize = dimen(R.dimen.dp50)

	private val drawable = MeshDrawable()

	override fun onDraw(canvas: Canvas) {
		val half = blankSize * 3
		drawable.setBounds(
			(width / 2f - half).toInt(),
			(height / 2f - half).toInt(),
			(width / 2f + half).toInt(),
			(height / 2f + half).toInt()
		)
		drawable.draw(canvas)
	}
}