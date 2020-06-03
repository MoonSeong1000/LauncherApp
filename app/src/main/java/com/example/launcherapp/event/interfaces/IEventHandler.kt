package com.example.launcherapp.event.interfaces

import org.greenrobot.eventbus.EventBus
import java.lang.Exception

interface IEventHandler {
    fun register() {
        try {
            if(!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        } catch(ex:Exception) {

        }
    }

    fun unregister() {
        try {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        } catch (ex: Exception) {

        }
    }
}