package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private var operand1: Double? = null
    private var operand2: Double? = 0.0
    private var pendingOperation = "="

    private lateinit var binding: ActivityMainBinding

    private val displayOperation by lazy { binding.operation }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        result = binding.result
        newNumber = binding.newNum

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

        binding.apply {
            btn0.setOnClickListener(listener)
            btn1.setOnClickListener(listener)
            btn2.setOnClickListener(listener)
            btn3.setOnClickListener(listener)
            btn4.setOnClickListener(listener)
            btn5.setOnClickListener(listener)
            btn6.setOnClickListener(listener)
            btn7.setOnClickListener(listener)
            btn8.setOnClickListener(listener)
            btn9.setOnClickListener(listener)
            dot.setOnClickListener(listener)
            equals.setOnClickListener(listener)
            div.setOnClickListener(listener)
            mul.setOnClickListener(listener)
            sub.setOnClickListener(listener)
            add.setOnClickListener(listener)
        }

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if (value.isNotEmpty()) {
                performOperation(value, op)
            }
            pendingOperation = op
            displayOperation.text = pendingOperation


        }

        binding.apply {
            equals.setOnClickListener(opListener)
            div.setOnClickListener(opListener)
            mul.setOnClickListener(opListener)
            sub.setOnClickListener(opListener)
            add.setOnClickListener(opListener)
            neg.setOnClickListener {
                val value = newNumber.text.toString()
                if (value.isEmpty()) {
                    newNumber.setText("-")
                }
            }
        }


    }

    private fun performOperation(value: String, operation: String) {
        if (operand1 == null) {
            operand1 = value.toDouble()
        } else {
            operand2 = value.toDouble()
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "=" -> operand1 = operand2


                "/" -> operand1 = if (operand2 == 0.0) {
                    Double.NaN
                } else {
                    operand1!! / operand2!!
                }
                "*" -> operand1 = operand1!! * operand2!!
                "-" -> operand1 = operand1!! - operand2!!
                "+" -> operand1 = operand1!! + operand2!!
            }
        }
//        displayOperation.text = operation
        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
