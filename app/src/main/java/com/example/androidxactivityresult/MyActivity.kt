package com.example.androidxactivityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my.*

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        btnBackWithData.setOnClickListener {
            val intent = Intent().apply {
                putExtra(MESSAGE, "Hello World!")
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val MESSAGE = "message"
        const val MESSAGE_ID = "message_id"

        fun getIntent(context: Context, message_id: Int): Intent {
            return Intent(context, MyActivity::class.java).apply {
                putExtra(MESSAGE_ID, message_id)
            }
        }
    }
}