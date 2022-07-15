package com.example.kotlinchat

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class AccountSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.account_settings,rootKey)



    }

}