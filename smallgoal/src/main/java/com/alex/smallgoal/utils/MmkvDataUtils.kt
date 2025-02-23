package com.alex.smallgoal.utils

import android.util.Log
import com.alex.smallgoal.bean.GoalItem
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.reflect.TypeToken

/**
 *
 * @ProjectName: VRSAndroid
 * @Package: com.alex.vrsandroid.utils
 * @ClassName: MmkvDataUtils
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2024-11-30 10:38
 */
object MmkvDataUtils {
  private const val TAG = "MmkvDataUtils"

  private const val KEY_GOAL_LIST = "key_goal_list"

  fun addGoalList(goalItem: GoalItem) {
    val goalList = getGoalList().toMutableList()
    goalList.add(goalItem)
    saveGoalList(goalList)
  }

  fun saveGoalList(goalList: List<GoalItem>) {
    val jsonString = GsonUtils.toJson(goalList)
    MmkvUtils.getInstance().put(KEY_GOAL_LIST, jsonString)
  }

  fun getGoalList(): List<GoalItem> {
    val jsonString = MmkvUtils.getInstance().getString(KEY_GOAL_LIST, "")
    if (jsonString.isEmpty()) {
      return emptyList()
    }
    return try {
      val type = object : TypeToken<List<GoalItem>>() {}.type
      val list = GsonUtils.fromJson<List<GoalItem>>(jsonString, type)
      list
    } catch (e: Exception) {
      Log.e(TAG, "getGoalList parse error: ${e.message}")
      emptyList()
    }
  }

}