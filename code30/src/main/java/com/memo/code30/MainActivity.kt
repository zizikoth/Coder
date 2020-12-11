package com.memo.code30

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.core.base.BaseListActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf()
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
			}
		}
	}

}