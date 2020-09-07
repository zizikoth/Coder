package com.memo.code18_20.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-03 15:56
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SquareImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec)
		val size = min(measuredHeight, measuredWidth)
		setMeasuredDimension(size, size)
	}

}