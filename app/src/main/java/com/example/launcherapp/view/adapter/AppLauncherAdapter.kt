package com.example.launcherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.launcherapp.R
import com.example.launcherapp.event.AppLauncherCallback
import com.example.launcherapp.viewmodel.AppLauncherViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_app.view.*

class AppLauncherAdapter(var apps: List<AppLauncherViewModel.App>, private val clickCallback: AppLauncherCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class AppLauncherViewHolder(val view: View, private val clickCallback:AppLauncherCallback) : RecyclerView.ViewHolder(view) {
        fun bind(app:AppLauncherViewModel.App) {
            view.app_icon.setImageDrawable(null)

            when (app) {
                is AppLauncherViewModel.App.AppInfo -> {
                    // app card
                    view.app_card.setOnClickListener { clickCallback.onAppClicked(app) }

                    // app icon
                    view.app_icon.setImageDrawable(app.icon)

                    // app text
                    view.app_text.text = app.label
                }
            }

            view.app_text.isSelected = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppLauncherViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_app,parent,false),clickCallback)
    }

    override fun getItemCount() = apps.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            (holder as AppLauncherViewHolder).bind(apps[position])
        } catch (ex: Exception) {
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        // call base class implementation
        super.onViewRecycled(holder)

        // cancel any pending requests to load an image
        Picasso.get().cancelRequest((holder as AppLauncherViewHolder).view.app_icon)
    }


}