package com.example.launcherapp

import android.app.Application
import android.content.Context

class AppLauncherDemo : Application() {
    companion object {
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}