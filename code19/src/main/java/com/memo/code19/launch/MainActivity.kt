package com.memo.code19.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code19.ui.ThreadUiActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"子线程修改UI"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"子线程修改UI" -> startActivity<ThreadUiActivity>()
			}
		}
	}

}
