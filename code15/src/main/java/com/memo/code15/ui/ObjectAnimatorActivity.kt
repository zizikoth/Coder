package com.memo.code15.ui

import android.animation.ObjectAnimator
import com.memo.code15.R
import com.memo.core.base.BaseTitleActivity
import com.memo.core.tool.ext.dimen
import com.memo.core.tool.ext.dp2pxf
import kotlinx.android.synthetic.main.activity_object_animator.*

class ObjectAnimatorActivity : BaseTitleActivity() {

	override fun bindTitle(): String = "ObjectAnimator"

	override fun bindLayoutRes(): Int = R.layout.activity_object_animator

	override fun initialize() {

		mCircleView.animate()
			.translationX(50.dp2pxf)
			.translationY(50.dp2pxf)
			.scaleX(1.2f)
			.scaleY(1.2f)
			.alpha(0.5f)
			.setStartDelay(500)
			.setDuration(1000)
			.start()

		val objectAnimator = ObjectAnimator.ofFloat(mCircleView, "radius", dimen(R.dimen.dp50))
		objectAnimator.duration = 1000
		objectAnimator.startDelay = 2000
		objectAnimator.start()
	}
}