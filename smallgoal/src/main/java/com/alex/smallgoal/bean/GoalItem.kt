package com.alex.smallgoal.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class GoalItem(
  val title: String,
  val target: Int,
  var completedValue: Int,
) : Parcelable {
  val id: String = UUID.randomUUID().toString()
}