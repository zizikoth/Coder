package com.memo.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.BarUtils
import com.memo.core.R

/**
 * title:基类Activity
 * describe:
 *
 * @author memo
 * @date 2020-08-13 15:51
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseActivity : AppCompatActivity() {

	/*** Context ***/
	protected val mContext: BaseActivity by lazy { this }

	/*** LifecycleOwner ***/
	protected val mLifecycleOwner: LifecycleOwner by lazy { this }

	override fun onCreate(savedInstanceState: Bundle?) {
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.activity_fade_hide)
		super.onCreate(savedInstanceState)
		setContentView(bindLayoutRes())
		BarUtils.setStatusBarLightMode(this, true)
		initialize()
	}

	override fun finish() {
		super.finish()
		overridePendingTransition(R.anim.activity_fade_show, R.anim.slide_out_to_right)
	}

	@LayoutRes
	abstract fun bindLayoutRes(): Int

	abstract fun initialize()

}