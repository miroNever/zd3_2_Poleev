package com.example.tvapplication

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tvapplication.databinding.ActivitySignInBinding

class SignInActivity : Activity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getPreferences(MODE_PRIVATE)
        val email = sharedPreferences.getString("login", null)
        val password = sharedPreferences.getString("password", null)
        if (email != null) {
            binding.email.setText(email)
            binding.password.setText(password)
        }
        binding.login.setOnClickListener {
            if (binding.email.text.length < 3 || binding.password.text.length < 3) {
                Toast.makeText(
                    this,
                    "Длина логина и пароля должна быть больше 3 символов",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if(email==null){
                sharedPreferences.edit().putString("login",binding.email.text.toString()).apply()
                sharedPreferences.edit().putString("password",binding.password.text.toString()).apply()
                startActivity(Intent(this,QuestsActivity::class.java))
            }
            if(binding.email.text.toString()==email&&binding.password.text.toString()==password)
                startActivity(Intent(this,QuestsActivity::class.java))
            else
                Toast.makeText(this,"Неверный логин или пароль",Toast.LENGTH_SHORT).show()
        }
    }
}