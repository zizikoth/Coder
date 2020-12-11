package com.memo.code28.ui

import com.memo.code28.R
import com.memo.core.base.BaseTitleActivity

class SampleActivity : BaseTitleActivity() {
	override fun bindTitle(): String = "文本约束"

	override fun bindLayoutRes(): Int = R.layout.activity_sample

	override fun initialize() {
	}
}