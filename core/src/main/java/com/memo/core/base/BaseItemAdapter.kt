package com.memo.core.base

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.memo.core.R

/**
 * title: 基类条目Item
 * describe:
 *
 * @author memo
 * @date 2020-08-14 09:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BaseItemAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_item) {

	override fun convert(holder: BaseViewHolder, item: String) {
		(holder.itemView as TextView).text = item
	}
}

fun RecyclerView.applyList(data: ArrayList<String>, listener: OnItemClickListener) {
	this.run {
		layoutManager = LinearLayoutManager(context)
		val mAdapter = BaseItemAdapter().apply { setList(data) }
		val divider = RecyclerViewDivider.Builder(context)
			.setColorRes(R.color.color_F5F5F5)
			.setHeightDp(0.5f)
			.setMarginLeftDp(15f)
			.setMarginRightDp(15f)
			.build()
		addItemDecoration(divider)
		adapter = mAdapter
		mAdapter.setOnItemClickListener(listener)
	}
}