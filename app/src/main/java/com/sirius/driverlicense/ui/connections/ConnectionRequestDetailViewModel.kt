package com.sirius.driverlicense.ui.connections

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel

import javax.inject.Inject

class ConnectionRequestDetailViewModel @Inject constructor(
    resourcesProvider: ResourcesProvider,

    ) : BaseViewModel(resourcesProvider) {


    val connectionDetailsLiveData = MutableLiveData<String>()
    val connectionDateLiveData = MutableLiveData<String>()
    val connectionColorLiveData = MutableLiveData<ColorStateList>()
    val connectionImageLiveData = MutableLiveData<Drawable>()

   // var details: ConnectionDetailsWrapper? = null

    override fun onViewCreated() {
        super.onViewCreated()

       // connectionDateLiveData.value = details?.name
     //   connectionDetailsLiveData.value = details?.text
     //   connectionColorLiveData.value = App.getContext().resources.getColorStateList(details?.color ?: R.color.cardConnectionCredentials)
     //   connectionImageLiveData.value = App.getContext().resources.getDrawable(details?.icon ?: R.drawable.ic_connection_credentials2)
    }

}