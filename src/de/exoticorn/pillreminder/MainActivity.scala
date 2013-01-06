package de.exoticorn.pillreminder

import android.os.Bundle
import android.app.Activity
import android.view.Menu
import android.widget.TextView
import de.exoticorn.android.ActivitySugar
import android.content.Context
import android.app.AlarmManager
import android.content.Intent
import android.app.PendingIntent
import android.util.Log
import android.app.NotificationManager
import java.util.Calendar
import android.view.View

class MainActivity extends Activity with ActivitySugar {
  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val alarmManager = getSystemService(Context.ALARM_SERVICE).asInstanceOf[AlarmManager]
    val alarmIntent = new Intent(this, classOf[AlarmReceiver])
    val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
    alarmManager.set(AlarmManager.RTC, nextAlarmTime, pendingIntent)
  }

  override def onCreateOptionsMenu(menu: Menu) = {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_main, menu)
    true
  }

  def takenButtonClicked(view: View) {
    getSystemService(Context.NOTIFICATION_SERVICE).asInstanceOf[NotificationManager].cancelAll()
  }

  private def nextAlarmTime: Long = {
    val nowCal = Calendar.getInstance()
    val targetCal = nowCal.clone().asInstanceOf[Calendar]
    targetCal.set(Calendar.HOUR_OF_DAY, 10)
    targetCal.set(Calendar.MINUTE, 0)
    targetCal.set(Calendar.SECOND, 0)
    targetCal.set(Calendar.MILLISECOND, 0)
    if (targetCal.before(nowCal)) {
      targetCal.add(Calendar.DAY_OF_YEAR, 1)
    }
    targetCal.getTimeInMillis()
  }
}
