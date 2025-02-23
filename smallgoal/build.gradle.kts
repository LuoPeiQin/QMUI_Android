import com.qmuiteam.plugin.Dep

plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
}

android {
  namespace = "com.alex.smallgoal"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.alex.smallgoal"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(Dep.AndroidX.appcompat)
  implementation(Dep.AndroidX.annotation)
  implementation(Dep.AndroidX.activity)
  implementation(Dep.MaterialDesign.material)
  implementation(Dep.ButterKnife.butterknife)
  implementation(Dep.Compose.activity)
  implementation(Dep.Compose.constraintlayout)
  kapt(Dep.ButterKnife.compiler)
  implementation(project(":lib"))
  implementation(project(":qmui"))
  implementation(project(":arch"))
  implementation(project(":type"))
  implementation(project(":compose"))
  implementation(project(":photo"))
  implementation(project(":photo-coil"))
  implementation(project(":photo-glide"))
  implementation(project(":editor"))
  implementation(Dep.Flipper.soLoader)
  implementation(Dep.Flipper.flipper)
  kapt(project(":compiler"))
  kapt(project(":arch-compiler"))
  kapt(Dep.Glide.compiler)

  implementation("com.iqiyi.xcrash:xcrash-android-lib:3.1.0")
}