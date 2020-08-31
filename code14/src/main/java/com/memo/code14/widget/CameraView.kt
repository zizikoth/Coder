package com.memo.code14.widget

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.memo.code14.R
import com.memo.core.utils.ImageUtils
import org.jetbrains.anko.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-27 10:44
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CameraView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val imageSize = dimen(R.dimen.dp150)
	private val avatar = ImageUtils.getBitmap(R.drawable.cake, imageSize)

	private val camera = Camera()

	init {
		camera.rotateX(30f)
		camera.setLocation(0f, 0f, -4 * resources.displayMetrics.density)
	}

	override fun onDraw(canvas: Canvas) {
		// 绘制上半部分
		canvas.withSave {
			canvas.translate(width / 2f, height / 2f)
			canvas.rotate(-90f)
			canvas.clipRect(-imageSize.toFloat(), -imageSize.toFloat(), imageSize.toFloat(), 0f)
			canvas.rotate(90f)
			canvas.translate(-width / 2f, -height / 2f)
			canvas.drawBitmap(avatar, (width - imageSize) / 2f, (height - imageSize) / 2f, paint)
		}

		// 绘制下半部分
		canvas.withSave {
			// 7.图原中心移回到画布中心
			canvas.translate(width / 2f, height / 2f)
			// 6.旋转-90度回正
			canvas.rotate(-90f)
			// 5.Z轴Camera旋转
			camera.applyToCanvas(canvas)
			// 4.裁剪下半部分（旋转过后的下半部分）
			canvas.clipRect(-imageSize.toFloat(), 0f, imageSize.toFloat(), imageSize.toFloat())
			// 3.平面旋转90度
			canvas.rotate(90f)
			// 2.图中心移动到左上角
			canvas.translate(-width / 2f, -height / 2f)
			// 1.中心画个图
			canvas.drawBitmap(avatar, (width - imageSize) / 2f, (height - imageSize) / 2f, paint)
		}
	}
}
