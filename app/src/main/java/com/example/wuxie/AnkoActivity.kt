package com.example.wuxie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.wuxie.base.NewBaseActivity
import com.example.wuxie.db.database
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AnkoActivity : NewBaseActivity() {
    private val log = AnkoLogger(this.javaClass)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_anko)
//        linearLayout {
//            orientation = LinearLayout.VERTICAL
//
//            //tools:context = .AnkoActivity //not support attribute
//            button {
//                text = "click"
//                setOnClickListener {
//                    //click()
//                }
//            }
//            recyclerView {
//                //				horizon = LinearLayout.HORIZONTAL
//            }.lparams(width = matchParent, height = matchParent)
//        }

        log.debug("debug")
        log.info { "info" }
//        database.use {
//        }
//        AnkoLogger("tag")

    }
}
