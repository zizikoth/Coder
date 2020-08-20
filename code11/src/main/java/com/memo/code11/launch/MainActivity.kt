package com.memo.code11.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code11.ui.DashboardActivity
import com.memo.code11.ui.PieActivity
import com.memo.code11.ui.TestActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {

	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"TestView",
			"DashboardView",
			"PieView"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, view, position ->
			when (adapter.data[position]) {
				"TestView" -> startActivity<TestActivity>()
				"DashboardView" -> startActivity<DashboardActivity>()
				"PieView" -> startActivity<PieActivity>()
			}
		}
	}

}