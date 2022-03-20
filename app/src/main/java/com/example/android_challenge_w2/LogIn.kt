package com.example.android_challenge_w2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_challenge_w2.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import com.example.android_challenge_w2.databinding.ActivityLoginBinding

open class LogIn : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    private lateinit var bindingLogin: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide();

        bindingLogin = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        context = this@LogIn

        bindingLogin.button4.setOnClickListener {
            var email: String = bindingLogin.editTextTextEmailAddress.text.toString()
            var password: String = bindingLogin.editTextTextPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                bindingLogin.editTextTextEmailAddress.setError("Enter your email, Please!!!");
            }
            if (TextUtils.isEmpty(password)) {
                bindingLogin.editTextTextPassword.setError("Enter your password, Please!!!");
            }

            loginViewModel.getLoginDetails(context, email, password)!!.observe(this, Observer {
                if (it == null) {
                    Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Login complete", Toast.LENGTH_LONG).show()
                    val bundle = Bundle()
                    bundle.putString("email", it.Email)
                    bundle.putString("fullName", it.FullName)
                    bundle.putString("passWord", it.Password)
                    val intent = Intent(this, Profile::class.java)
                    intent.putExtras(bundle)
                    //     intent.putExtra( "Email", email)
                    startActivity(intent)


                }

            })

        }
        bindingLogin.textView18.setOnClickListener {

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        bindingLogin.imageView13.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}