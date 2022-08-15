package com.example.oliymahad.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.oliymahad.api.repository.LoginRepository
import com.example.oliymahad.model.UserLogin
import com.example.oliymahad.databinding.ActivitySignInBinding

import com.example.oliymahad.resource.Resource
import com.example.oliymahad.ui.vm.LoginVM
import com.example.oliymahad.ui.vmpf.LoginVMFactory

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: LoginVM
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignInBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = LoginRepository()
        val vmFactory = LoginVMFactory(repository)
        viewModel = ViewModelProvider(this, vmFactory).get(LoginVM::class.java)

        viewModel.userLogin.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.pbSignInButton.visibility = View.INVISIBLE
                    binding.signInText.visibility = View.VISIBLE
                    Log.d("Successsssss", it.data.toString())
                }
                is Resource.Error -> {
                    binding.pbSignInButton.visibility = View.INVISIBLE
                    binding.signInText.visibility = View.VISIBLE
                    Log.d("Error", it.message.toString())
                }
                is Resource.Loading -> {
                    binding.pbSignInButton.visibility = View.VISIBLE
                    binding.signInText.visibility = View.INVISIBLE
                    Log.d("Loading", "Loading.....")
                }
            }
        }
        binding.signInBtn.setOnClickListener {

            if (binding.signInPhone.text.isEmpty()) {
                showToast("phone is empty")
                return@setOnClickListener
            }
            if (binding.signInPassword.text.isEmpty()) {
                showToast("password is empty")
                return@setOnClickListener
            }

            val user = UserLogin(
                password = binding.signInPassword.text.toString().trim(),
                phoneNumber = binding.signInPhone.text.toString().trim()
            )

            viewModel.loginUser(user)
        }

        binding.goRegister.setOnClickListener {
            binding.signInPhone.text.clear()
            binding.signInPassword.text.clear()
            startActivity(Intent(this@SignIn, Register::class.java))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}