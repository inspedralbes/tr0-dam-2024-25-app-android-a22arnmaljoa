package com.example.projecte

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val Resultats = findViewById<TextView>(R.id.Resultats)

        Resultats.text = "Respuestas correctas: $correctAnswers / 20"

        val tornarprincipi = findViewById<Button>(R.id.tornarprincipi)
        tornarprincipi.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
