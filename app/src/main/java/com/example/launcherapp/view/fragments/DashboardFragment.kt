package com.example.launcherapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.launcherapp.databinding.FragmentDashboardBinding
import com.example.launcherapp.viewmodel.DashboardViewModel

class DashboardFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //inflate the view
        val binding = FragmentDashboardBinding.inflate(inflater,container,true)

        //set the view model
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        binding.viewmodel = viewModel as DashboardViewModel

        binding.lifecycleOwner = this
        return binding.root
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}