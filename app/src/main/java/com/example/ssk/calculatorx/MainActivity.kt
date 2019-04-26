package com.example.ssk.calculatorx

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADDITION = "+"
        const val SUBTRACTION = "-"
        const val MULTIPLICATION = "*"
        const val DIVISION = "/"
    }

    private val TAG = "MainActivity"

    private var firstValue: Double? = null
    private var secondValue: Double? = null
    private var currentOperator: String? = null
    private var calculated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHandler()
    }

    @SuppressLint("SetTextI18n")
    private fun setHandler() {
        setClickButtons(
            zeroButton, oneButton, twoButton,
            threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton,
            nineButton, dotButton, divButton,
            plusButton, multiplyButton, subButton,
            equalButton, clearButton
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setClickButtons(vararg buttons: Button) {
        for (button in buttons) {
            when (button) {
                zeroButton, oneButton, twoButton, threeButton,
                fourButton, fiveButton, sixButton, sevenButton,
                eightButton, nineButton, dotButton -> setClickNumber(button)
                divButton, plusButton, multiplyButton, subButton -> setClickOperator(button)
                equalButton -> setClickCalculate(button)
                clearButton -> setClickClear(button)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setClickNumber(button: Button) {
        button.setOnClickListener {
            if (calculated) {
                resultText.text.clear()
                calculated = false
            }
            resultText.setText(resultText.text.toString() + button.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setClickOperator(button: Button) {
        button.setOnClickListener {
            if (firstValue == null) {
                firstValue = resultText.text.toString().toDouble()
                currentOperator = button.text.toString()
                resultText.text.clear()
            }
        }
    }

    private fun setClickCalculate(button: Button) {
        button.setOnClickListener {
            secondValue = resultText.text.toString().toDouble()
            resultText.setText(calculate().toString())
            calculated = true
            reset()
        }
    }

    private fun setClickClear(button: Button) {
        button.setOnClickListener {
            resultText.text.clear()
            reset()
        }
    }

    private fun calculate(): Double {
        return if (firstValue == null || secondValue == null) 0.0
        else when (currentOperator) {
            SUBTRACTION -> (firstValue!! - secondValue!!)
            ADDITION -> (firstValue!! + secondValue!!)
            MULTIPLICATION -> (firstValue!! * secondValue!!)
            DIVISION -> (firstValue!! / secondValue!!)
            else -> 0.0
        }
    }

    private fun reset() {
        firstValue = null
        secondValue = null
        currentOperator = null
    }
}
