package com.eshc.dolhareubab.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.databinding.ActivityLoginBinding
import com.eshc.dolhareubab.ui.MainActivity


class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )

        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        binding?.btnOk?.setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(
            Intent(
                this, MainActivity::class.java
            )
        )
        finish()
    }


}