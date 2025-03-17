package com.example.alarmclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private lateinit var timePicker: TimePicker
    private lateinit var btnSetAlarm: Button

    private val CHANNEL_ID = "alarm_notification_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View
        timePicker = findViewById(R.id.timePicker)
        btnSetAlarm = findViewById(R.id.btnSetAlarm)

        // Xử lý khi nhấn nút Đặt báo thức
        btnSetAlarm.setOnClickListener {
            setAlarm()
        }
    }

    private fun setAlarm() {
        val hour = timePicker.hour
        val minute = timePicker.minute
        val message = "Dậy đi học nào!"

        // Tạo intent đặt báo thức
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minute)
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
        }

        // Kiểm tra nếu có ứng dụng báo thức trên máy
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            Toast.makeText(this, "Báo thức đã được đặt lúc $hour:$minute", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Không tìm thấy ứng dụng báo thức", Toast.LENGTH_SHORT).show()
        }
    }
}
