package com.yogeshandroid.mvvm_daggerhilt_retrofit.views

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yogeshandroid.mvvm_daggerhilt_retrofit.R
import com.yogeshandroid.mvvm_daggerhilt_retrofit.databinding.ActivityMainBinding
import com.yogeshandroid.mvvm_daggerhilt_retrofit.viewModel.RandomViewModel
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var ft: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        clickEvents()
        attachObservers()

    }

    private fun attachObservers() {


    }

    private fun clickEvents() {

    }

    private fun initSetup() {
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame, HomeFragment()).commit()
    }
}