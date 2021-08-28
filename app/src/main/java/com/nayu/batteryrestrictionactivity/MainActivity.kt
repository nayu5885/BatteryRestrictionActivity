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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)

        var dataStore = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        var num = dataStore.getInt("input", STATE_BEFORE_START)


        when(num){
            STATE_BEFORE_START ->{
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
              //  finish()
            }

            STATE_STARTED ->{
                val batteryStatus = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
                    this.registerReceiver(null, it)
                }

                val batteryPct: Float = batteryStatus?.let { intent ->
                    val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                    level * 100 / scale.toFloat()
                } ?: return

                val dataStore2: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
                val editor = dataStore2.edit()
                editor.putInt("limit", batteryPct.toInt())
                editor.apply()

                var limit = dataStore2.getInt("limit", -1).toFloat()

                if (batteryPct == limit){
                    val intent = Intent(this, ClearActivity::class.java)
                    startActivity(intent)
                   // finish()
                }else if (limit < batteryPct){
                    val intent = Intent(this, SafeActivity::class.java)
                    startActivity(intent)
                   // finish()
                }else if(limit > batteryPct){
                    val intent = Intent(this, OutActivity::class.java)
                    startActivity(intent)
                    //finish()
                }
            }



        }

   }

}
