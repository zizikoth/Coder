package com.memo.code12.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code12.ui.AvatarActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {

	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"avatar"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, view, position ->
			when (adapter.data[position]) {
				"avatar" -> startActivity<AvatarActivity>()
			}
		}
	}

}