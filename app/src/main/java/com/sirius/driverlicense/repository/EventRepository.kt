package com.sirius.driverlicense.repository

import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.repository.models.LocalMessage
import com.sirius.driverlicense.utils.DateUtils
import com.sirius.library.agent.aries_rfc.feature_0036_issue_credential.messages.OfferCredentialMessage
import com.sirius.library.agent.aries_rfc.feature_0037_present_proof.messages.RequestPresentationMessage
import com.sirius.library.agent.aries_rfc.feature_0113_question_answer.messages.QuestionMessage
import com.sirius.library.agent.aries_rfc.feature_0160_connection_protocol.messages.ConnRequest
import com.sirius.library.agent.aries_rfc.feature_0160_connection_protocol.messages.Invitation
import com.sirius.library.messaging.Message
import com.sirius.library.mobile.scenario.EventStorageAbstract


import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(val messageRepository: MessageRepository)
     :
     EventStorageAbstract {



      override fun eventStore(id: String, event: Pair<String?, Message?>?, accepted: Boolean) {
          val localMessage = LocalMessage(id, event?.first)
          localMessage.message = event?.second?.serialize()
          localMessage.isAccepted = accepted
          if(event?.second is com.sirius.library.agent.aries_rfc.feature_0095_basic_message.Message){
              localMessage.type = "text"
          }else if(event?.second is Invitation || event?.second is ConnRequest){
              localMessage.type = "invitation"
          }else if(event?.second is OfferCredentialMessage){
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



    override fun eventRemove(id: String) {
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
    }
}