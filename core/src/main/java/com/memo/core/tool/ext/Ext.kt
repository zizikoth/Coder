package com.memo.core.tool.ext

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-07 15:05
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun Int.foreach(action: (Int) -> Unit) {
	(0 until this).forEach {
		action.invoke(it)
	}
}