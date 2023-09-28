package com.acoder.base.Activity

import com.acoder.base.Base.BaseActivity
import com.acoder.base.R
import com.acoder.base.databinding.ActivityHomePageBinding


/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */
class HomePage : BaseActivity() {
    lateinit var b: ActivityHomePageBinding
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_home_page
    }

    override fun initComponent() {

        b = getBinding()



    }


}