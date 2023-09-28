package com.acoder.imagecompressor.Activity

import com.acoder.imagecompressor.Base.BaseActivity
import com.acoder.imagecompressor.R
import com.acoder.imagecompressor.databinding.ActivityHomePageBinding


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