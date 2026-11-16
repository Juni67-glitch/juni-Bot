
package com.juni.bot

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private val purple = Color.rgb(145, 75, 255)
    private val dark = Color.rgb(15, 15, 24)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = dark
        window.navigationBarColor = dark

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 28, 20, 20)
            setBackgroundColor(dark)
        }

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        fun addText(text: String, size: Float, color: Int) {
            content.addView(TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(color)
                setPadding(0, 12, 0, 12)
            })
        }

        fun addButton(label: String, action: () -> Unit) {
            val button = Button(this).apply {
                text = label
                textSize = 15f
                setTextColor(Color.WHITE)
                background = GradientDrawable().apply {
                    setColor(purple)
                    cornerRadius = 22f
                }
                setOnClickListener { action() }
            }

            content.addView(button, LinearLayout.LayoutParams(
                -1, 58
            ).apply {
                setMargins(0, 9, 0, 9)
            })
        }

        addText("JUNI'S BOT", 29f, Color.WHITE)
        addText("Messenger Auto Reply Assistant", 15f, purple)

        addText("BOT STATUS", 14f, Color.LTGRAY)

        val status = TextView(this).apply {
            text = "●  BOT IS OFF"
            textSize = 19f
            setTextColor(Color.RED)
            setPadding(15, 20, 15, 20)
        }

        content.addView(status)

        addButton("START BOT") {
            Toast.makeText(this,
                "Bot setup is not complete yet",
                Toast.LENGTH_SHORT).show()
        }

        addButton("STOP BOT") {
            Toast.makeText(this,
                "Bot stopped",
                Toast.LENGTH_SHORT).show()
        }

        addText("CHAT MANAGEMENT", 18f, Color.WHITE)

        addButton("MY MESSENGER CHATS") {
            Toast.makeText(this,
                "Chat detection will be added next",
                Toast.LENGTH_SHORT).show()
        }

        addText("REPLY MANAGEMENT", 18f, Color.WHITE)

        addButton("SAVED REPLIES") {
            Toast.makeText(this,
                "Reply manager coming next",
                Toast.LENGTH_SHORT).show()
        }

        addButton("IMPORT / EXPORT CSV") {
            Toast.makeText(this,
                "CSV manager coming next",
                Toast.LENGTH_SHORT).show()
        }

        addText("ACTIVITY", 18f, Color.WHITE)

        addButton("ACTIVITY HISTORY") {
            Toast.makeText(this,
                "Activity history coming next",
                Toast.LENGTH_SHORT).show()
        }

        addText("Juni's Bot v1.0", 12f, Color.GRAY)

        scroll.addView(content)
        root.addView(scroll)
        setContentView(root)
    }
}

