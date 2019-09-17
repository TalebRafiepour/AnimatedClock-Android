package com.taleb.animatedclockviewdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startAnimate.setOnClickListener { animatedClockView.animate = true }
        stopAnimate.setOnClickListener { animatedClockView.animate = false }
        resetClock.setOnClickListener { animatedClockView.resetClock() }


    }
}
