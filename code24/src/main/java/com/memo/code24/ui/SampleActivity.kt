package com.memo.code24.ui

import com.memo.code24.R
import com.memo.core.base.BaseTitleActivity

class SampleActivity : BaseTitleActivity() {
    override fun bindTitle(): String = "多点触摸"

    override fun bindLayoutRes(): Int = R.layout.activity_sample

    override fun initialize() {
    }
}