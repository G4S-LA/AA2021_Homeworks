package com.example.lesson9.background

import android.content.Context
import androidx.work.*
import com.example.lesson9.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
                    "sync",
                    ExistingPeriodicWorkPolicy.REPLACE,
                    periodicWorkRequest
            )
        }
    }

    private fun tryToSync() = GlobalScope.launch {
        App.synchronizer.sync()
    }
}