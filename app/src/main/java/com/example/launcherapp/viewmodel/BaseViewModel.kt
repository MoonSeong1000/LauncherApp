package com.example.launcherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.launcherapp.navigation.NavigationCommand
import com.example.launcherapp.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel(){
    private val _navigationCommand : SingleLiveEvent<NavigationCommand> = SingleLiveEvent()

    val navigationCommand: LiveData<NavigationCommand>
        get() = _navigationCommand

    fun navigate(navDirections:NavDirections) {
        _navigationCommand.postValue(NavigationCommand.To(navDirections))
    }


}