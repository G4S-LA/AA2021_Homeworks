package com.example.lesson10.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SwitchCompat
import androidx.work.WorkManager
import com.example.lesson10.App.Companion.PREFERENCE_SYNC
import com.example.lesson10.App.Companion.SYNC_ID
import com.example.lesson10.R
import com.example.lesson10.background.SyncMoviesWorker
import com.example.lesson10.ui.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity() {
    private val syncSwitcher by lazy { findViewById<SwitchCompat>(R.id.switch_sync) }
    private val pref by lazy { getSharedPreferences(PREFERENCE_SYNC, MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList())
                .commit()
        }

        syncSwitcher.setOnClickListener {
            when (syncSwitcher.isChecked){
                true -> SyncMoviesWorker.sync(applicationContext)
                else -> WorkManager.getInstance(applicationContext).cancelUniqueWork(SYNC_ID)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        syncSwitcher.isChecked = pref.getBoolean(SYNC_ID, false)
    }

    override fun onStop() {
        pref.edit().putBoolean(SYNC_ID,syncSwitcher.isChecked).apply()
        super.onStop()
    }
}