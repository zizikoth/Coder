package com.memo.core.base

import android.view.LayoutInflater
import android.view.View
import com.memo.core.R
import kotlinx.android.synthetic.main.layout_title_activity.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-18 13:36
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseTitleActivity : BaseActivity() {

	private lateinit var mRootView: View

	override fun setContentView(layoutResID: Int) {
		mRootView = LayoutInflater.from(mContext).inflate(R.layout.layout_title_activity, null)
		val container = LayoutInflater.from(mContext).inflate(layoutResID, null)
		mRootView.mContainer.addView(container)
		mRootView.mTitleView.setTitle(bindTitle())
		super.setContentView(mRootView)
	}

	abstract fun bindTitle(): String

}