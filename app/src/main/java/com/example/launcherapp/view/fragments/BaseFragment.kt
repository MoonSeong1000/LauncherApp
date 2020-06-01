package com.example.launcherapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.launcherapp.navigation.NavigationCommand
import com.example.launcherapp.viewmodel.BaseViewModel
import java.lang.Exception

abstract class BaseFragment : Fragment() {
    protected lateinit var viewModel:BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            super.onActivityCreated(savedInstanceState)

            //viewModel의 navigationCommand의 변화를 감지를 한다.
            //감지가 되면 Observer안의 내용을 실행한다.
            viewModel.navigationCommand.observe(this, Observer { navigationCommand ->
                when (navigationCommand) {
                    is NavigationCommand.To -> {
                        findNavController().navigate(navigationCommand.directions)
                    }
                    is NavigationCommand.Up -> {
                        findNavController().navigateUp()
                    }
                }
            })
        } catch(ex:Exception) {

        } finally {

        }
    }
}