/**
 * Copyright (C), 2023-2025, luo
 * FileName: GoalApplication
 * Author: alex.luo
 * Date: 2025-02-23 21:40
 * Description: 用一句话描述下
 */
package com.alex.smallgoal

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 *
 * @ProjectName: QMUI_Android
 * @Package: com.alex.smallgoal
 * @ClassName: GoalApplication
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2025-02-23 21:40
 */
class GoalApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    initLibrary()
  }

  private fun initLibrary() {
    MMKV.initialize(this)
  }
}
