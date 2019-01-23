package com.example.wuxie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val image =  findViewById<ImageView>(R.id.ic_image)

        Picasso.get().load(
                "https://slack-files.com/T0BLTQC84-FFLCQMX6X-9ec3e12be7").into(image)
//        Glide.with(this).load(
//                "https://slack-files.com/T0BLTQC84-FFLCQMX6X-9ec3e12be7").into(image)
    }
}
