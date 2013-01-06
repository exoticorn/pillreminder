package de.exoticorn.android

import android.app.Activity

trait ActivitySugar extends Activity {
  def viewById[T](id: Int) = findViewById(id).asInstanceOf[T]
}