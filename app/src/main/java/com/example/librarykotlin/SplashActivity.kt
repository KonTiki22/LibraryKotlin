package com.example.librarykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var imageView = findViewById<View>(R.id.imageSplash)
        var intent = Intent(this, MainActivity::class.java)
        val animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.fade_in)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        imageView?.startAnimation(animation)
    }
}