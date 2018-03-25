package com.kalapuneet.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kalapuneet.kme.guard

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        guard{
            failure {
                Log.e("MainActivity", "Failure Block")
            }
            always {
                Log.e("MainActivity", "Always Block")
            }
            Log.e("MainActivity", "Guard Block")
        }
    }
}
