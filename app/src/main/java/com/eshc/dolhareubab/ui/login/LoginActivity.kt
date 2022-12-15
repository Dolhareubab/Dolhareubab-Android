package com.eshc.dolhareubab.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.databinding.ActivityLoginBinding
import com.eshc.dolhareubab.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        initObserver()
        initViews()
    }

    private fun initViews() {
        binding?.btnOk?.setOnClickListener {
            viewModel.register()
        }
    }

    private fun initObserver() {
        viewModel.loginState.observe(this) {
            if(it) startMainActivity()
        }

        viewModel.name.observe(this) {
            if(it.isNullOrEmpty()){
                binding?.tiNickname?.error = resources.getString(R.string.login_error)
                binding?.btnOk?.isEnabled  = false
            } else {
                binding?.tiNickname?.isErrorEnabled = false
                if(!viewModel.phone.value.isNullOrEmpty()) binding?.btnOk?.isEnabled  = true
            }
        }

        viewModel.phone.observe(this) {
            if(it.isNullOrEmpty()){
                binding?.tiPhone?.error = resources.getString(R.string.login_error)
                binding?.btnOk?.isEnabled  = false
            } else {
                binding?.tiPhone?.isErrorEnabled = false
                if(!viewModel.name.value.isNullOrEmpty()) binding?.btnOk?.isEnabled  = true
            }
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