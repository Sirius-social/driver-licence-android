package com.sirius.driverlicense.sirius_sdk_impl.scenario


import com.sirius.library.mobile.scenario.impl.InviteeScenario
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository
import com.sirius.driverlicense.transform.LocalMessageTransform
import com.sirius.library.mobile.helpers.ScenarioHelper
import com.sirius.library.mobile.scenario.EventAction
import com.sirius.library.mobile.scenario.EventActionListener


class InviteeScenarioImp  constructor(val messageRepository: MessageRepository,
                                      val eventRepository: EventRepository) : InviteeScenario(eventRepository) {

    override fun onScenarioStart(id: String) {

    }

    override fun onScenarioEnd(id: String,success: Boolean, error: String?) {
        messageRepository.invitationStartLiveData.postValue(id)
        val message = messageRepository.getItemBy(id)
        ScenarioHelper.getInstance().acceptScenario("Invitee", message?.getId() as? String ?: "", "", object :
            EventActionListener {
            override fun onActionStart(action: EventAction, id: String, comment: String?) {

            }

            override fun onActionEnd(
                action: EventAction,
                id: String,
                comment: String?,
                success: Boolean,
                error: String?
            ) {
                val isError = !success
                if(!isError){
                    messageRepository?.invitationSuccessLiveData?.postValue(id)
                }else{
                    messageRepository?.invitationErrorLiveData?.postValue(Pair(isError, error))
                }

            }

        })

    }

}