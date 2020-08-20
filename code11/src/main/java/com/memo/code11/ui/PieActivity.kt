package com.memo.code11.ui

import com.memo.code11.R
import com.memo.core.base.BaseTitleActivity
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_pie.*
import kotlin.random.Random

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-18 17:58
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class PieActivity : BaseTitleActivity() {
	override fun bindTitle(): String = "饼图"

	override fun bindLayoutRes(): Int = R.layout.activity_pie

	override fun initialize() {
		mPieView.percents = arrayListOf(0.1f, 0.2f, 0.3f, 0.4f)
		mPieView.onClick { mPieView.selectPosition = Random.nextInt(0, 4) }
	}
}