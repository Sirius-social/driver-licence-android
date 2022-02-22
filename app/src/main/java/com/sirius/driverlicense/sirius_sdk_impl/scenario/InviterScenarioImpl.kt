package com.sirius.driverlicense.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.InviterScenario
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository

class InviterScenarioImpl  constructor(val messageRepository: MessageRepository) : InviterScenario() {
    override fun onScenarioStart(id : String) {
        messageRepository.invitationStartLiveData.postValue(id)
    }

    override fun onScenarioEnd(id : String,success: Boolean, error: String?) {
        messageRepository.invitationPolicemanSuccessLiveData.postValue(id)
    }

}