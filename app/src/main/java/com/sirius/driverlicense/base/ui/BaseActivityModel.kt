package com.sirius.driverlicense.base.ui

import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.design.BottomNavView


abstract class BaseActivityModel(resourceProvider: ResourcesProvider) : BaseViewModel(
    resourceProvider
)  {

    val  bottomNavClick : MutableLiveData<BottomNavView.BottomTab> =  MutableLiveData(BottomNavView.BottomTab.Menu)
    var selectedTab = MutableLiveData(BottomNavView.BottomTab.Menu)
    val  isVisibleUnauthBottomBar   : MutableLiveData<Pair<Boolean,Boolean>> =  MutableLiveData<Pair<Boolean,Boolean>>(Pair(false,false))

    fun getOnBottomNavClickListner() : BottomNavView.OnbottomNavClickListener{
        return object : BottomNavView.OnbottomNavClickListener{
            override fun onBottomClick(tab: BottomNavView.BottomTab) {
                bottomNavClick.postValue(tab)
            }
        }
    }

}