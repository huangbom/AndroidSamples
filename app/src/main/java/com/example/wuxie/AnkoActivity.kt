package com.example.wuxie

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import com.example.wuxie.base.NewBaseActivity
import com.example.wuxie.db.database
import kotlinx.android.synthetic.main.activity_anko.*
import kotlinx.android.synthetic.main.activity_my_cart.view.*
import kotlinx.android.synthetic.main.guide_activity2.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabItem
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AnkoActivity : NewBaseActivity() {

    companion object {
        fun start( ctx: Context){
            ctx.startActivity(Intent(ctx,AnkoActivity::class.java))
        }
    }
//    var tabl: TabLayout? = null

    private val log = AnkoLogger(this.javaClass)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_anko)
//        btn.setText("fewf");
        linearLayout {
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE
            button {
                id = R.id.btn
                text = "good luck"
                padding = dip(32)
            }
            val tabl = tabLayout(){
                addTab(newTab().setText("few").setIcon(R.drawable.abc_btn_check_material))
                addTab(newTab().setText("fewf").setIcon(R.drawable.navigation_empty_icon))
                addTab(newTab().setText("3wf").setIcon(R.drawable.navigation_empty_icon))

            }.lparams(width = matchParent,height = wrapContent)
            tabl.getTabAt(2)?.select();
//            recyclerView {
//                orientation = LinearLayout.HORIZONTAL
//            }.lparams(width = matchParent, height = matchParent)
        }
//

        log.debug("deb?????ug")
        log.debug("da bu chu lai?????ug")
        log.info { "info" }
        log.info { "a bu chu" }
//        database.use {
//        }
//        AnkoLogger("tag")

    }
}
