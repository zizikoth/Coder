package com.memo.code23.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code23.ui.ScaleImageViewActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"ScaleImageView"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"ScaleImageView" -> startActivity<ScaleImageViewActivity>()
			}
		}
	}

}
