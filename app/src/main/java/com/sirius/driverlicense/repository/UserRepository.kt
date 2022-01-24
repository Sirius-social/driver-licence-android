package com.sirius.driverlicense.repository

import androidx.annotation.WorkerThread
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.AppExecutors
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.models.User

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val appExecutors: AppExecutors)  {

    var myUser = User()
    var myUserPolice = User()
    init {
        setupUserFromPref()
    }

    fun setupUserFromPref(){
        val user =  AppPref.getInstance().getUser()
        user?.let {
            myUser = it
        }
        val userPolice =  AppPref.getInstance().getPoliceUser()
        userPolice?.let {
            myUserPolice = it
        }
    }

    fun saveUserToPref(){
        AppPref.getInstance().setUser(myUser)
    }

    fun saveUserPoliceToPref(){
        AppPref.getInstance().setPoliceUser(myUserPolice)
    }

    fun logout(){
        AppPref.getInstance().setUser(null)
    }

    fun logoutPolice(){
        AppPref.getInstance().setPoliceUser(null)
    }
}