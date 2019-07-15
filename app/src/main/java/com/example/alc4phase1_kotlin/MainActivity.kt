package com.example.alc4phase1_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_about_alc.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        welcomeMsg_textView.setOnClickListener {
            YoYo.with(Techniques.StandUp)
                .duration(700)
                .playOn(findViewById(R.id.welcomeMsg_textView))
        }

        aboutALC_button.setOnClickListener {
            startActivity(Intent(this, AboutALCActivity::class.java))
        }

        myProfile_button.setOnClickListener {
            startActivity(Intent(this, MyProflieActivity::class.java))
        }
    }
}
