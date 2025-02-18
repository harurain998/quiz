package com.example.quiz

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
// import android.widget.Toast

class QuizActivity : AppCompatActivity() {
    private lateinit var questions: List<Question>
    private var currentQuestionIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val questionText: TextView = findViewById(R.id.questionText)
        val answerButtons = listOf<Button>(
            findViewById(R.id.option1),
            findViewById(R.id.option2),
            findViewById(R.id.option3),
            findViewById(R.id.option4)
        )

        // ランダムな3問を取得
        questions = QuizHelper.getRandomQuestions(this, 3)

        if (questions.isNotEmpty()) {
            currentQuestionIndex = 0
            showQuestion(questionText, answerButtons)
        } else {
            questionText.text = "問題の取得に失敗しました"
        }
    }

    private fun showQuestion(questionText: TextView, answerButtons: List<Button>) {
        val question = questions[currentQuestionIndex]

        answerButtons.forEachIndexed { index, button ->
            button.text = question.answers[index]
            button.setOnClickListener {
                if (index == question.correctAnswer) {


                    AlertDialog.Builder(this)
                        .setTitle("正解！")
                        .setMessage("エネルギーが1増えました")
                        .setPositiveButton("ok") { _, _ -> moveToNextQuestion(questionText, answerButtons) }
                        .show()
                } else {
                    AlertDialog.Builder(this)
                        .setTitle("不正解...")
                        .setMessage("もう一度挑戦しよう")
                        .setPositiveButton("ok", null)
                        .show()
                }
            }
        }
    }

    private fun moveToNextQuestion(questionText: TextView, answerButtons: List<Button>) {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            showQuestion(questionText, answerButtons)
        } else {
            showEndOfQuizDialog()
        }
    }

    private fun showEndOfQuizDialog() {
        AlertDialog.Builder(this)
            .setTitle("クイズ終了")
            .setMessage("クイズが終了しました！")
            .setPositiveButton("ok", null)
            .show()
    }
}



/*
class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val questionText: TextView = findViewById(R.id.questionText)
        val answerButtons = listOf<Button>(
            findViewById(R.id.option1),
            findViewById(R.id.option2),
            findViewById(R.id.option3),
            findViewById(R.id.option4)
        )

        // QuizHelper クラスを使ってランダムに問題を取得
        val question = QuizHelper.getRandomQuestion(this)

        question?.let { q ->
            questionText.text = q.question

            answerButtons.forEachIndexed { index, button ->
                button.text = q.answers[index]
                button.setOnClickListener {
                    if (index == q.correctAnswer) {
                        // Toast.makeText(this, "正解！", Toast.LENGTH_SHORT).show()
                        AlertDialog.Builder(this)
                            .setTitle("正解！")
                            .setMessage("エネルギーが1増えました")
                            .setPositiveButton("ok",null)
                            .show()
                    } else {
                        // Toast.makeText(this, "不正解...", Toast.LENGTH_SHORT).show()
                        AlertDialog.Builder(this)
                            .setTitle("不正解...")
                            .setMessage("もう一度挑戦しよう")
                            .setPositiveButton("ok",null)
                            .show()
                    }
                }
            }
        } ?: run {
            questionText.text = "問題の取得に失敗しました"
        }
    }
}*/
