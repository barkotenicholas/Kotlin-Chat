package com.example.kotlinchat

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.example.kotlinchat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get NavHost and NavController
        val navHostFrag = supportFragmentManager.findFragmentById(binding.navHostFrag.id) as NavHostFragment
        navController = navHostFrag.navController

        // Get AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Link ActionBar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        val autoReplyTime = sharedPref.getString(getString(R.string.key_auto_reply_time),"")

        val publicInfo : Set<String>? = sharedPref.getStringSet(getString(R.string.key_public_info),null)


        Log.i("Pref", "Auto Reply Time: $autoReplyTime")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        if(key == getString(R.string.key_status)) {
            val newStatus = sharedPreferences?.getString(key, "")
            Toast.makeText(this, "New status is $newStatus", Toast.LENGTH_SHORT).show()
        }

        if(key == getString(R.string.key_auto_reply)){
            val stat = sharedPreferences?.getBoolean(key, false)

            if(stat!!){
                Toast.makeText(this, "Auto Reply On", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Auto Reply Off", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)

    }
}
