package de.exoticorn.pillreminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.app.PendingIntent
import android.app.NotificationManager

class AlarmReceiver extends BroadcastReceiver {
  def onReceive(context: Context, intent: Intent) {
    Log.d("AlarmReceiver", "received alarm")
    val notificationBuilder =
      new NotificationCompat.Builder(context)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("Pille!")
        .setContentText("Es ist Zeit!")

    val notificationIntent = new Intent(context, classOf[MainActivity])
    val stackBuilder = TaskStackBuilder.create(context)
    stackBuilder.addParentStack(classOf[MainActivity])
    stackBuilder.addNextIntent(notificationIntent)
    val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT)
    notificationBuilder.setContentIntent(pendingIntent)
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE).asInstanceOf[NotificationManager]
    notificationManager.notify(0, notificationBuilder.build())
  }
}