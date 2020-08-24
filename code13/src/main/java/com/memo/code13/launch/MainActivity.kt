package com.memo.code13.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code13.ui.MultiTextActivity
import com.memo.code13.ui.SportActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"SportView",
			"MultiText"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"SportView" -> startActivity<SportActivity>()
				"MultiText" -> startActivity<MultiTextActivity>()
			}
		}
	}

}
