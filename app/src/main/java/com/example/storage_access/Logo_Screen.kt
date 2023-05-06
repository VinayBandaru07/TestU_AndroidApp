package com.example.storage_access

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation

import android.widget.TextView
import android.content.Intent

import android.R
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import com.example.storage_access.databinding.ActivityLogoScreenBinding


class Logo_Screen : AppCompatActivity() {

    var harpreet_text: TextView? = null
    var fade_in_anim: Animation? = null

    private lateinit var binding : ActivityLogoScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        binding = ActivityLogoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        getSupportActionBar()?.hide()
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )







        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)


    }


}