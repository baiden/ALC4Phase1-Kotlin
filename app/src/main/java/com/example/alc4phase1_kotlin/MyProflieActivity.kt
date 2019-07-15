package com.example.alc4phase1_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_proflie.*

class MyProflieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_proflie)

        backButton.setOnClickListener{
            onBackPressed()
        }

        messageButton.setOnClickListener {
            YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.messageButton))
        }

        editProfilebutton.setOnClickListener {
            YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.editProfilebutton))
        }

        settingsButton.setOnClickListener {
            YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.settingsButton))
        }
    }
}
