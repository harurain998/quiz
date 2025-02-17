package com.example.quiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


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
                        Toast.makeText(this, "正解！", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "不正解...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } ?: run {
            questionText.text = "問題の取得に失敗しました"
        }
    }
}