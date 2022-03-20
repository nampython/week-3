package com.example.android_challenge_w2

import android.content.Context
import androidx.room.*
import com.example.android_challenge_w2.LoginTableModel

//room
@Database(entities = arrayOf(LoginTableModel::class), version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {


    abstract fun loginDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDataseClient(context: Context): LoginDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                        .databaseBuilder(context, LoginDatabase::class.java, "LOGIN_DATABASE")
                        .fallbackToDestructiveMigration()
                        .build()

                return INSTANCE!!
            }
        }


    }

}