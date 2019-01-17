package com.example.wuxie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wuxie.db.database
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.verticalLayout

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko)
//        database.use {
//        }
//        AnkoLogger("tag")

    }
}
