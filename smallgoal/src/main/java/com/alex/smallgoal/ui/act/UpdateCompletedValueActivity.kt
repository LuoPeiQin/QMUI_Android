package com.alex.smallgoal.ui.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alex.smallgoal.R
import com.alex.smallgoal.bean.GoalItem
import com.alex.smallgoal.databinding.ActivityCreateGoalBinding
import com.alex.smallgoal.databinding.ActivityUpdateCompletedValueBinding
import com.alex.smallgoal.utils.MmkvDataUtils
import com.blankj.utilcode.util.ToastUtils

class UpdateCompletedValueActivity : AppCompatActivity(), View.OnClickListener {

  private val TAG = "UpdateCompletedValueActivity"
  private val curGoalItemId by lazy {
    intent.getStringExtra(KEY_GOAL_ITEM_ID)
  }
  private var curGoalItem: GoalItem? = null
  private val goalList by lazy {
    MmkvDataUtils.getGoalList()
  }

  private lateinit var binding: ActivityUpdateCompletedValueBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityUpdateCompletedValueBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initTopBar()

    binding.btnUpdateGoal.setOnClickListener(this)

    initData()
  }

  private fun initData() {
    goalList.forEach {
      if (it.id == curGoalItemId) {
        curGoalItem = it
      }
    }
    binding.tvGoalTitle.text = curGoalItem?.title
    binding.tvGoalNumber.text = "${curGoalItem?.target}"
    binding.tvCompletedValue.text = "${curGoalItem?.completedValue}"
  }

  private fun updateGoal() {
    val completedThisTimeCount = binding.etCompletedThisTimeCount.text.toString().toIntOrNull()
    if (completedThisTimeCount == null) {
      ToastUtils.showShort("请输入本次完成数量")
      return
    }
    curGoalItem?.let {
      val newCount = it.completedValue + completedThisTimeCount
      it.completedValue = newCount
      Log.d(TAG, "updateGoal: curGoalItem = $curGoalItem")
      MmkvDataUtils.saveGoalList(goalList)
      ToastUtils.showShort("更新成功")
      finish()
    } ?: kotlin.run {
      ToastUtils.showShort("更新失败")
    }
  }

  override fun onClick(v: View) {
    when(v.id) {
      R.id.btnUpdateGoal -> {
        updateGoal()
      }
    }
  }

  private fun initTopBar() {
    binding.topbar.addLeftBackImageButton().setOnClickListener { finish() }
    binding.topbar.setTitle("更新完成进度")
  }

  companion object {
    private const val KEY_GOAL_ITEM_ID = "key_goal_item_id"

    fun startActivity(context: Context, goalItemId: String) {
      val intent = Intent(context, UpdateCompletedValueActivity::class.java)
      intent.putExtra(KEY_GOAL_ITEM_ID, goalItemId)
      context.startActivity(intent)
    }
  }
}