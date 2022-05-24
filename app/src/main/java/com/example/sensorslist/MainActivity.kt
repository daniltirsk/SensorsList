package com.example.sensorslist

import android.hardware.Sensor
import android.hardware.SensorManager
import android.icu.lang.UCharacter.getName
import android.icu.lang.UScript.getName
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.sensorslist.databinding.ActivityMainBinding
import android.widget.AdapterView
import android.widget.ListView
import java.lang.Character.getName


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner: Spinner = findViewById(R.id.spinner)

        val listView: ListView = findViewById(R.id.sensors_list)
        var listItems = mutableListOf<String>("Empty")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

        val Health = mutableListOf<Int>(34,31,21)
        val Position = mutableListOf<Int>(10,18,19,29,17,8,30,36,16,4,9,20,15,11,35,1)
        val Environment = mutableListOf<Int>(12,6,14,2,5,13)

        val sensors = mutableListOf(Environment,Position,Health)

        val sm = getSystemService(SENSOR_SERVICE) as SensorManager

    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                Log.d("mytag", position.toString())
                var selected = sensors[position]
                var names = mutableSetOf<String>()

                for (i in selected){
                    var Sensors = sm.getSensorList(i);
                    for (s in Sensors){
                        names.add(s.name);
                    }
                }


                listItems.clear()
                listItems.addAll(names)
                adapter.notifyDataSetChanged();
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

    }
}