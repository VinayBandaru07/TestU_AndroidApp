package com.example.storage_access

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class readLogo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_logo)

        getSupportActionBar()?.hide()
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        var FilePosition = intent.getIntExtra("SelectedPosition", 1)

        Handler().postDelayed(Runnable {
            val intent = Intent(this, Prepare::class.java).putExtra("SelectedPosition", FilePosition)
            startActivity(intent)
            finish()
        }, 1000)





    }
}