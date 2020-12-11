package com.memo.code23.widget

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.memo.code23.R
import com.memo.core.utils.ImageUtils
import org.jetbrains.anko.dimen
import kotlin.math.max
import kotlin.math.min

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-09 14:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ScaleImageView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val size = dimen(R.dimen.dp300)
	private val bitmap = ImageUtils.getBitmap(R.drawable.cake, size)

	// 图片的初始偏移
	private var originOffsetX = 0f
	private var originOffsetY = 0f

	// 手指移动带动图片移动
	private var offsetX = 0f
	private var offsetY = 0f

	// 最小放缩比例
	private var smallScale = 0f

	// 最大放缩比例
	private var bigScale = 0f

	// 额外放缩比例
	private var extraScaleFraction = 1.5f

	// 实际放缩比例
	private var currentScale = 1f

	// 点击监听器
	private val gestureDetector = GestureDetectorCompat(context, this)

	// 滑动监听器
	private val scroller = OverScroller(context)

	// 放大缩小标志
	private var inScaleBigMode = false

	private var scaleFraction = 0f
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}
	private val scaleAnim by lazy { ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f) }

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		originOffsetX = (width - bitmap.width) / 2f
		originOffsetY = (height - bitmap.height) / 2f

		val widthScale = width / bitmap.width.toFloat()
		val heightScale = height / bitmap.height.toFloat()
		smallScale = min(widthScale, heightScale)
		bigScale = max(widthScale, heightScale) * extraScaleFraction
	}

	override fun onDraw(canvas: Canvas) {
		canvas.translate(offsetX, offsetY)
		currentScale = smallScale + (bigScale - smallScale) * scaleFraction
		canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
		canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint)
	}

	@SuppressLint("ClickableViewAccessibility")
	override fun onTouchEvent(event: MotionEvent?): Boolean {
		return gestureDetector.onTouchEvent(event)
	}

	override fun onDown(e: MotionEvent?): Boolean = true

	// 按压的时候
	override fun onShowPress(e: MotionEvent?) {}

	// 单击监听 在长按时间内 一次手指按下后抬起 如果支持双击此时有可能是双击中的第一次抬起 不支持双击推荐使用
	override fun onSingleTapUp(e: MotionEvent?): Boolean = false

	// 长按监听
	override fun onLongPress(e: MotionEvent?) {}

	// 手指移动的时候
	override fun onScroll(downEvent: MotionEvent?, currentEvent: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
		if (inScaleBigMode) {
			offsetX -= distanceX
			offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
			offsetX = max(offsetX, -((bitmap.width * bigScale - width) / 2))
			offsetY -= distanceY
			offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
			offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
			invalidate()
		}
		return false
	}

	// 手指移开后继续惯性滑动的时候
	override fun onFling(downEvent: MotionEvent?, currentEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
		if (inScaleBigMode) {
		}
		return false
	}

	// 确定一定肯定是单机事件 但是会有双击的检测时间 会等待这个时间 支持双击的时候推荐使用
	override fun onSingleTapConfirmed(e: MotionEvent?): Boolean = true

	// 双击事件
	override fun onDoubleTap(e: MotionEvent?): Boolean {
		inScaleBigMode = !inScaleBigMode
		if (inScaleBigMode) {
			scaleAnim.start()
		} else {
			scaleAnim.reverse()
		}
		return true
	}

	// 双击第二次按下不抬起并且开始滑动
	override fun onDoubleTapEvent(e: MotionEvent?): Boolean = true

}