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

    /**
     * Mount Ticket Function
     */
    private fun mountTicket() {
        val numList = generateNumber()
        val numberView: TextView = findViewById(R.id.numberView)

        // Assemble the string with all the numbers and space between the third and fourth number.
        numberView.text = "${numList[0]}${numList[1]}${numList[2]} ${numList[3]}${numList[4]}${numList[5]}"
    }

    /**
     * Generate Random Number
     */
    private fun generateNumber(): MutableList<Int> {
        val number: MutableList<Int> = ArrayList()

        // Repeat for 6 times
        for (i in 1..6) {
            // Add a random number between 0 and 9 to the array of numbers "number"
            number.add((0..9).random())
        }

        // Return the array
        return number
    }
}

