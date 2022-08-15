package com.example.oliymahad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
                email = binding.userEmail.text.toString().trim(),
                password = binding.userPassword.text.toString().trim(),
                phoneNumber = binding.userPhone.text.toString().trim()
            )

            viewModel.registerUser(user)
        }

        viewModel.user.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.pbRegisterBtn.visibility = View.INVISIBLE
                    binding.registerText.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pbRegisterBtn.visibility = View.INVISIBLE
                    binding.registerText.visibility = View.VISIBLE
                }
                is Resource.Loading -> {
                    binding.pbRegisterBtn.visibility = View.VISIBLE
                    binding.registerText.visibility = View.INVISIBLE
                }
            }
        }

        binding.goSignIn.setOnClickListener {
            binding.userEmail.text.clear()
            binding.userPassword.text.clear()
            binding.userPhone.text.clear()
            onBackPressed()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}