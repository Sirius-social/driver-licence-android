package com.sirius.driverlicense.ui.auth.auth_first

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.repository.UserRepository

import javax.inject.Inject



open class AuthFirstViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {

    var isPolice: Boolean = false

    val registerHintLiveData = MutableLiveData<String>("")
    val registerFieldHintLiveData = MutableLiveData<String>("")
    val registerBtnTextLiveData = MutableLiveData<String>("")
    val alreadyExistAccountTextLiveData = MutableLiveData<CharSequence>()
    val goToNextScreenLiveData = MutableLiveData<Boolean>()
    val nextVisibilityLiveData = MutableLiveData<Int>()



    fun onNextClick(v: View) {
        goToNextScreenLiveData.postValue(true)
    }

    override fun setupViews() {
        super.setupViews()
        isNextEnabled()
    }


    fun isNextEnabled()  {
        var isNextEnabled = false
        if(isPolice){
             isNextEnabled = !userRepository.myUserPolice.name.isNullOrEmpty()
        }else{
             isNextEnabled = !userRepository.myUser.name.isNullOrEmpty()
        }

        if(isNextEnabled){
            nextVisibilityLiveData.postValue( View.VISIBLE)
        }else{
            nextVisibilityLiveData.postValue(  View.INVISIBLE)
        }

    }

    fun setUserName(name: String) {
        if(isPolice){
            userRepository.myUserPolice.name = name
        }else{
            userRepository.myUser.name = name
        }

    }

    fun saveUser() {
        if(isPolice){
            userRepository.saveUserPoliceToPref()
        }else{
            userRepository.saveUserToPref()
        }

    }

    fun setUserPassword(pass: String) {
        if(isPolice){
            userRepository.myUserPolice.pass = pass
        }else{
            userRepository.myUser.pass = pass
        }

    }

    fun setUserUid(uid: String) {
        if(isPolice){
            userRepository.myUserPolice.uid = uid
        }else{
            userRepository.myUser.uid = uid
        }


    }

    fun getUserName() : String?{
        if(isPolice){
            return userRepository.myUserPolice?.name
        }else{
            return userRepository.myUser?.name
        }

    }

}


