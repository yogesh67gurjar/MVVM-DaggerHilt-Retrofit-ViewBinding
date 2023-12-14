package com.yogeshandroid.mvvm_daggerhilt_retrofit.views

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yogeshandroid.mvvm_daggerhilt_retrofit.databinding.ActivityMainBinding
import com.yogeshandroid.mvvm_daggerhilt_retrofit.viewModel.RandomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val randomViewModel: RandomViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        clickEvents()
        attachObservers()

    }

    private fun attachObservers() {
        randomViewModel.failureString.observe(this, Observer {
            binding.progress.visibility = View.GONE
        })

        randomViewModel.randomData.observe(this, Observer {
            binding.progress.visibility = View.GONE
            Glide.with(this).load(it.message).into(binding.image)
        })

    }

    private fun clickEvents() {
        binding.btn.setOnClickListener(View.OnClickListener {
            ObjectAnimator.ofFloat<View>(it, View.ALPHA, 0.4f, 1.0f).setDuration(1000).start()

            randomViewModel.getRandomDogImages()
            binding.progress.visibility = View.VISIBLE
        })
    }

    private fun initSetup() {
        randomViewModel.getRandomDogImages()
        binding.progress.visibility = View.VISIBLE
    }
}