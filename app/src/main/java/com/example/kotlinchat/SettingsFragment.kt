package com.example.kotlinchat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.kotlinchat.databinding.FragmentSettingsBinding


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)

        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingsPref?.setOnPreferenceClickListener {

            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        val statusPref = findPreference<Preference>(getString(R.string.key_status))


        //Read the preference

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val autoReplyTime = sharedPref.getString(getString(R.string.key_auto_reply_time),"")

        Log.i("MainActivity", "Auto Reply Time: $autoReplyTime")

        val autoDownload = sharedPref.getBoolean(getString(R.string.key_auto_download),false)

        Log.i("MainActivity", "Auto download: $autoDownload")

        statusPref?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->

                val newStatus = newValue as String

                if(newStatus.contains("bad")){
                    Toast.makeText(context,"Inappropriate status. Please follow community guidelines",Toast.LENGTH_SHORT).show()
                     false}
                else {
                    true
                }
            }

        val notif = findPreference<Preference>(getString(R.string.key_new_msg_notif))
        notif?.summaryProvider = Preference.SummaryProvider<SwitchPreference> {
            if(it.isChecked){
                "Status: On"
            }else{
                "Status: off"
            }
        }
    }


}