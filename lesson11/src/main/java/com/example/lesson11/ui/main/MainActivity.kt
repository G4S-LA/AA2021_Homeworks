package com.example.lesson11.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SwitchCompat
import androidx.work.WorkManager
import com.example.lesson11.App.Companion.PREFERENCE_SYNC
import com.example.lesson11.App.Companion.SYNC_ID
import com.example.lesson11.R
import com.example.lesson11.background.SyncMoviesImpl
import com.example.lesson11.background.SyncMoviesWorker
import com.example.lesson11.ui.moviesdetails.FragmentMoviesDetails
import com.example.lesson11.ui.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity() {
    private val syncSwitcher by lazy { findViewById<SwitchCompat>(R.id.switch_sync) }
    private val pref by lazy { getSharedPreferences(PREFERENCE_SYNC, MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            Log.v("§", "Main Activity NULL")
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FragmentMoviesList())
                    .commit()
            intent?.let(::handleIntent)
        }

        syncSwitcher. setOnClickListener {
            when (syncSwitcher.isChecked) {
                true -> SyncMoviesWorker.sync(applicationContext)
                else -> WorkManager.getInstance(applicationContext).cancelUniqueWork(SYNC_ID)
            }
        }
        Log.v("§", "Main Activity")
    }

    override fun onStart() {
        super.onStart()
        syncSwitcher.isChecked = pref.getBoolean(SYNC_ID, false)
    }

    override fun onStop() {
        pref.edit().putBoolean(SYNC_ID, syncSwitcher.isChecked).apply()
        super.onStop()
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val movieId = intent.data?.lastPathSegment?.toIntOrNull()
                movieId?.run {
                    openMovie(movieId)
                }
            }
        }
    }

    private fun openMovie(movieId: Int) {
        val fragment = FragmentMoviesDetails()
        fragment.arguments = Bundle().apply { putInt(FragmentMoviesList.MOVIE_ID, movieId) }
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack("123")
    }

}