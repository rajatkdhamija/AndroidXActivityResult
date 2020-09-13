package com.example.androidxactivityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract


class MyActivityContract : ActivityResultContract<Int, String?>() {

    override fun createIntent(context: Context, input: Int): Intent {
        return MyActivity.getIntent(context, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(MyActivity.MESSAGE)
        return if (resultCode == Activity.RESULT_OK && data != null) data
        else null
    }

}