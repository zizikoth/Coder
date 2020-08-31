package com.memo.code16.ui

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.memo.code16.R
import com.memo.core.base.BaseTitleActivity

class DrawableBitmapActivity : BaseTitleActivity() {
	override fun bindTitle(): String = "Bitmap <-> Drawable"

	override fun bindLayoutRes(): Int = R.layout.activity_drawable

	override fun initialize() {
		var bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
		val drawable = bitmap.toDrawable(resources)
		bitmap = drawable.toBitmap()
	}

}