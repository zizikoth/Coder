package com.memo.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * title:基类Fragment
 * describe:
 *
 * @author memo
 * @date 2020-08-13 16:01
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseFragment : Fragment() {
	/*** 根布局 ***/
	protected lateinit var mRootView: View

	/*** 上下文Activity ***/
	protected val mContext by lazy { activity!! }

	/*** 标识 标识是否界面准备完毕 ***/
	private var isPrepared: Boolean = false

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(bindLayoutRes(), container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mRootView = view
		isPrepared = true
		onVisibleToUser()
	}

	private fun onVisibleToUser() {
		if (isPrepared && isResumed) {
			isPrepared = false
			initialize()
		}
	}

	override fun onResume() {
		if (isPrepared) {
			onVisibleToUser()
		}
		super.onResume()
	}

	/*** 绑定布局 ***/
	@LayoutRes
	protected abstract fun bindLayoutRes(): Int

	/*** 在界面可见的时候进行初始化 ***/
	protected abstract fun initialize()
}