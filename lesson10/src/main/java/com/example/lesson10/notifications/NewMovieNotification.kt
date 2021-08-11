package com.example.lesson10.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.lesson10.App.Companion.PATH
import com.example.lesson10.App.Companion.SCHEMA
import com.example.lesson10.R
import com.example.lesson10.data.db.entities.MovieEntity
import com.example.lesson10.ui.main.MainActivity
import java.util.*

class NewMovieNotification: Notification {
    private val channelId = "NEW_MOVIE"

    override fun show(context: Context, movie: MovieEntity?) {
        val uri = "$SCHEMA$PATH${movie?.id}".toUri()
        val intent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            data = uri
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        movie?.run {
            createNotificationChannel(context)
            val notification = NotificationCompat.Builder(context, channelId)
                    .setContentTitle(context.getString(R.string.new_movie_notification_text))
                    .setContentText(title)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setWhen(Calendar.getInstance().timeInMillis)
                    .setAutoCancel(true)
                    .setLargeIcon(Glide.with(context)
                            .asBitmap()
                            .load(imageUrl)
                            .submit()
                            .get())
                    .build()
            NotificationManagerCompat.from(context).notify(4, notification)
        }
    }

    private fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                    channelId,
                    "General Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            mChannel.description = "This is default channel used for all other notifications"

            val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}