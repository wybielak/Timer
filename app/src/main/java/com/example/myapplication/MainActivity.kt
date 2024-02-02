package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var hour: Int = 0
    var minutes: Int = 0
    var seconds: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt_h1 = findViewById<TextView>(R.id.hours1)
        var txt_h2 = findViewById<TextView>(R.id.hours2)

        var txt_m1 = findViewById<TextView>(R.id.min1)
        var txt_m2 = findViewById<TextView>(R.id.min2)

        var txt_s1 = findViewById<TextView>(R.id.sec1)
        var txt_s2 = findViewById<TextView>(R.id.sec2)

        val button_h1u = findViewById<Button>(R.id.hours1_up)
        val button_h2u = findViewById<Button>(R.id.hours2_up)

        val button_m1u = findViewById<Button>(R.id.min1_up)
        val button_m2u = findViewById<Button>(R.id.min2_up)

        val button_s1u = findViewById<Button>(R.id.sec1_up)
        val button_s2u = findViewById<Button>(R.id.sec2_up)

        val button_h1d = findViewById<Button>(R.id.hours1_down)
        val button_h2d = findViewById<Button>(R.id.hours2_down)

        val button_m1d = findViewById<Button>(R.id.min1_down)
        val button_m2d = findViewById<Button>(R.id.min2_down)

        val button_s1d = findViewById<Button>(R.id.sec1_down)
        val button_s2d = findViewById<Button>(R.id.sec2_down)

        val button_start = findViewById<Button>(R.id.button_start)
        val button_stop = findViewById<Button>(R.id.button_stop)

        fun conv(unit: Int): Array<Int> {
            var unit = unit
            var unit1 = 0
            var unit2 = 0

            if (unit.toString().length == 2) {
                unit1 = unit/10
                unit2 = unit%10
            }

            if (unit.toString().length == 1) {
                unit1 = 0
                unit2 = unit
            }

            return arrayOf(unit1, unit2)
        }

        var h: Int = (savedInstanceState?.get("hour")?:0) as Int
        var m: Int = (savedInstanceState?.get("minutes")?:0) as Int
        var s: Int = (savedInstanceState?.get("seconds")?:0) as Int

        txt_h1.text = conv(h)[0].toString()
        txt_h2.text = conv(h)[1].toString()

        txt_m1.text = conv(m)[0].toString()
        txt_m2.text = conv(m)[1].toString()

        txt_s1.text = conv(s)[0].toString()
        txt_s2.text = conv(s)[1].toString()

        fun actionUp1(unit1: Int, unit2: Int, border: Int, n: Int): Array<Int> {
            var unit1 = unit1
            var unit2 = unit2

            if (unit1 < border) {
                unit1 += 1
            }

            if (unit1 == border) {
                unit2 = n
            }

            return arrayOf(unit1, unit2)
        }

        fun actionUp2(unit1: Int, unit2: Int, border: Int, u1border: Int, n: Int): Array<Int> {
            var unit1 = unit1
            var unit2 = unit2

            if (unit2 < border) {
                if (unit1 < u1border) unit2 += 1
            }

            if (unit2 == border) {
                unit1 += 1
                unit2 = n
            }

            return arrayOf(unit1, unit2)
        }

        fun actionDown1(unit1: Int, unit2: Int): Array<Int> {
            var unit1 = unit1
            var unit2 = unit2

            if (unit1 >= 0) {
                unit1 -= 1
            }

            if (unit1 == -1) {
                unit1 = 0
                unit2 = 0
            }

            return arrayOf(unit1, unit2)
        }

        fun actionDown2(unit1: Int, unit2: Int): Array<Int> {
            var unit1 = unit1
            var unit2 = unit2

            var unit = (unit1.toString()+unit2.toString()).toInt()

            if (unit > 0) unit -= 1

            if (unit.toString().length == 2) {
                unit1 = unit/10
                unit2 = unit%10
            }

            if (unit.toString().length == 1) {
                unit1 = 0
                unit2 = unit
            }

            return arrayOf(unit1, unit2)
        }


        button_s1u.setOnClickListener {
            var sec1 = txt_s1.text.toString().toInt()
            var sec2 = txt_s2.text.toString().toInt()

            var result = actionUp1(sec1, sec2, 6, 0)

            txt_s1.text = result[0].toString()
            txt_s2.text = result[1].toString()

        }

        button_s2u.setOnClickListener {
            var sec1 = txt_s1.text.toString().toInt()
            var sec2 = txt_s2.text.toString().toInt()

            var result = actionUp2(sec1, sec2, 10,6, 0)

            txt_s1.text = result[0].toString()
            txt_s2.text = result[1].toString()

        }

        button_m1u.setOnClickListener {
            var min1 = txt_m1.text.toString().toInt()
            var min2 = txt_m2.text.toString().toInt()

            var result = actionUp1(min1, min2, 6, 0)

            txt_m1.text = result[0].toString()
            txt_m2.text = result[1].toString()

        }

        button_m2u.setOnClickListener {
            var min1 = txt_m1.text.toString().toInt()
            var min2 = txt_m2.text.toString().toInt()

            var result = actionUp2(min1, min2, 10,6, 0)

            txt_m1.text = result[0].toString()
            txt_m2.text = result[1].toString()

        }

        button_h1u.setOnClickListener {
            var hou1 = txt_h1.text.toString().toInt()
            var hou2 = txt_h2.text.toString().toInt()

            if (hou1 < 2) {
                hou1 += 1
            }

            if (hou1 == 2) {
                if (hou2 > 4) hou2 = 4
            }

            txt_h1.text = hou1.toString()
            txt_h2.text = hou2.toString()

        }

        button_h2u.setOnClickListener {
            var hou1 = txt_h1.text.toString().toInt()
            var hou2 = txt_h2.text.toString().toInt()

            if (hou2 < 10) {
                if (hou1 < 2) hou2 += 1
                if (hou1 == 2 && hou2 < 4) hou2 += 1
            }

            if (hou2 == 10) {
                hou1 += 1
                hou2 = 0
            }

            txt_h1.text = hou1.toString()
            txt_h2.text = hou2.toString()

        }

        button_s1d.setOnClickListener {
            var sec1 = txt_s1.text.toString().toInt()
            var sec2 = txt_s2.text.toString().toInt()

            var result = actionDown1(sec1, sec2)

            txt_s1.text = result[0].toString()
            txt_s2.text = result[1].toString()

        }

        button_s2d.setOnClickListener {
            var sec1 = txt_s1.text.toString().toInt()
            var sec2 = txt_s2.text.toString().toInt()

            var result = actionDown2(sec1, sec2)

            txt_s1.text = result[0].toString()
            txt_s2.text = result[1].toString()

        }

        button_m1d.setOnClickListener {
            var min1 = txt_m1.text.toString().toInt()
            var min2 = txt_m2.text.toString().toInt()

            var result = actionDown1(min1, min2)

            txt_m1.text = result[0].toString()
            txt_m2.text = result[1].toString()

        }

        button_m2d.setOnClickListener {
            var min1 = txt_m1.text.toString().toInt()
            var min2 = txt_m2.text.toString().toInt()

            var result = actionDown2(min1, min2)

            txt_m1.text = result[0].toString()
            txt_m2.text = result[1].toString()

        }

        button_h1d.setOnClickListener {
            var hou1 = txt_h1.text.toString().toInt()
            var hou2 = txt_h2.text.toString().toInt()

            var result = actionDown1(hou1, hou2)

            txt_h1.text = result[0].toString()
            txt_h2.text = result[1].toString()

        }

        button_h2d.setOnClickListener {
            var hou1 = txt_h1.text.toString().toInt()
            var hou2 = txt_h2.text.toString().toInt()

            var result = actionDown2(hou1, hou2)

            txt_h1.text = result[0].toString()
            txt_h2.text = result[1].toString()

        }

        var countdown: CountDownTimer? = null

        button_start.setOnClickListener {
            var ho = (txt_h1.text.toString()+txt_h2.text.toString()).toInt()
            var min = (txt_m1.text.toString()+txt_m2.text.toString()).toInt()
            var sec = (txt_s1.text.toString()+txt_s2.text.toString()).toInt()

            var milisec = (ho*3600 + min*60 + sec)*1000

            button_stop.isEnabled = true
            button_start.isEnabled = false

            button_h1u.isEnabled = false
            button_h2u.isEnabled = false
            button_m1u.isEnabled = false
            button_m2u.isEnabled = false
            button_s1u.isEnabled = false
            button_s2u.isEnabled = false

            button_h1d.isEnabled = false
            button_h2d.isEnabled = false
            button_m1d.isEnabled = false
            button_m2d.isEnabled = false
            button_s1d.isEnabled = false
            button_s2d.isEnabled = false

            countdown = object : CountDownTimer(milisec.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    hour = (millisUntilFinished / (3600 * 1000)).toInt()
                    minutes = (millisUntilFinished / (60 * 1000)).toInt()
                    seconds = ((millisUntilFinished / 1000) % 60).toInt()

                    txt_h1.text = conv(hour)[0].toString()
                    txt_h2.text = conv(hour)[1].toString()

                    txt_m1.text = conv(minutes)[0].toString()
                    txt_m2.text = conv(minutes)[1].toString()

                    txt_s1.text = conv(seconds)[0].toString()
                    txt_s2.text = conv(seconds)[1].toString()
                }

                override fun onFinish() {
                    button_start.isEnabled = true
                    button_stop.isEnabled = false

                    button_h1u.isEnabled = true
                    button_h2u.isEnabled = true
                    button_m1u.isEnabled = true
                    button_m2u.isEnabled = true
                    button_s1u.isEnabled = true
                    button_s2u.isEnabled = true

                    button_h1d.isEnabled = true
                    button_h2d.isEnabled = true
                    button_m1d.isEnabled = true
                    button_m2d.isEnabled = true
                    button_s1d.isEnabled = true
                    button_s2d.isEnabled = true
                }
            }.start()
        }

        button_stop.setOnClickListener {
            countdown?.cancel()
            button_start.isEnabled = true
            button_stop.isEnabled = false

            button_h1u.isEnabled = true
            button_h2u.isEnabled = true
            button_m1u.isEnabled = true
            button_m2u.isEnabled = true
            button_s1u.isEnabled = true
            button_s2u.isEnabled = true

            button_h1d.isEnabled = true
            button_h2d.isEnabled = true
            button_m1d.isEnabled = true
            button_m2d.isEnabled = true
            button_s1d.isEnabled = true
            button_s2d.isEnabled = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("hour", hour)
        outState.putInt("minutes", minutes)
        outState.putInt("seconds", seconds)
    }
}