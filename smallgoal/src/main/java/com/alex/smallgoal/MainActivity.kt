package com.alex.smallgoal

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex.smallgoal.bean.TargetItem
import com.alex.smallgoal.databinding.ActivityMainBinding
import com.alex.smallgoal.ui.adapter.TargetAdapter

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val items = mutableListOf<TargetItem>()
  private val mAdapter = TargetAdapter(items)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initTopBar()
    initList()
    initTestData()
  }

  private fun initTestData() {
    items.add(TargetItem("俯卧撑20000个", 20000, 200))
    items.add(TargetItem("跑步800公里", 800, 200))
    items.add(TargetItem("游泳15000米", 15000, 200))
    items.add(TargetItem("仰卧起做20000个", 20000, 200))
    mAdapter.notifyDataSetChanged()
  }

  private fun initList() {
    binding.rvMainGoal.addOnItemTouchListener(
      object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
          when (e.action) {
            MotionEvent.ACTION_DOWN -> {
              rv.findChildViewUnder(e.x, e.y)?.apply {
                animate().scaleX(0.98f).scaleY(0.98f).setDuration(200).start()
              }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
              rv.findChildViewUnder(e.x, e.y)?.apply {
                animate().scaleX(1f).scaleY(1f).setDuration(200).start()
              }
            }
          }
          return false
        }
      }
    )

    binding.rvMainGoal.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = mAdapter
    }
  }

  private fun initTopBar() {
    binding.topbar.addRightImageButton(R.mipmap.add, R.id.topbar_right_change_button).setOnClickListener { finish() }
    binding.topbar.setTitle("2025目标")
  }

}