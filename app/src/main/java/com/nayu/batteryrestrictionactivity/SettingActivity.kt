package com.nayu.batteryrestrictionactivity

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText

class SettingActivity : AppCompatActivity() {
   // @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val startButton: Button = findViewById(R.id.StartButton)
        val numberEditText: EditText = findViewById(R.id.numberEditText)
      //  val messageEditText: EditText = findViewById(R.id.messageEditText)

       //

       startButton.setOnClickListener {
          // var dataStore: SharedPreferences = getSharedPreferences("Datastore", Context.MODE_PRIVATE)
           val settingNumberText = numberEditText.text.toString()
           var num = STATE_STARTED
           var dataStore = PreferenceManager.getDefaultSharedPreferences(applicationContext)
           val editor = dataStore.edit()
           editor.putInt("input",num)
           editor.apply()
           finish()
       }

    }
}