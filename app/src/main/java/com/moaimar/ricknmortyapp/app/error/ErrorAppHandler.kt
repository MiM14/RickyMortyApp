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
import com.moaimar.ricknmortyapp.app.extensions.navController
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ErrorAppHandler @Inject constructor(@ActivityContext private val context: Context) {


    fun navigateToError(errorApp: ErrorApp) {
        val dataErrorMessage = context.getString(R.string.data_error_message)
        val internetErrorMessage = context.getString(R.string.no_internet_error_message)
        val timeOutErrorMessage = context.getString(R.string.no_time_out_error_message)
        val unknownErrorMessage = context.getString(R.string.no_unknown_error_message)
        val notFoundErrorMessage = context.getString(R.string.not_found_error_message)

        when (errorApp) {
            is ErrorApp.DataLayerError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError(dataErrorMessage))
            is ErrorApp.NoInternetError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError(internetErrorMessage))
            is ErrorApp.TimeOutError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError(timeOutErrorMessage))
            is ErrorApp.NotFoundError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError(notFoundErrorMessage))
            else -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.toError(unknownErrorMessage))
        }}
}