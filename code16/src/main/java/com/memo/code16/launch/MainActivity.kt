package com.memo.code16.launch

import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.code16.ui.DrawableActivity
import com.memo.code16.ui.DrawableBitmapActivity
import com.memo.core.base.BaseListActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseListActivity() {

	override fun bindData(): ArrayList<String> {
		return arrayListOf(
			"Bitmap <-> Drawable",
			"Drawable"
		)
	}

	override fun bindListener(): OnItemClickListener {
		return OnItemClickListener { adapter, _, position ->
			when (adapter.data[position]) {
				"Bitmap <-> Drawable" -> startActivity<DrawableBitmapActivity>()
				"Drawable" -> startActivity<DrawableActivity>()
			}
		}
	}
}
