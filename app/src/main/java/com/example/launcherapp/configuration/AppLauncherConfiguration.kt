package com.example.launcherapp.configuration

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppLauncherConfiguration (
    @Expose @SerializedName("Apps") val apps:List<String>,
    @Expose @SerializedName("FloatApps") val floatApps:Boolean
)