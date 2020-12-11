package com.memo.core.tool.ext

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.memo.core.tool.glide.GlideApp

/**
 * title:ImageView拓展
 * describe:
 *
 * @author memo
 * @date 2020-08-12 16:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun ImageView.load(url: Any) {
	GlideApp.with(this)
		.load(url)
		.placeholder(ColorDrawable(Color.LTGRAY))
		.centerCrop()
		.into(this)
}

fun ImageView.loadRound(url: Any, radius: Int) {
	GlideApp.with(this)
		.load(url)
		.placeholder(ColorDrawable(Color.LTGRAY))
		.transform(CenterCrop(), RoundedCorners(radius))
		.into(this)
}

fun ImageView.loadCircle(url: Any) {
	GlideApp.with(this)
		.load(url)
		.placeholder(ColorDrawable(Color.LTGRAY))
		.transform(CenterCrop(), CircleCrop())
		.into(this)
}
