package com.example.launcherapp.viewmodel

import android.content.Intent
import android.content.Intent.CATEGORY_LAUNCHER
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.launcherapp.R
import com.example.launcherapp.event.AppLauncherCallback
import com.example.launcherapp.model.AppLauncherModel
import java.lang.Exception
import com.example.launcherapp.AppLauncherDemo.Companion.context as AppContext

class AppLauncherViewModel : ViewModel(), AppLauncherCallback {

    private val dataModel = AppLauncherModel()

    val appLauncherApps = MediatorLiveData<List<App.AppInfo>>().apply {
        addSource(dataModel.appLauncherApps) {
            value = extractAppLaunchInfo(it)
        }
    }

    val appLauncherLabel = MediatorLiveData<String>().apply {
        addSource(dataModel.appLauncherApps) {
            value = AppContext.getString(R.string.dialog_app_launcher_apps_available, it?.size ?: 0)
        }
    }

    init {
        dataModel.register()
    }

    sealed class App {
        abstract val label: String

        data class AppInfo(
            val packageName: String, override val label: String,
            val icon: Drawable, val launchIntent: Intent?
        ) : App()
    }

    private fun extractAppLaunchInfo(appPackage: List<String>?): List<App.AppInfo> {
        val apps = mutableListOf<App.AppInfo>()

        try {
            appPackage?.apply {
                distinctBy { it }.forEach { appPackage ->
                    try {
                        val packageManager = AppContext.packageManager

                        val appInfo = packageManager.getApplicationInfo(
                            appPackage,
                            PackageManager.GET_META_DATA
                        )

                        val appLabel = packageManager.getApplicationLabel(appInfo)

                        val appIcon = packageManager.getApplicationIcon(appInfo)

                        val appLaunchIntent = packageManager.getLaunchIntentForPackage(appPackage)
                        appLaunchIntent?.addCategory(CATEGORY_LAUNCHER)

                        apps.add(
                            App.AppInfo(
                                appPackage,
                                appLabel.toString(),
                                appIcon,
                                appLaunchIntent
                            )
                        )
                    } catch (nameNotFoundException: PackageManager.NameNotFoundException) {
                    }
                }
            }
        } catch (ex: Exception) {

        }

        return apps
    }

    override fun onAppClicked(appInfo: App.AppInfo) {
        TODO("Not yet implemented")
    }
}
