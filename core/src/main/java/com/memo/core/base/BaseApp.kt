package com.memo.core.base

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-13 15:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BaseApp : Application() {

	override fun onCreate() {
		super.onCreate()
		Utils.init(this)
	}

}