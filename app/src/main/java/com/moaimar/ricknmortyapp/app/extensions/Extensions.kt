package com.moaimar.ricknmortyapp.app.extensions

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.moaimar.ricknmortyapp.R

fun Activity.navController(): NavController =
    Navigation.findNavController(this, R.id.fragment_container_view)