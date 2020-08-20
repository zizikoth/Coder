package com.memo.code11.ui

import com.memo.code11.R
import com.memo.core.base.BaseTitleActivity

class TestActivity : BaseTitleActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_test

	override fun bindTitle(): String = "TestView"

	override fun initialize() {

	}
}