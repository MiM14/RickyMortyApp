package com.moaimar.ricknmortyapp.app.error

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.moaimar.ricknmortyapp.MainActivity
import com.moaimar.ricknmortyapp.NavGraphDirections
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ErrorAppHandler @Inject constructor(@ActivityContext private val context: Context): ErrorFragment() {


    fun navigateToError(errorApp: ErrorApp) {
        when (errorApp) {
            is ErrorApp.DataLayerError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError(getString(R.string.data_error_message)))
            is ErrorApp.NoInternetError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError())
            is ErrorApp.TimeOutError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError())
            else -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError())
        }}

    fun Activity.navController(): NavController =
        Navigation.findNavController(this, R.id.fragment_container_view)
}