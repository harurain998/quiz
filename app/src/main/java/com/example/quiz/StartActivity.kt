package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val stBtn : ImageButton = findViewById(R.id.stBtn)
        val homeBtn : ImageButton = findViewById(R.id.homeBtn)
        val setBtn : ImageButton = findViewById(R.id.setBtn)
        val mypBtn : ImageButton = findViewById(R.id.mypBtn)

/*        //ボタンを押したらゲームスタート
        stBtn.setOnClickListener{
            val intent = Intent(this,(ここに遷移したい画面（QuizActivityなど）)::class.java)
                startActivity(intent)
        }

        //ボタンを押したらホーム画面
        homeBtn.setOnClickListener{
            val intent = Intent(this,(ここに遷移したい画面（QuizActivityなど）)::class.java)
                startActivity(intent)
        }

        //ボタンを押したら設定画面
        setBtn.setOnClickListener{
            val intent = Intent(this,(ここに遷移したい画面（QuizActivity）など)::class.java)
                startActivity(intent)
        }

        //ボタンを押したらマイページ画面
        mypBtn.setOnClickListener{
            val intent = Intent(this,(ここに遷移したい画面（QuizActivityなど）)::class.java)
            startActivity(intent)
        }*/

    }
}