package com.example.launcherapp.event

import com.example.launcherapp.viewmodel.AppLauncherViewModel

interface AppLauncherCallback {
    fun onAppClicked(appInfo: AppLauncherViewModel.App.AppInfo)
}