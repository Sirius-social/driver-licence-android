package com.sirius.driverlicense.repository

import androidx.lifecycle.MutableLiveData


import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(val messageRepository: MessageRepository)
     {


   /* override fun eventStore(id: String, event: Pair<String?, Message?>?, accepted: Boolean) {
        val localMessage = LocalMessage(id, event?.first)
        localMessage.message = event?.second?.serialize()
        localMessage.isAccepted = accepted
        if(event?.second is com.sirius.library.agent.aries_rfc.feature_0095_basic_message.Message){
            localMessage.type = "text"
        }else if(event?.second is Invitation || event?.second is ConnRequest){
            localMessage.type = "invitation"
        }else if(event?.second is  OfferCredentialMessage){
            localMessage.type = "offer"
        }else if(event?.second is RequestPresentationMessage){
            localMessage.type = "prover"
        }else if(event?.second is QuestionMessage){
            localMessage.type = "question"
        }
        if (event?.second?.messageObjectHasKey("sent_time") == true) {
            val sentTime = event?.second?.getStringFromJSON("sent_time")
            localMessage.sentTime = DateUtils.getDateFromString(
                sentTime,
                DateUtils.PATTERN_ROSTER_STATUS_RESPONSE2, true
            )
        }
        if (localMessage.sentTime == null) {
            localMessage.sentTime = Date()
        }

        messageRepository.createOrUpdateItem(localMessage)
    }
*/


  /*  override fun eventRemove(id: String) {
        //   messageRepository.r
    }

    override fun getEvent(id: String): Pair<String, Message>? {
        val message = messageRepository.getItemBy(id)
        message?.let { local ->
            val restoredMessage = local.message()
            restoredMessage?.let {
                return Pair(local.pairwiseDid ?: "", it)
            }
        }
        return null
    }*/
}