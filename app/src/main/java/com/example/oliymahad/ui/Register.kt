package com.example.oliymahad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.oliymahad.api.repository.RegisterRepository
import com.example.oliymahad.databinding.ActivityRegisterBinding
import com.example.oliymahad.model.UserRegister
import com.example.oliymahad.resource.Resource
import com.example.oliymahad.ui.vm.RegisterViewModel
import com.example.oliymahad.ui.vmpf.RegisterVMFactory

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = RegisterRepository()
        val vmFactory = RegisterVMFactory(repository)
        viewModel = ViewModelProvider(this, vmFactory).get(RegisterViewModel::class.java)

        binding.registerBtn.setOnClickListener {
            if (binding.userEmail.text.isEmpty()) {
                showToast("email is empty")
                return@setOnClickListener
            }
            if (binding.userPhone.text.isEmpty()) {
                showToast("phone is empty")
                return@setOnClickListener
            }
            if (binding.userPassword.text.isEmpty()) {
                showToast("password is empty")
                return@setOnClickListener
            }

            val user = UserRegister(
               email =  binding.userEmail.text.toString().trim(),
                password = binding.userPassword.text.toString().trim(),
                phoneNumber = binding.userPhone.text.toString().trim()
            )

            viewModel.registerUser(user)
        }

        viewModel.user.observe(this) {
            when (it) {
                is Resource.Success -> {
                    Log.d("REGISTER", it.data.toString())
                }
                is Resource.Error -> {

                    Log.d("ERROrr", it.message.toString())
                }
                is Resource.Loading -> {
                    Log.d("LOADING", "loading..")
                }
            }
        }

        binding.goSignIn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}