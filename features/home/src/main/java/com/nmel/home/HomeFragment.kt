package com.nmel.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.fragment.app.viewModels
import com.nmel.core_ui.ParentFragmentWithBinding
import com.nmel.core_ui.theme.RandomUserTheme
import com.nmel.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@AndroidEntryPoint
class HomeFragment : ParentFragmentWithBinding<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel.getUsers()
        binding.composeView.apply {
            defineContentOfComposeView(this) {
                RandomUserTheme {
                    Text(text = "Hello ${isSystemInDarkTheme()}")
                }
            }
        }
        return binding.root
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
}
