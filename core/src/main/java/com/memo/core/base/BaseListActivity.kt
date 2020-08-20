package com.memo.core.base

import com.blankj.utilcode.util.AppUtils
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.memo.core.R
import kotlinx.android.synthetic.main.activity_list.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-14 10:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseListActivity : BaseActivity() {

	abstract fun bindData(): ArrayList<String>
	abstract fun bindListener(): OnItemClickListener

	override fun bindLayoutRes(): Int = R.layout.activity_list

	override fun initialize() {
		mTitleView.setTitle(AppUtils.getAppName())
		mRvList.applyList(bindData(), bindListener())
	}
}