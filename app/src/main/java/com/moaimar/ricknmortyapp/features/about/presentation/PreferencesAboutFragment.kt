package com.moaimar.ricknmortyapp.features.about.presentation

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.moaimar.ricknmortyapp.BuildConfig
import com.moaimar.ricknmortyapp.R

class PreferencesAboutFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_fragment_about, rootKey)
        setUpAbout()
    }

    private fun setUpAbout() {
        val pref: Preference? = findPreference("app_version")
        pref?.summary = BuildConfig.VERSION_NAME
    }
}