package com.memo.code24.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import androidx.core.util.forEach
import com.memo.core.tool.ext.dp2pxf

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
class MultiTouchView3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4.dp2pxf
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = Color.BLACK
    }
    private val paths = SparseArray<Path>()

    init {
        setBackgroundColor(Color.WHITE)
    }

    override fun onDraw(canvas: Canvas) {
        paths.forEach { _, path -> canvas.drawPath(path, paint) }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            // 手指按下 不管是1个还是多个
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                val actionIndex = event.actionIndex
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                paths.append(event.getPointerId(actionIndex), path)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val pointerId = event.getPointerId(i)
                    paths[pointerId].lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val pointerId = event.getPointerId(event.actionIndex)
                paths.remove(pointerId)
                invalidate()
            }
        }
        return true
    }

}