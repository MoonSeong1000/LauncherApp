package com.example.launcherapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.launcherapp.R
import com.example.launcherapp.databinding.DialogAppLauncherBinding
import com.example.launcherapp.view.adapter.AppLauncherAdapter
import com.example.launcherapp.viewmodel.AppLauncherViewModel

class AppLauncherDialog : DialogFragment() {
    private lateinit var viewModel: AppLauncherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val binding = DialogAppLauncherBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this).get(AppLauncherViewModel::class.java)
        binding.viewmodel = viewModel

        configureRecyclerView(binding)
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun configureRecyclerView(binding:DialogAppLauncherBinding) {
        binding.appContainer.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = AppLauncherAdapter(listOf(), viewModel)

            viewModel.appLauncherApps.observe(this@AppLauncherDialog.viewLifecycleOwner, Observer<List<AppLauncherViewModel.App>> {
                try {
                    layoutManager = if (it.size <= 3) {
                        GridLayoutManager(context, Math.max(1, it.size))
                    } else {
                        GridLayoutManager(context, 3)
                    }


                    (adapter as AppLauncherAdapter).apply {
                        notifyDataSetChanged()
                    }
                } catch (ex: Exception) {

                }
            })


        }
    }
}