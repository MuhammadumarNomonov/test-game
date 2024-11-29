package com.example.testgame

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var rad: RadioGroup
    lateinit var txt_misol: TextView
    lateinit var btn_next: Button
    lateinit var rad_a: RadioButton
    lateinit var rad_b: RadioButton
    lateinit var rad_c: RadioButton
    lateinit var rad_d: RadioButton

    var javob = 0
    var random1 = 0
    var random2 = 0
    var random3 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI komponentlarini bog'lash
        rad = findViewById(R.id.rad_group)
        txt_misol = findViewById(R.id.txt_misol)
        btn_next = findViewById(R.id.btn_next)
        rad_a = findViewById(R.id.rad_a)
        rad_b = findViewById(R.id.rad_b)
        rad_c = findViewById(R.id.rad_c)
        rad_d = findViewById(R.id.rad_d)

        // Yangi misol yaratish
        random()

        // Next tugmasi bosilganda
        btn_next.setOnClickListener {
            // Qaysi radio tugma tanlanganini tekshirish
            val selectedRadio = when {
                rad_a.isChecked -> rad_a
                rad_b.isChecked -> rad_b
                rad_c.isChecked -> rad_c
                rad_d.isChecked -> rad_d
                else -> null
            }

            if (selectedRadio != null) {
                if (selectedRadio.text.toString().toInt() == javob) {
                    Toast.makeText(this, "To'g'ri!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Notog'ri!", Toast.LENGTH_SHORT).show()
                }
                random() // Yangi misol yaratish
            } else {
                Toast.makeText(this, "Iltimos, javobni tanlang!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun random() {
        // Tasodifiy raqamlar
        val number1 = Random.nextInt(1, 20)
        val number2 = Random.nextInt(1, 20)
        val operation = Random.nextInt(0, 4)

        // Amalni tanlash
        when (operation) {
            0 -> {
                txt_misol.text = "$number1 + $number2 = "
                javob = number1 + number2
            }
            1 -> {
                txt_misol.text = "$number1 - $number2 = "
                javob = number1 - number2
            }
            2 -> {
                txt_misol.text = "$number1 * $number2 = "
                javob = number1 * number2
            }
            3 -> {
                if (number2 != 0) {
                    txt_misol.text = "$number1 / $number2 = "
                    javob = number1 / number2
                } else {
                    random() // 0 ga bo'lish holatini oldini olish
                    return
                }
            }
        }

        // Tasodifiy javoblarni yaratish
        randomSonaniqla()
        radEkrangaYoz()
    }

    // Takrorlanmas tasodifiy sonlarni yaratish
    private fun randomSonaniqla() {
        random1 = Random.nextInt(1, 20)
        random2 = Random.nextInt(1, 20)
        random3 = Random.nextInt(1, 20)

        // Agar tasodifiy sonlar takrorlansa, yangi sonlarni yaratish
        while (random1 == random2 || random2 == random3 || random1 == random3) {
            random1 = Random.nextInt(1, 20)
            random2 = Random.nextInt(1, 20)
            random3 = Random.nextInt(1, 20)
        }
    }

    // Javoblarni radio tugmalarga joylash
    private fun radEkrangaYoz() {
        val options = arrayListOf(javob, random1, random2, random3)
        options.shuffle() // Javoblarni tasodifiy joylashtirish

        rad_a.text = options[0].toString()
        rad_b.text = options[1].toString()
        rad_c.text = options[2].toString()
        rad_d.text = options[3].toString()
    }
}