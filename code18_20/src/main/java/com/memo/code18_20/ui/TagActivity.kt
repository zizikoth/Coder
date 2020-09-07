package com.memo.code18_20.ui

import com.memo.code18_20.R
import com.memo.code18_20.widget.TagView
import com.memo.core.base.BaseTitleActivity
import com.memo.core.tool.ext.foreach
import kotlinx.android.synthetic.main.activity_tag.*

class TagActivity : BaseTitleActivity() {
	override fun bindTitle(): String = "TagLayout"

	override fun bindLayoutRes(): Int = R.layout.activity_tag

	override fun initialize() {
		20.foreach {
			mTagLayout.addView(TagView(mContext))
		}

	}
}