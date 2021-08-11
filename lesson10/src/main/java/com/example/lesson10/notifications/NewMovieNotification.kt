package com.example.lesson10.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.lesson10.R
import com.example.lesson10.data.db.entities.MovieEntity
import com.example.lesson10.ui.main.MainActivity
import java.util.*

class NewMovieNotification: Notification {
    private val channelId = "NEW_MOVIE"
//    private val tag = "NEW_MOVIE"

    override fun show(context: Context, movie: MovieEntity?) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        movie?.run {
            createNotificationChannel(context)
            Log.v("-§-------", movie.toString())
            val notification = NotificationCompat.Builder(context, channelId)
                    .setContentTitle(title)
                    .setContentText(context.getString(R.string.new_movie_notification_text))
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
            Log.v("-§-------", "Build")
            NotificationManagerCompat.from(context).notify(4, notification)
            Log.v("-§-------", "Done")
        }
    }

    fun createNotificationChannel(context: Context) {

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