package com.memo.code18_20.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import com.memo.code18_20.R
import org.jetbrains.anko.dimen
import kotlin.math.max

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-04 09:52
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TagLayout @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

	private val childBounds = ArrayList<Rect>()
	private val margin = dimen(R.dimen.dp10)

	@SuppressLint("DrawAllocation")
	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		// 当前宽度
		var widthUsed = margin
		// 当前高度
		var heightUsed = margin
		// 一行最大高度
		var lineMaxHeight = 0
		// 一行最大宽度
		var lineMaxWidth = 0
		val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
		val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
		children.forEachIndexed { index, child ->
			measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
			// 如果需要换行
			if (specWidthMode != MeasureSpec.UNSPECIFIED && widthUsed + child.measuredWidth + margin > specWidthSize) {
				widthUsed = margin
				heightUsed += lineMaxHeight + margin
				lineMaxHeight = 0
				measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed)
			}
			if (index >= childBounds.size) {
				childBounds.add(Rect())
			}
			val bounds = childBounds[index]
			bounds.set(widthUsed, heightUsed, widthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
			widthUsed += child.measuredWidth + margin
			lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
			lineMaxWidth = max(lineMaxWidth, widthUsed)
		}
		heightUsed += lineMaxHeight + margin
		setMeasuredDimension(lineMaxWidth, heightUsed)
	}

	override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
		children.forEachIndexed { index, view ->
			val bound = childBounds[index]
			view.layout(bound.left, bound.top, bound.right, bound.bottom)
		}
	}

}