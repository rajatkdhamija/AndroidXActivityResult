package com.example.androidxactivityresult

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivityNormally.setOnClickListener {
            startMyActivity.launch(
                MyActivity.getIntent(this, 1)
            )
        }

        startActivityWithContract.setOnClickListener {
            openMyActivityWithContract.launch(1)
        }

        requestPermissions.setOnClickListener {
            requestPermission.launch(Manifest.permission.CAMERA)
        }

        requestMultiplePermission.setOnClickListener {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    // Normal activity result contract
    private val startMyActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Can do something here
                showSnackBack("Result OK received from My Activity")
            }
        }

    // My Custom activity result contract
    private val openMyActivityWithContract =
        registerForActivityResult(MyActivityContract()) { result ->
            if (result != null) showSnackBack("Result : $result")
            else showSnackBack("No Result")
        }

    // Request single permission contract
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // Do something if permission granted
            if (isGranted) showSnackBack("Permission Granted!")
            else showSnackBack("Permission Denied!!")
        }

    // Request multiple permissions contract
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Do something for permissions granted or denied
            permissions.entries.forEach {
                it.key
            }
        }

    private fun showSnackBack(text: String) {
        Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }
}