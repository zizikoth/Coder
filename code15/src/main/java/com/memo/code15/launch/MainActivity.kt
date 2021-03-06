package com.memo.code15.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code15.ui.AnimActivity
import com.memo.code15.ui.ObjectAnimatorActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"ObjectAnimator",
			"Anim"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"ObjectAnimator" -> startActivity<ObjectAnimatorActivity>()
				"Anim" -> startActivity<AnimActivity>()
			}
		}
	}

}
