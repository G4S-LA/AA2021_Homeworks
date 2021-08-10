package com.example.lesson10.background

import android.content.Context
import androidx.work.*
import com.example.lesson10.App
import com.example.lesson10.App.Companion.SYNC_ID
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SyncMoviesWorker(context: Context, workerParameters: WorkerParameters) :
        Worker(context, workerParameters) {

    override fun doWork(): Result {
        return try {
            tryToSync()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        private val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        private val periodicWorkRequest =
                PeriodicWorkRequest.Builder(SyncMoviesWorker::class.java, 8, TimeUnit.HOURS)
                        .setInitialDelay(1, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build()

        fun sync(context: Context) {
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                    SYNC_ID,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    periodicWorkRequest
            )
        }
    }

    private fun tryToSync() = CoroutineScope(Dispatchers.IO).launch {
        App.synchronizer.sync()
    }
}