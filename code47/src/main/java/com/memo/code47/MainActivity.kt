package com.memo.code47

import com.memo.core.base.BaseTitleActivity

/**
 * title:MainActivity
 * describe:范型
 *
 * @author memo
 * @date 2020-11-19 11:04
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MainActivity : BaseTitleActivity() {

	override fun bindTitle(): String = "范型"

	override fun bindLayoutRes(): Int = R.layout.activity_main

	override fun initialize() {

	}
}