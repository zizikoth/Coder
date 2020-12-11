package com.memo.code19.ui

import com.memo.code19.R
import com.memo.core.base.BaseTitleActivity
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_thread_ui.*
import kotlin.concurrent.thread

class ThreadUiActivity : BaseTitleActivity() {

	override fun bindTitle(): String = "子线程修改UI"

	override fun bindLayoutRes(): Int = R.layout.activity_thread_ui

	override fun initialize() {
		mBtnChange.onClick {
			thread {
				mBtnChange.text = Thread.currentThread().name
			}
		}
	}
}