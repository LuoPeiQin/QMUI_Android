package com.alex.smallgoal

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.alex.smallgoal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initTopBar()
//    binding.fab.setOnClickListener { view ->
//      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//        .setAction("Action", null)
//        .setAnchorView(R.id.fab).show()
//    }
  }

  private fun initTopBar() {
    binding.topbar.addLeftBackImageButton().setOnClickListener { finish() }
    binding.topbar.setTitle("2025目标")
  }

}