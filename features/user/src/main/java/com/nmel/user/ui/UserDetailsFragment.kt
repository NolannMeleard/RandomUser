package com.nmel.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.navArgs
import com.nmel.core_ui.ParentFragmentWithBinding
import com.nmel.core_ui.composable.BackButton
import com.nmel.core_ui.theme.RandomUserTheme
import com.nmel.user.databinding.FragmentUserDetailsBinding
import com.nmel.user.models.local.User
import com.nmel.user.ui.details.ContactInformation
import com.nmel.user.ui.details.PersonalInformation
import com.nmel.user.ui.details.UserHeader
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@AndroidEntryPoint
class UserDetailsFragment : ParentFragmentWithBinding<FragmentUserDetailsBinding>() {
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.composeView.apply {
            defineContentOfComposeView(this) {
                RandomUserTheme {
                    UserDetailsScreen(
                        user = args.user,
                        onBackClick = {
                            navigateUp()
                        }
                    )
                }
            }
        }
        return binding.root
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserDetailsBinding {
        return FragmentUserDetailsBinding.inflate(inflater, container, false)
    }
}





