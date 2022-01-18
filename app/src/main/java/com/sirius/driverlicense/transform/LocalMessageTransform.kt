package com.sirius.driverlicense.transform

import androidx.lifecycle.LiveData

import com.sirius.driverlicense.models.ui.ItemActions
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository
import com.sirius.driverlicense.repository.models.LocalMessage

import com.sirius.driverlicense.utils.extensions.observeOnceUnsafe
import java.util.*

class LocalMessageTransform {

    companion object {
        fun toItemContacts(localMessage: LocalMessage?): ItemContacts {
            if (localMessage == null) {
                return ItemContacts()
            }
       /*     val message = localMessage.message()
            var title = ""
           *//* if (message is Invitation) {
                title = message.label() ?:""
            }*//*
            var id = localMessage.id
            val pairwise = localMessage.restorePairwise()
            pairwise?.let {
                id = localMessage.pairwiseDid
                title = pairwise.their.label ?:""
            }*/
          //  val contact = ItemContacts(id ?: "", title, Date())
            val contact = ItemContacts("", "title", Date())
            return contact
        }

       /* fun toBaseItemMessage(localMessage: LocalMessage?): BaseItemMessage {
            if (localMessage == null) {
                return TextItemMessage(localMessage = null)
            }
            val message = localMessage.message()
            if (message is Invitation) {
                val message = ConnectItemMessage(localMessage)
                return message
            }
             if(message is ConnRequest){
                 val message = ConnectItemMessage(localMessage)
                 return message
             }
            if (message is OfferCredentialMessage) {
                return OfferItemMessage(localMessage)
            }
            if (message is RequestPresentationMessage) {
                return ProverItemMessage(localMessage)
            }
            if (message is QuestionMessage) {
                return QuestionItemMessage(localMessage)
            }

            if (message is Message) {
                return TextItemMessage(localMessage)
            }
            return TextItemMessage(localMessage = null)
        }*/

       /* fun toItemActions(localMessage: LocalMessage?): ItemActions {
            if (localMessage == null) {
                return ItemActions()
            }
            val message = localMessage.message()
            var type: ItemActions.ActionType? = null
            var hint = ""
            if (message is OfferCredentialMessage) {
                type = ItemActions.ActionType.Offer
            }
            if (message is Invitation) {
                type = ItemActions.ActionType.Connect
                hint = message.label() ?: ""
            }

            if (message is OfferCredentialMessage) {
                type = ItemActions.ActionType.Offer
               // hint = message.label() ?: ""
            }
            if (message is RequestPresentationMessage) {
                type = ItemActions.ActionType.Request
                // hint = message.label() ?: ""
            }
            if (message is QuestionMessage) {
                type = ItemActions.ActionType.Question
                // hint = message.label() ?: ""
            }
            var id = localMessage.id
            val pairwise = localMessage.restorePairwise()
            pairwise?.let {
              //  id = localMessage.pairwiseDid
                hint = pairwise.their.label ?:""
            }
            return ItemActions(id ?: "", type, hint)
        }*/


        fun toLocalMessage(
            itemContacts: ItemContacts?,
            messageRepository: MessageRepository
        ): LiveData<LocalMessage?> {
            return messageRepository.getItemById(itemContacts?.id ?: "")
        }

        fun toLocalMessage(
            itemActions: ItemActions?,
            messageRepository: MessageRepository
        ): LiveData<LocalMessage?> {
            return messageRepository.getItemById(itemActions?.id ?: "")
        }
    }
}