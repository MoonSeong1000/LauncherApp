package com.example.launcherapp.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions:NavDirections) : NavigationCommand()
    object Up : NavigationCommand()
}