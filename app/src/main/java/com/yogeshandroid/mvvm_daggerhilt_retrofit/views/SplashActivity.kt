package com.yogeshandroid.mvvm_daggerhilt_retrofit.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.slidetoact.SlideToActView
import com.yogeshandroid.mvvm_daggerhilt_retrofit.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        clickEvents()
    }

    private fun clickEvents() {

//        binding.slideToActView.onSlideCompleteListener.onSlideComplete()

        binding.slideToActView.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener{
            override fun onSlideComplete(view: SlideToActView) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }

//        binding.slideToActView.onSlideToActAnimationEventListener(SlideToActView.OnSlideCompleteListener{
//
//        })
//
//        binding.slideToActView.onSlideCompleteListener(this, object :
//            SlideToActView.OnSlideCompleteListener {
//            override fun onSlideComplete(view: SlideToActView) {
//                startActivity(Intent(applicationContext, MainActivity::class.java))
//            }
//
//        })


    }
}