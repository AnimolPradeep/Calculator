package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private var operand1: Double? = null
    private var operand2: Double? = 0.0
    private var pendingOperation = "="

    private val displayOperation by lazy { findViewById<TextView>(R.id.operation) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNum)

        //data input buttons
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val dot: Button = findViewById(R.id.dot)
        val neg: Button = findViewById(R.id.neg)

        //operation buttons
        val equals = findViewById<Button>(R.id.equals)
        val div = findViewById<Button>(R.id.div)
        val mul = findViewById<Button>(R.id.mul)
        val sub = findViewById<Button>(R.id.sub)
        val add = findViewById<Button>(R.id.add)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

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

        val opListener = View.OnClickListener {
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if (value.isNotEmpty()){
                performOperation(value, op)
            }
            pendingOperation = op
            displayOperation.text= pendingOperation



        }
        equals.setOnClickListener(opListener)
        div.setOnClickListener(opListener)
        mul.setOnClickListener(opListener)
        sub.setOnClickListener(opListener)
        add.setOnClickListener(opListener)

        neg.setOnClickListener({ view ->
            val value =newNumber.text.toString()
            if (value.isEmpty()){
                newNumber.setText("-")
            }
        })

    }
    private fun performOperation(value: String, operation: String){
        if (operand1 == null){
            operand1 = value.toDouble()
        }else{
            operand2 = value.toDouble()
            if (pendingOperation == "="){
                pendingOperation = operation
            }
            when (pendingOperation){
                "=" -> operand1 = operand2
                "/" -> operand1 = if (operand2 == 0.0){
                             Double.NaN
                        }else{
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
