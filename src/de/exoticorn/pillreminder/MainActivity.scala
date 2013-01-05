package de.exoticorn.pillreminder

import android.os.Bundle
import android.app.Activity
import android.view.Menu
import android.widget.TextView

class MainActivity extends Activity {
    override protected def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.hello_label).asInstanceOf[TextView].setText("Some other text!")
    }

    override def onCreateOptionsMenu(menu: Menu) = {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu)
        true
    }
    
}
