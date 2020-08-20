package com.memo.code12.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.core.base.BaseListActivity

class MainActivity : BaseListActivity() {

	override fun bindData(): ArrayList<String> {
		return arrayListOf(
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, view, position ->
			when (adapter.data[position]) {
			}
		}
	}

}