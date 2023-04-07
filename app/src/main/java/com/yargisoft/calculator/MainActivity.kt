package com.yargisoft.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.yargisoft.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }

    fun onDigit(view: View) {
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun onClear(view: View) {
        binding.tvInput.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        binding.tvInput.text.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                binding.tvInput.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        firstValue = prefix + firstValue
                    }
                    binding.tvInput.text =
                        (firstValue.toDouble() - secondValue.toDouble()).toString()

                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        firstValue = prefix + firstValue
                    }
                    binding.tvInput.text =
                        (firstValue.toDouble() + secondValue.toDouble()).toString()

                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        firstValue = prefix + firstValue
                    }
                    binding.tvInput.text =
                        (firstValue.toDouble() / secondValue.toDouble()).toString()

                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        firstValue = prefix + firstValue
                    }
                    binding.tvInput.text =
                        (firstValue.toDouble() * secondValue.toDouble()).toString()

                }


            } catch (e: java.lang.ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}