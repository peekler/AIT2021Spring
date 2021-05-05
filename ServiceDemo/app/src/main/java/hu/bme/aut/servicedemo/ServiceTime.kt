package hu.bme.aut.servicedemo

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.util.*

class ServiceTime : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    var enabled = false

    inner class MyTimeThread : Thread() {
        override fun run() {
            val handlerMain = Handler(Looper.getMainLooper())

            while (enabled) {
                handlerMain.post {
                    Toast.makeText(this@ServiceTime, Date(System.currentTimeMillis()).toString(), Toast.LENGTH_SHORT).show()
                }

                updateNotification(Date(System.currentTimeMillis()).toString())

                sleep(5000)
            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        enabled = true
        MyTimeThread().start()

        startForeground(NOTIF_FOREGROUND_ID, getMyNotification("Demo"))

        return START_STICKY
    }


    private val NOTIFICATION_CHANNEL_ID = "time_service_notifications"
    private val NOTIFICATION_CHANNEL_NAME = "Time Service notifications"
    private val NOTIF_FOREGROUND_ID = 101

    private fun updateNotification(text: String) {
        val notification = getMyNotification(text)
        val notifMan = getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notifMan?.notify(NOTIF_FOREGROUND_ID, notification)
    }

    private fun getMyNotification(text: String): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this,
            NOTIF_FOREGROUND_ID,
            notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT)

        return NotificationCompat.Builder(
            this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("This the MyTimeService")
            .setContentText(text)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setVibrate(longArrayOf(1000, 2000, 1000))
            .setContentIntent(contentIntent).build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        stopForeground(false)
        enabled = false
        super.onDestroy()
    }

}