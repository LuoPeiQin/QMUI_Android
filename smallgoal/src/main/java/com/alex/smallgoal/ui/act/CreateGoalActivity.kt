package com.alex.smallgoal.ui.act

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alex.smallgoal.R
import com.alex.smallgoal.bean.GoalItem
import com.alex.smallgoal.databinding.ActivityCreateGoalBinding
import com.alex.smallgoal.utils.MmkvDataUtils
import com.blankj.utilcode.util.ToastUtils

class CreateGoalActivity : AppCompatActivity(), View.OnClickListener {

  private val TAG = "CreateGoalActivity"

  private lateinit var binding: ActivityCreateGoalBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityCreateGoalBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initTopBar()

    binding.btnAddGoal.setOnClickListener(this)
  }

  private fun initTopBar() {
    binding.topbar.addLeftBackImageButton().setOnClickListener { finish() }
    binding.topbar.setTitle("添加目标")
  }

  private fun addGoal() {
    val goalTitle = binding.etGoalTitle.text.toString()
    val goalNumber = binding.etGoalNumber.text.toString().toIntOrNull()
    if (goalTitle.isEmpty()) {
      ToastUtils.showShort("请输入目标")
      return
    }
    if (goalNumber == null) {
      ToastUtils.showShort("请输入目标数量")
      return
    }
    val goalItem = GoalItem(goalTitle, goalNumber, 0)
    Log.d(TAG, "addGoal: ")
    MmkvDataUtils.addGoalList(goalItem)

    ToastUtils.showShort("添加成功")
    finish()
  }

  override fun onClick(v: View) {
    when(v.id) {
      R.id.btnAddGoal -> {
        addGoal()
      }
    }
  }

}