package com.example.android_challenge_w2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.customdialog.view.*
import androidx.lifecycle.Observer

import com.example.android_challenge_w2.databinding.ActivityProfileBinding
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.customdialog.view.*

class Profile : LogIn() {
    private  lateinit var  binding: ActivityProfileBinding
    private  lateinit var  viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_profile)
        viewModel=ViewModelProvider(this).get(ProfileViewModel::class.java)

        val bundle=intent.extras
        bundle?.let {
            val emailOrgi=bundle.getString("email","unknow")
            val fullNameOrgi=bundle.getString("fullName","unknow")
            val passwordOrgi=bundle.getString("passWord","unknow")

            if(viewModel.account.email.equals("default")){
                viewModel.account.fullName=fullNameOrgi
                viewModel.account.email=emailOrgi
                viewModel.account.password=passwordOrgi
                viewModel.account.numberPhone="enter your number phone"

            }



            binding.account= viewModel.account




            //button click to show dialog
            textView26.setOnClickListener {
                //Inflate the dialog with custom view
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.customdialog, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Edit Profile")
                //show dialog
                val  mAlertDialog = mBuilder.show()
                //login button click of custom layout
                mDialogView.dialogLoginBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                    //get text from EditTexts of custom layout
                    val name = mDialogView.dialogNameEt.text.toString()
                    val email = mDialogView.dialogEmailEt.text.toString()
                    val numberPhone = mDialogView.dialogPasswEt.text.toString()
//------------------------------
                    loginViewModel.getLoginDetails(context,emailOrgi,passwordOrgi)!!.observe(this, Observer {
                        it.Email=email
                        it.FullName=name


                        viewModel.account.fullName=name
                        viewModel.account.email=email
                        viewModel.account.password=passwordOrgi
                        viewModel.account.numberPhone=numberPhone
                        binding.invalidateAll()
                        binding.account=viewModel.account
                    })


                }
                //cancel button click of custom layout
                mDialogView.dialogCancelBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }
            }
        }



        imageView9.setOnClickListener{

            val intent=Intent(this,LogIn::class.java)
            startActivity(intent)
        }


    }
}