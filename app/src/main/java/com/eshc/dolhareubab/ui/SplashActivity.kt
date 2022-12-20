package com.eshc.dolhareubab.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.ui.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(700)
            startActivity(Intent(
                this@SplashActivity,LoginActivity::class.java
            ))
            finish()
        }

    }
}