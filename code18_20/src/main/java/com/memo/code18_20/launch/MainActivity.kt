package com.memo.code18_20.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code18_20.ui.CircleActivity
import com.memo.code18_20.ui.SquareActivity
import com.memo.code18_20.ui.TagActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"SquareImageView",
			"CircleView",
			"TagLayout"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"SquareImageView" -> startActivity<SquareActivity>()
				"CircleView" -> startActivity<CircleActivity>()
				"TagLayout" -> startActivity<TagActivity>()
			}
		}
	}

}
