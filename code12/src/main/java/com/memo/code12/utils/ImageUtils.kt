package com.memo.code12.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.blankj.utilcode.util.Utils

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-20 14:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object ImageUtils {
	fun getBitmap(source: Int, size: Int): Bitmap {
		val option = BitmapFactory.Options()
		option.inJustDecodeBounds = true
		BitmapFactory.decodeResource(Utils.getApp().resources, source, option)
		option.inJustDecodeBounds = false
		option.inTargetDensity = size
		option.inDensity = option.outWidth
		return BitmapFactory.decodeResource(Utils.getApp().resources, source, option)
	}
}