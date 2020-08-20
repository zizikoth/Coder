package com.memo.core.tool.ext

import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.core.view.drawToBitmap
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ClickUtils

/**
 * title:View的Kotlin拓展
 * describe:
 *
 * @author memo
 * @date 2019-11-20 14:39
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

/**
 * 设置View的高度
 * @param height 设置的高度
 */
fun View.height(height: Int): View {
	layoutParams.height = height
	return this
}

/**
 * 设置View的宽度
 * @param width 设置的宽度
 */
fun View.width(width: Int): View {
	layoutParams.width = width
	return this
}

/**
 * 设置View的宽度和高度
 * @param width 要设置的宽度
 * @param height 要设置的高度
 */
fun View.widthAndHeight(width: Int, height: Int): View {
	val params: ViewGroup.LayoutParams? = layoutParams
	if (params == null) {
		layoutParams = ViewGroup.LayoutParams(width, height)
	} else {
		params.width = width
		params.height = height
		layoutParams = params
	}
	return this
}


/**
 * 设置View的margin  默认保留原设置
 * @param leftMargin 距离左的距离
 * @param topMargin 距离上的距离
 * @param rightMargin 距离右的距离
 * @param bottomMargin 距离下的距离
 */
fun View.margin(
	leftMargin: Int = Int.MAX_VALUE,
	topMargin: Int = Int.MAX_VALUE,
	rightMargin: Int = Int.MAX_VALUE,
	bottomMargin: Int = Int.MAX_VALUE
): View {
	if (layoutParams is ViewGroup.MarginLayoutParams) {
		val params = layoutParams as ViewGroup.MarginLayoutParams
		if (leftMargin != Int.MAX_VALUE)
			params.leftMargin = leftMargin
		if (topMargin != Int.MAX_VALUE)
			params.topMargin = topMargin
		if (rightMargin != Int.MAX_VALUE)
			params.rightMargin = rightMargin
		if (bottomMargin != Int.MAX_VALUE)
			params.bottomMargin = bottomMargin
		layoutParams = params
	}
	return this
}


/**
 * 设置控件Visible
 */
fun View.visible() {
	visibility = View.VISIBLE
}

/**
 * 设置控件Gone
 */
fun View.gone() {
	visibility = View.GONE
}

/**
 * 设置控件Invisible
 */
fun View.invisible() {
	visibility = View.INVISIBLE
}

/**
 * 设置控件Visible
 */
fun visible(vararg views: View?) {
	for (view in views) {
		view?.visible()
	}
}

/**
 * 设置控件Invisible
 */
fun invisible(vararg views: View?) {
	for (view in views) {
		view?.invisible()
	}
}

/**
 * 设置控件Gone
 */
fun gone(vararg views: View?) {
	for (view in views) {
		view?.gone()
	}
}

/**
 * 设置是否可见
 * @param visibleOrGone true - Visible false - Gone
 */
fun View.setVisible(visibleOrGone: Boolean) {
	visibility = if (visibleOrGone) View.VISIBLE else View.GONE
}

/**
 * 获取View的Bitmap
 * 支持RecyclerView ScrollView 基础控件 不支持ListView了
 * 注意:使用这个方法的时候必须要在View测量完毕之后才能进行
 */
fun View.toBitmap(): Bitmap {
	return this.drawToBitmap()
}

/**
 * 控件绘制监听
 */
inline fun View.onGlobalLayoutListener(crossinline callback: () -> Unit) =
	with(viewTreeObserver) {
		addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
			@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
			override fun onGlobalLayout() {
				callback()
				viewTreeObserver.removeOnGlobalLayoutListener(this)
			}
		})
	}

/**
 * 设置控件的点击时间
 * @param method 点击事件
 */
fun View.onClick(method: (view: View) -> Unit) {
	ClickUtils.applyGlobalDebouncing(this, method)
}

fun onViewClickListener(vararg views: View, onClick: (View) -> Unit) {
	ClickUtils.applyGlobalDebouncing(views, onClick)
}

/**
 * 设置距离填充状态栏
 */
fun View.paddingStatusBar() {
	setPadding(paddingLeft, BarUtils.getStatusBarHeight(), paddingRight, paddingBottom)
}

/**
 * 设置距离状态栏高度
 */
fun View.marginStatusBar() {
	margin(topMargin = BarUtils.getStatusBarHeight())
}





