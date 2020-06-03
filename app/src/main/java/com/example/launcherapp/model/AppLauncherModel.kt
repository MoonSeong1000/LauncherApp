package com.example.launcherapp.model

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.launcherapp.AppLauncherDemo
import com.example.launcherapp.configuration.AppLauncherConfiguration
import com.example.launcherapp.event.interfaces.IEventHandler
import com.google.gson.Gson
import java.io.File
import java.lang.Exception

class AppLauncherModel : IEventHandler {
    val appLauncherApps = MutableLiveData<List<String>>(readAppLauncherConfigurationFromFile().apps)

    val appLauncherFloatApps = MutableLiveData<Boolean>(readAppLauncherConfigurationFromFile().floatApps)

    private fun readAppLauncherConfigurationFromFile() : AppLauncherConfiguration {
        var appLauncherConfiguration = AppLauncherConfiguration(emptyList(), false)

        try {
            val appLauncherFileConfiguration = File(
                AppLauncherDemo.context.getExternalFilesDir("tablet")
                    ?.absolutePath, "applauncher.json"
            ).readText()

            if (!TextUtils.isEmpty(appLauncherFileConfiguration)) {
                appLauncherConfiguration = Gson().fromJson<AppLauncherConfiguration>(
                    appLauncherFileConfiguration, AppLauncherConfiguration::class.java
                )
            }
        } catch (ex: Exception) {

        } finally {

        }

        return appLauncherConfiguration
    }

}