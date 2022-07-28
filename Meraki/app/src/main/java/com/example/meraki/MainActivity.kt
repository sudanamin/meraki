package com.example.meraki

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.meraki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.courseFinished.setOnClickListener {
            displayNotification(notificationID_courseFinished)
        }

        binding.exerciseFinished.setOnClickListener {
            displayNotification(notificationID_exerciseFinished)
        }

        binding.exercisePassed.setOnClickListener {
            displayNotification(notificationID_exercisePassed)
        }



    }

    private fun displayNotification(notificationID : Int) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            createNotificaionChannel()
        }
        var notificationTitle = "asd"
        var notificationText = "xxx"


        when (notificationID) {
            1 -> {
                notificationTitle =  notificationID_1_title
                notificationText = notificationID_1_Text

            }
            2 -> {
                notificationTitle =  notificationID_2_title
                notificationText = notificationID_2_Text
            }
            3 -> {
                notificationTitle =  notificationID_3_title
                notificationText = notificationID_3_Text

            }
        }

        val notification = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .build()

        val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)



    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificaionChannel() {
        val name = "courses status"
        val desc = "notifications about student courses"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}