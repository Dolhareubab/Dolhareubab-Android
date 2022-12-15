package com.eshc.dolhareubab.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.ui.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            delay(700)
            startActivity(Intent(
                this@SplashActivity,LoginActivity::class.java
            ))
            finish()
        }

    }
}