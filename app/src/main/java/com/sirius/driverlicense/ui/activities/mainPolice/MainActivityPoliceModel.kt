package com.sirius.driverlicense.ui.activities.mainPolice




import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseActivityModel
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository
import com.sirius.driverlicense.transform.LocalMessageTransform
import javax.inject.Inject

class MainActivityModelPolice @Inject constructor(
    resourceProvider: ResourcesProvider,
    val eventRepository: EventRepository,
    val messageRepository: MessageRepository

) : BaseActivityModel(resourceProvider) {

   val invitationStartLiveData = messageRepository.invitationStartLiveData
   val invitationErrorLiveData = messageRepository.invitationErrorLiveData
   val invitationSuccessLiveData = messageRepository.invitationSuccessLiveData
   val invitationPolicemanSuccessLiveData = messageRepository.invitationSuccessLiveData
   val eventStoreLiveData = messageRepository.eventStoreLiveData
   val eventStartLiveData = messageRepository.eventStartLiveData
   val eventStopLiveData = messageRepository.eventStopLiveData


    override fun onViewCreated() {
        super.onViewCreated()
    }

    fun getMessage(id : String) : ItemContacts {
        val localMessage = messageRepository.getItemBy(id)
        return LocalMessageTransform.toItemContacts(localMessage)
    }
}