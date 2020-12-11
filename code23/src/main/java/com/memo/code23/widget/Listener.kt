package com.memo.code23.widget

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-11 10:21 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DefaultOnGestureListener : GestureDetector.OnGestureListener {

    override fun onDown(e: MotionEvent?): Boolean = true

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean = true

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean = true

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean = true

}
