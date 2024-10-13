package com.example.projecte

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.AlphabetIndexer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PreguntesMain : AppCompatActivity() {
    private lateinit var tvQuestion: TextView
    private lateinit var answerImage1: ImageView
    private lateinit var answerButton1: Button
    private lateinit var answerImage2: ImageView
    private lateinit var answerButton2: Button
    private lateinit var answerImage3: ImageView
    private lateinit var answerButton3: Button
    private lateinit var answerImage4: ImageView
    private lateinit var answerButton4: Button
    private lateinit var questions: ArrayList<Question>
    private lateinit var tvTimer: TextView
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private lateinit var countDownTimer: CountDownTimer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuestion = findViewById(R.id.tv_question)
        answerImage1 = findViewById(R.id.answerImage1)
        answerButton1 = findViewById(R.id.answerButton1)
        answerImage2 = findViewById(R.id.answerImage2)
        answerButton2 = findViewById(R.id.answerButton2)
        answerImage3 = findViewById(R.id.answerImage3)
        answerButton3 = findViewById(R.id.answerButton3)
        answerImage4 = findViewById(R.id.answerImage4)
        answerButton4 = findViewById(R.id.answerButton4)
        tvTimer = findViewById(R.id.tvTimer)


        loadQuestions()
        startTimer()
        showQuestion(0)
    }

    data class Question(
        val id: Int,
        val text: String,
        val optionimg: List<Int>,
        val answerOptions: List<String>,
        val correctAnswer: String
    )

private fun loadQuestions() {
    questions = arrayListOf(
        PreguntesMain.Question(
            0,
            "Quina marca utilitza el logo d'una poma mossegada?",
            listOf(R.drawable.microsoft, R.drawable.samsung, R.drawable.sony, R.drawable.apple),
            listOf("Microsoft", "Samsung", "Sony", "Apple"),
            "Apple"
        ),
        PreguntesMain.Question(
            1,
            "¿Quina marca de begudes és coneguda pel seu logotip vermell amb lletres blanques?",
            listOf(R.drawable.pepsi, R.drawable.cocacola, R.drawable.fanta, R.drawable.sprite),
            listOf("Pepsi", "Cocacola", "Fanta", "Sprite"),
            "Cocacola"
        ),
        PreguntesMain.Question(
            2,
            "Quina marca de tecnologia té un logotip amb una lletra 'G' de colors?",
            listOf(R.drawable.google, R.drawable.facebook, R.drawable.amazon, R.drawable.twitter),
            listOf("Google", "Facebook", "Amazon", "Twitter"),
            "Google"
        ),
        PreguntesMain.Question(
            3,
            "Quina marca de cotxes utilitza un logo amb quatre cercles entrellaçats?",
            listOf(R.drawable.bmw, R.drawable.audi, R.drawable.volkswagen, R.drawable.tesla),
            listOf("BMW", "Audi", "Volkswagen", "Tesla"),
            "Audi"
        ),
        PreguntesMain.Question(
            4,
            "Quina marca d'esports utilitza un logotip amb el símbol de la deessa grega Nike?",
            listOf(R.drawable.puma, R.drawable.nike, R.drawable.adidas, R.drawable.reebok),
            listOf("Puma", "Nike", "Adidas", "Reebok"),
            "Nike"
        ),
        PreguntesMain.Question(
            5,
            "Quina marca de menjar ràpid utilitza un logotip amb una gran 'M' daurada?",
            listOf(
                R.drawable.mcdonald_s,
                R.drawable.burger_king,
                R.drawable.subway,
                R.drawable.kfc
            ),
            listOf("McDonald's", "Burger King", "Subway", "KFC"),
            "McDonald's"
        ),
        PreguntesMain.Question(
            6,
            "Quina marca de luxe utilitza un logotip amb dues lletres 'C' entrellaçades?",
            listOf(
                R.drawable.louis_vuitton,
                R.drawable.gucci,
                R.drawable.chanel,
                R.drawable.hermes
            ),
            listOf("Louis Vuitton", "Gucci", "Chanel", "Hermès"),
            "Chanel"
        ),
        PreguntesMain.Question(
            7,
            "Quina marca de moda esportiva utilitza tres ratlles paral·leles com a logotip?",
            listOf(R.drawable.adidas, R.drawable.puma, R.drawable.nike, R.drawable.tommy_hilfiger),
            listOf("Adidas", "Puma", "Nike", "Tommy Hilfiger"),
            "Adidas"
        ),
        PreguntesMain.Question(
            8,
            "Quina marca d'electrònica utilitza un logotip en forma d'una fletxa somrient que apunta d'una 'A' a una 'Z'?",
            listOf(R.drawable.ebay, R.drawable.alibaba, R.drawable.amazon, R.drawable.twitter),
            listOf("eBay", "Alibaba", "Amazon", "Walmart"),
            "Amazon"
        )
    )
}

private fun showQuestion(currentQuestionIndex: Int) {

    val question = questions[currentQuestionIndex]

    tvQuestion.text = question.text
    answerImage1.setImageResource(question.optionimg[0])
    answerButton1.text = question.answerOptions[0]
    answerImage2.setImageResource(question.optionimg[1])
    answerButton2.text = question.answerOptions[1]
    answerImage3.setImageResource(question.optionimg[2])
    answerButton3.text = question.answerOptions[2]
    answerImage4.setImageResource(question.optionimg[3])
    answerButton4.text = question.answerOptions[3]

    answerButton1.setOnClickListener { checkAnswer(question.answerOptions[0], question.correctAnswer) }
    answerButton2.setOnClickListener { checkAnswer(question.answerOptions[1], question.correctAnswer) }
    answerButton3.setOnClickListener { checkAnswer(question.answerOptions[2], question.correctAnswer) }
    answerButton4.setOnClickListener { checkAnswer(question.answerOptions[3], question.correctAnswer) }
}
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = "Tiempo: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                showResults()
                nextQuestion()
            }
        }.start()
    }

    private fun checkAnswer(selectedAnswer: String, correctAnswer: String) {
        val correctAnswer = questions[currentQuestionIndex].correctAnswer
        if (selectedAnswer == correctAnswer) {
            correctAnswers++
        }
        nextQuestion()
    }
    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            showQuestion(currentQuestionIndex)
        } else {
            showResults()
        }
    }
    private fun showResults() {
        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        startActivity(intent)
        finish()
    }
}
