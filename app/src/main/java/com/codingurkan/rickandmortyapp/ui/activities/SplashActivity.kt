package com.codingurkan.rickandmortyapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.codingurkan.rickandmortyapp.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var binding : ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        splashAction()
    }
    private fun initBinding(){
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
    private fun splashAction(){
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
        },2000)
    }
}