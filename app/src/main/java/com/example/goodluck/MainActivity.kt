package com.example.goodluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call mount ticket on start up
        mountTicket()

        // Call mount ticket on button click
        val btnNewNumber: Button = findViewById(R.id.btnNewNumber)
        btnNewNumber.setOnClickListener {
            mountTicket()
        }
    }

    private fun mountTicket() {
        val numList = generateNumber()
        val numberView: TextView = findViewById(R.id.numberView)

        numberView.text = "${numList[0]}${numList[1]}${numList[2]} ${numList[3]}${numList[4]}${numList[5]}"
    }

    private fun generateNumber(): MutableList<Int> {
        val number: MutableList<Int> = ArrayList()

        for (i in 1..6) {
            number.add((0..9).random())
        }

        return number
    }
}

