package com.example.android_challenge_w2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_challenge_w2.LoginTableModel
import com.example.android_challenge_w2.LoginRepository


class LoginViewModel : ViewModel() {
    var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(context: Context, email: String, password: String, fullname: String) {
        LoginRepository.insertData(context, email, password, fullname)
    }

    fun getLoginDetails(context: Context, email: String, password: String): LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, email, password)
        return liveDataLogin
    }

}