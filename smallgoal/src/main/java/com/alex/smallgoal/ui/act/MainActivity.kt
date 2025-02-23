package com.alex.smallgoal.ui.act

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex.smallgoal.R
import com.alex.smallgoal.bean.GoalItem
import com.alex.smallgoal.databinding.ActivityMainBinding
import com.alex.smallgoal.ui.adapter.GoalListAdapter
import com.alex.smallgoal.utils.MmkvDataUtils

class MainActivity : AppCompatActivity() {

  private val TAG = "MainActivity"

  private lateinit var binding: ActivityMainBinding
  private val items = mutableListOf<GoalItem>()
  private val mAdapter = GoalListAdapter(items)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initTopBar()
    initList()
  }

  override fun onResume() {
    super.onResume()
    initRealList()
  }

  private fun initRealList() {
    val goalList = MmkvDataUtils.getGoalList()
    Log.d(TAG, "initRealList: goalList = $goalList")
    items.clear()
    items.addAll(goalList)
    mAdapter.notifyDataSetChanged()
  }

  private fun initTestData() {
    items.add(GoalItem("俯卧撑20000个", 20000, 200))
    items.add(GoalItem("跑步800公里", 800, 200))
    items.add(GoalItem("游泳15000米", 15000, 200))
    items.add(GoalItem("仰卧起做20000个", 20000, 200))
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
    binding.topbar.addRightImageButton(R.mipmap.add, R.id.topbar_right_change_button).setOnClickListener {
      startActivity(Intent(this, CreateGoalActivity::class.java))
    }
    binding.topbar.setTitle("2025目标")
  }

}