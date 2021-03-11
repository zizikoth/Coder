package com.memo.code24.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.memo.code24.R
import com.memo.core.tool.ext.dp2px
import com.memo.core.utils.ImageUtils

/**
 * title:接力多指触控
 * describe:
 *
 * @author memo
 * @date 2021-03-11 2:35 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MultiTouchView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmapSize = 200.dp2px

    private val bitmap = ImageUtils.getBitmap(R.drawable.cake, bitmapSize)

    // 实际需要显示的偏移 （左上角位置）
    private var offsetX = 0f
    private var offsetY = 0f

    // 手指按下的偏移 （左上角位置）
    private var downX = 0f
    private var downY = 0f

    // 上一次图像的偏移（左上角位置）
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    // 追踪的手指id
    private var trackingPointerId = 0

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    // Point(x,y,index,id）x坐标 y坐标 index序号 id实际id

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                // 当前按下的手指index
                val actionIndex = event.actionIndex
                trackingPointerId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.findPointerIndex(actionIndex)
                // 如果抬起的是后一次追踪的手指才进行处理
                if (trackingPointerId == pointerId) {
                    // 如果你抬起的手指是最后序号的手指
                    val newPointerActionIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointerId = event.getPointerId(newPointerActionIndex)
                    downX = event.getX(newPointerActionIndex)
                    downY = event.getY(newPointerActionIndex)
                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val trackingPointIndex = event.findPointerIndex(trackingPointerId)
                offsetX = event.getX(trackingPointIndex) - downX + originalOffsetX
                offsetY = event.getY(trackingPointIndex) - downY + originalOffsetY
                // 不超出边界
                offsetX = if (offsetX < 0) 0f else if (offsetX > width - bitmapSize) (width - bitmapSize).toFloat() else offsetX
                offsetY = if (offsetY < 0) 0f else if (offsetY > height - bitmapSize) (height - bitmapSize).toFloat() else offsetY
                invalidate()
            }
        }
        return true
    }
}