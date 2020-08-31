package com.memo.code17.launch

import com.memo.code17.R
import com.memo.core.base.BaseTitleActivity

class MainActivity : BaseTitleActivity() {

	override fun bindTitle(): String = "MaterialEditText"

	override fun bindLayoutRes(): Int = R.layout.activity_main

	override fun initialize() {
	}
}
