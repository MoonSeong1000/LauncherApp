package com.example.launcherapp.viewmodel

import androidx.navigation.ActionOnlyNavDirections
import com.example.launcherapp.R

class DashboardViewModel : BaseViewModel() {
    fun handleActionShowAppLauncher() {
        navigate(ActionOnlyNavDirections(R.id.action_dashboard_to_app_launcher))
    }
}