package com.memo.code15.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import com.memo.code15.R
import com.memo.core.base.BaseTitleActivity
import kotlinx.android.synthetic.main.activity_anim.*

class AnimActivity : BaseTitleActivity() {

	override fun bindTitle(): String = "自定义动画"

	override fun bindLayoutRes(): Int = R.layout.activity_anim

	override fun initialize() {
		val animSet = AnimatorSet()
		val topAnim = ObjectAnimator.ofFloat(mAnimView, "topFlip", -60f)
		val bottomAnim = ObjectAnimator.ofFloat(mAnimView, "bottomFlip", 60f)
		val rotateAnim = ObjectAnimator.ofFloat(mAnimView, "flipRotation", 90f)
		animSet.setDuration(1000).startDelay = 1000
		animSet.playTogether(topAnim, bottomAnim, rotateAnim)
		animSet.start()
	}
}