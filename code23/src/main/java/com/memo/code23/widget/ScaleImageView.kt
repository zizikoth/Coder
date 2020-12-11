package com.memo.code23.widget

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.memo.code23.R
import com.memo.core.utils.ImageUtils
import org.jetbrains.anko.dimen

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
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmapSize = dimen(R.dimen.dp300)
    private val bitmap = ImageUtils.getBitmap(R.drawable.cake, bitmapSize)
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var maxOffsetX = 0f
    private var maxOffsetY = 0f

    private var minScale = 1f
    private var maxScale = 1f
    private val extraScale = 1.3f
    private val gestureDetector = GestureDetectorCompat(context, OnGestureListener())
    private val scaleGestureDetector = ScaleGestureDetector(context, OnScaleGestureListener())
    private var isBigMode = false
    private var currentScale = 1f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnim = ObjectAnimator.ofFloat(this, "currentScale", minScale, maxScale)

    private val scroller = OverScroller(context)
    private val overScrollSize = dimen(R.dimen.dp40)


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (width - bitmap.width) / 2f
        originalOffsetY = (height - bitmap.height) / 2f
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            minScale = width / bitmap.width.toFloat()
            maxScale = height / bitmap.height.toFloat() * extraScale
        } else {
            maxScale = width / bitmap.width.toFloat()
            minScale = height / bitmap.height.toFloat() * extraScale
        }
        maxOffsetX = (bitmap.width * maxScale - width) / 2f
        maxOffsetY = (bitmap.height * maxScale - height) / 2f
        scaleAnim.setFloatValues(minScale, maxScale)
        currentScale = minScale
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scaleFraction = (currentScale - minScale) / (maxScale - minScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    inner class OnGestureListener : GestureDetector.SimpleOnGestureListener() {

        /**
         * 手指滑动
         * @param distanceX Float 两点X轴的距离 前一个点 减去 后一个点
         * @param distanceY Float 两点Y轴的距离 前一个点 减去 后一个点
         * @return Boolean
         */
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (isBigMode) {
                offsetX -= distanceX
                // 设置横向不超过边界
                offsetX = if (offsetX < -maxOffsetX) -maxOffsetX else if (offsetX > maxOffsetX) maxOffsetX else offsetX
                offsetY -= distanceY
                // 设置纵向不超过边界
                offsetY = if (offsetY < -maxOffsetY) -maxOffsetY else if (offsetY > maxOffsetY) maxOffsetY else offsetY
                invalidate()
            }
            return false
        }


        /**
         * 惯性滑动
         * @param e1 MotionEvent
         * @param e2 MotionEvent
         * @param velocityX Float 抬手时候的X轴速度
         * @param velocityY Float 抬手时候的Y轴速度
         * @return Boolean
         */
        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (isBigMode) {
                // 把偏移后的点当作移动中心点
                scroller.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    -maxOffsetX.toInt(), maxOffsetX.toInt(), -maxOffsetY.toInt(), maxOffsetY.toInt(),
                    overScrollSize, overScrollSize)
                // 在下一桢进行绘制
                ViewCompat.postOnAnimation(this@ScaleImageView, refreshRunnable)
            }
            return false
        }

        private val refreshRunnable = object : Runnable {
            override fun run() {
                scroller.computeScrollOffset()
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                if (!scroller.isFinished) {
                    postOnAnimation(this)
                }
            }
        }

        override fun onDown(e: MotionEvent?): Boolean = true

        override fun onDoubleTap(e: MotionEvent): Boolean {
            isBigMode = !isBigMode
            if (isBigMode) {
                // 这里的负号？ 为了让图片向右 画布需要向左移动  上下左右同理
                offsetX = -(e.x - width / 2f) * (maxScale / minScale - 1)
                offsetY = -(e.y - height / 2f) * (maxScale / minScale - 1)
                // 设置横向不超过边界
                offsetX = offsetX.coerceAtLeast(-maxOffsetX).coerceAtMost(maxOffsetX)
                // 设置纵向不超过边界
                offsetY = offsetY.coerceAtLeast(-maxOffsetY).coerceAtMost(maxOffsetY)
                scaleAnim.start()
            } else {
                scaleAnim.reverse()
            }
            return true
        }

    }

    inner class OnScaleGestureListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            // 这里的负号？ 为了让图片向右 画布需要向左移动  上下左右同理
            offsetX = -(detector.focusX - width / 2f) * (maxScale / minScale - 1)
            offsetY = -(detector.focusY - height / 2f) * (maxScale / minScale - 1)
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val temp = currentScale * detector.scaleFactor
            return if (temp < minScale || temp > maxScale) {
                false
            } else {
                currentScale *= detector.scaleFactor
                true
            }
            // 获取与初始状态的比例
            // return false
            // 获取上一状态的比例
            // return true
        }
    }

}