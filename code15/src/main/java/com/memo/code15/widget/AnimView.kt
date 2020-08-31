package com.memo.code15.widget

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.memo.code15.R
import com.memo.core.utils.ImageUtils
import org.jetbrains.anko.dimen

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-27 16:12
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class AnimView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val size = dimen(R.dimen.dp150)
	private val avatar = ImageUtils.getBitmap(R.drawable.cake, size)
	private val camera = Camera()

	// 上方折痕角度
	var topFlip = 0f
		set(value) {
			field = value
			invalidate()
		}

	// 下方折痕角度
	var bottomFlip = 0f
		set(value) {
			field = value
			invalidate()
		}

	// 折痕旋转角度
	var flipRotation = 0f
		set(value) {
			field = value
			invalidate()
		}

	init {
		camera.setLocation(0f, 0f, -5 * resources.displayMetrics.density)
	}


	override fun onDraw(canvas: Canvas) {
		canvas.withSave {
			canvas.translate(width / 2f, height / 2f)
			canvas.rotate(-flipRotation)
			canvas.clipRect(-size, -size, size, 0)
			camera.save()
			camera.rotateX(topFlip)
			camera.applyToCanvas(canvas)
			camera.restore()
			canvas.rotate(flipRotation)
			canvas.translate(-width / 2f, -height / 2f)
			canvas.drawBitmap(avatar, (width - size) / 2f, (height - size) / 2f, paint)
		}

		canvas.withSave {
			canvas.translate(width / 2f, height / 2f)
			canvas.rotate(-flipRotation)
			canvas.clipRect(-size, 0, size, size)
			camera.save()
			camera.rotateX(bottomFlip)
			camera.applyToCanvas(canvas)
			camera.restore()
			canvas.rotate(flipRotation)
			canvas.translate(-width / 2f, -height / 2f)
			canvas.drawBitmap(avatar, (width - size) / 2f, (height - size) / 2f, paint)
		}
	}
}