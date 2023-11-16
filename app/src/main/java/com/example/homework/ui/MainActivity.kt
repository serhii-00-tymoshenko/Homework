package com.example.homework.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.homework.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        addChangeUserGesture()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        val topAppBar = binding.topAppBar
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        topAppBar.setupWithNavController(navController, appBarConfiguration)
        topAppBar.setOnMenuItemClickListener { menuItem ->
            NavigationUI.onNavDestinationSelected(menuItem, navController)
            true
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addChangeUserGesture() {
        val gestureDetectorListener = object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                e1?.let {
                    if (abs(e2.y - e1.y) > 10) {
                        Toast.makeText(this@MainActivity, "User changed", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }
        }
        val gestureDetector = GestureDetectorCompat(this, gestureDetectorListener)

        binding.currentUserPhoto.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
        }
    }
}