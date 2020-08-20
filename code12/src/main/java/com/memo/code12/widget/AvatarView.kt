package com.memo.code12.widget

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.memo.code12.R
import com.memo.code12.utils.ImageUtils
import com.memo.core.tool.ext.dp2pxf

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-20 14:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class AvatarView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val avatarSize = 100.dp2pxf()

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

	private var xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

	private lateinit var bounds: RectF

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		bounds = RectF((width - avatarSize) / 2, (height - avatarSize) / 2, (width + avatarSize) / 2, (height + avatarSize) / 2)
	}

	@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
	override fun onDraw(canvas: Canvas) {
		val count = canvas.saveLayer(bounds, null)
		canvas.drawOval((width - avatarSize) / 2, (height - avatarSize) / 2, (width + avatarSize) / 2, (height + avatarSize) / 2, paint)
		paint.xfermode = xfermode
		val bitmap = ImageUtils.getBitmap(R.drawable.cake, avatarSize.toInt())
		canvas.drawBitmap(bitmap, (width - avatarSize) / 2, (height - avatarSize) / 2, paint)
		paint.xfermode = null
		canvas.restoreToCount(count)
	}
}