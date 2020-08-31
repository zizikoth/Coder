package com.memo.code16.ui

import com.memo.code16.R
import com.memo.core.base.BaseTitleActivity

class DrawableActivity : BaseTitleActivity() {
	override fun bindTitle(): String = "DrawableView"

	override fun bindLayoutRes(): Int = R.layout.activity_draw

	override fun initialize() {
	}
}