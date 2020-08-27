package com.memo.code14.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code14.ui.CameraActivity
import com.memo.code14.ui.ClipActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {
	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"clip",
			"camera"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"clip" -> startActivity<ClipActivity>()
				"camera" -> startActivity<CameraActivity>()
			}
		}
	}

}