package com.memo.code11.ui

import com.memo.code11.R
import com.memo.core.base.BaseTitleActivity
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlin.random.Random

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-18 14:51
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DashboardActivity : BaseTitleActivity() {

	override fun bindTitle(): String = "仪表盘"

	override fun bindLayoutRes(): Int = R.layout.activity_dashboard

	override fun initialize() {
		mDashboardView.onClick {
			mDashboardView.speed = Random.nextInt(0, 20)
		}
	}
}