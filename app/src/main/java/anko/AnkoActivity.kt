package anko

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.design.*
import android.support.design.widget.*
import org.jetbrains.anko.*

import com.example.wuxie.R

/**
 * Generate with Plugin
 * @plugin Kotlin Anko Converter For Xml
 * @version 1.3.4
 */
class AnkoActivity : Activity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		linearLayout {
			//tools:context = .AnkoActivity //not support attribute
			button {
				text = "click"
				setOnClickListener {
					//click()
				}
			}
		}
	}
}
