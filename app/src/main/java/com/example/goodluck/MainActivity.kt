package com.example.goodluck

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call data on start up
        getData()

        // Call data on button click
        val btnNewNumber: Button = findViewById(R.id.btnNewNumber)
        btnNewNumber.setOnClickListener {
            getData()
        }
    }

    data class NumberTicket(
        @SerializedName("number")
        var number : String
    )

    interface Endpoint {
        @POST("number")
        fun getTicket() : Call<NumberTicket>
    }

    /**
     * Get number from API
     */
    private fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://good-luck-api.vercel.app/api/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getTicket()

        callback.enqueue(object : Callback<NumberTicket> {
            override fun onFailure(call: Call<NumberTicket>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<NumberTicket>, response: Response<NumberTicket>) {
                response.body()?.number?.let { mountTicket(it) }
            }
        })

    }

    /**
     * Mount Ticket Function
     */
    @SuppressLint("SetTextI18n")
    private fun mountTicket(number: String) {
        // val numList = generateNumber()
        val numberView: TextView = findViewById(R.id.numberView)

        // Assemble the string with all the numbers and space between tow digits.
        numberView.text = "${number[0]}${number[1]} ${number[2]}${number[3]} ${number[4]}${number[5]} ${number[6]}${number[7]}"
        //numberView.text = "${numList[0]}${numList[1]} ${numList[2]}${numList[3]} ${numList[4]}${numList[5]} ${numList[6]}${numList[7]}"
    }

    /**
     * Generate Random Number (Only tests)
     */
//    private fun generateNumber(): MutableList<Int> {
//        val number: MutableList<Int> = ArrayList()
//
//        // Repeat for 6 times
//        for (i in 1..8) {
//            // Add a random number between 0 and 9 to the array of numbers "number"
//            number.add((0..9).random())
//        }
//
//        // Return the array
//        return number
//    }
}

