package com.sirius.driverlicense.transform


import com.sirius.driverlicense.models.ui.ItemActions
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.ui.chats.chats.message.*

import com.sirius.library.agent.aries_rfc.feature_0036_issue_credential.messages.OfferCredentialMessage
import com.sirius.library.agent.aries_rfc.feature_0037_present_proof.messages.RequestPresentationMessage
import com.sirius.library.agent.aries_rfc.feature_0095_basic_message.Message
import com.sirius.library.agent.aries_rfc.feature_0113_question_answer.messages.QuestionMessage
import com.sirius.library.agent.aries_rfc.feature_0160_connection_protocol.messages.ConnRequest
import com.sirius.library.agent.aries_rfc.feature_0160_connection_protocol.messages.Invitation
import com.sirius.library.agent.listener.Event
import java.util.*


class EventTransform() {

    companion object {
        fun eventToItemContacts(event: Event?): ItemContacts {
            if (event == null) {
                return ItemContacts()
            }
            val message = event.message()
            var title = ""
            if (message is Invitation) {
                title = message?.label() ?:""
            }
            var id = message?.getId()
            if (event.pairwise != null) {
                id = event?.pairwise?.their?.did ?:""
                title = event?.pairwise?.their?.label ?:""
            }
            val contact = ItemContacts(id?:"", title, Date())
            return contact
        }

        fun eventToBaseItemMessage(event: Event?): BaseItemMessage {
            if (event == null) {
                return TextItemMessage(event = null)
            }
            val message = event.message()
            if (message is Invitation) {
                val message = ConnectItemMessage(event = event)
                return message
            }
             if(message is ConnRequest){
                 return OfferItemMessage(event = event)
             }
            if (message is OfferCredentialMessage) {
                return OfferItemMessage(event = event)
            }
            if (message is RequestPresentationMessage) {
                return ProverItemMessage(event = event)
            }
            if (message is QuestionMessage) {
                return QuestionItemMessage(event = event)
            }

            if (message is Message) {
                return TextItemMessage(event)
            }
            return TextItemMessage(event = null)
        }

        fun eventToItemActions(event: Event?): ItemActions {
            if (event == null) {
                return ItemActions()
            }
            val message = event.message()
            var type: ItemActions.ActionType? = null
            var hint = ""
            if (message is OfferCredentialMessage) {
                type = ItemActions.ActionType.Offer
                hint = event.pairwise?.their?.label ?: ""
            }
            if (message is Invitation) {
                type = ItemActions.ActionType.Connect
                hint = event.pairwise?.their?.label ?: ""
            }
            return ItemActions(message?.getId()?:"", type, hint)
        }

     /*   fun itemActionToEvent(itemActions: ItemActions?, eventRepository: EventRepository): Event? {
            return eventRepository.getEvent(itemActions?.id ?: "")
        }

        fun itemContactsToEvent(
            itemContacts: ItemContacts?,
            eventRepository: EventRepository
        ): Event? {
            return eventRepository.getEvent(itemContacts?.id ?: "")
        }*/
    }
}