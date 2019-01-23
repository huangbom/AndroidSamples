package com.example.wuxie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val image =  findViewById<ImageView>(R.id.ic_image)

        Glide.with(this).load(
                "https://raw.githubusercontent.com/huangbom/AndroidSamples/master/app/src/main/res/drawable/displayad_banner.png").into(image)
    }
}
