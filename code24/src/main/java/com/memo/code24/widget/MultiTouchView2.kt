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
 * title:协作多指触控
 * describe:
 *
 * @author memo
 * @date 2021-03-11 2:35 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MultiTouchView2 @JvmOverloads constructor(
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

    private var trackingPointerId = 0

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    // Point(x,y,index,id）x坐标 y坐标 index序号 id实际id

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var sumX = 0f
        var sumY = 0f
        val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        var pointerCount = event.pointerCount
        // 在多个手指在界面上，一只手指抬起，瞬间pointCount并没有减少，
        // 所以需要处理，如果是抬起手指，不去加该手指的位置数据
        for (i in 0 until pointerCount) {
            // 不是抬起手指 才加上这只手指的数据
            if (!(isPointerUp && i == event.actionIndex)) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        if (isPointerUp) pointerCount--
        val focusX = sumX / pointerCount
        val focusY = sumY / pointerCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originalOffsetX
                offsetY = focusY - downY + originalOffsetY
                // 不超出边界
                offsetX = if (offsetX < 0) 0f else if (offsetX > width - bitmapSize) (width - bitmapSize).toFloat() else offsetX
                offsetY = if (offsetY < 0) 0f else if (offsetY > height - bitmapSize) (height - bitmapSize).toFloat() else offsetY
                invalidate()
            }
        }
        return true
    }
}