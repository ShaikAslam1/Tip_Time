package com.aslam.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aslam.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.google_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isSelected

        if(roundUp) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        Toast.makeText(this, "${binding.tipResult.text}",Toast.LENGTH_SHORT ).show()

    }
}