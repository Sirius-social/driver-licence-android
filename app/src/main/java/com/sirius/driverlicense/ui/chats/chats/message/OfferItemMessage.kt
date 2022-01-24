package com.sirius.driverlicense.ui.chats.chats.message


import com.sirius.driverlicense.models.ui.ItemCredentialsDetails
import com.sirius.driverlicense.repository.models.LocalMessage
import com.sirius.driverlicense.utils.DateUtils
import com.sirius.library.agent.aries_rfc.feature_0036_issue_credential.messages.OfferCredentialMessage
import com.sirius.library.agent.listener.Event
import com.sirius.library.mobile.helpers.ScenarioHelper
import com.sirius.library.mobile.scenario.EventAction
import com.sirius.library.mobile.scenario.EventActionListener



import java.util.*

class OfferItemMessage : BaseItemMessage {

    constructor(event: Event?) : super(event)
    constructor(localMessage: LocalMessage?) : super(localMessage)

    override fun getType(): MessageType {
        if(isError){
            return MessageType.OfferAccepted
        }
        return if (isAccepted) {
            MessageType.OfferAccepted
        } else {
            MessageType.Offer
        }
    }

    var hint: String? = null
    var expiresTime: Date? = null
    var detailList: List<ItemCredentialsDetails>? = null
    var name: String? = null

    override fun setupFromLocalMessage(localMessage: LocalMessage?) {
        super.setupFromLocalMessage(localMessage)
        val offerMessage = localMessage?.message() as? OfferCredentialMessage
        setupMessage(offerMessage)
    }
    override fun setupFromEvent(event: Event?) {
        super.setupFromEvent(event)
        val offerMessage = event?.message() as? OfferCredentialMessage
        setupMessage(offerMessage)
    }

    fun setupMessage(offerMessage : OfferCredentialMessage?){

        val preview = offerMessage?.credentialPreview

        detailList = preview?.map {
            ItemCredentialsDetails(it.name?:"", it.value?:"")
        }
        hint = offerMessage?.comment

        val timeObj = offerMessage?.getJSONOBJECTFromJSON("expires_time")
        val timeString = timeObj?.getString("~timing")
        expiresTime = DateUtils.getDateFromString(timeString, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", false)

        var offerAttaches = offerMessage?.messageObj?.getJSONArray("~attach")
        if (offerAttaches != null) {
            val att = offerAttaches.optJSONObject(0)
            if (att != null) {
                val type = att.optString("@type") ?: ""
                if (type.endsWith("/issuer-schema")) {
                    val dataObject = att.optJSONObject("data")
                    val jsonObject = dataObject?.optJSONObject("json")
                    name = jsonObject?.optString("name")
                }
            }
        }
    }


    override fun accept(comment : String?) {
        ScenarioHelper.getInstance().acceptScenario("Holder", message?.getId() ?: "", comment, object :
            EventActionListener {
            override fun onActionStart(action: EventAction, id: String, comment: String?) {
                startLoading(id)
            }

            override fun onActionEnd(
                action: EventAction,
                id: String,
                comment: String?,
                success: Boolean,
                error: String?
            ) {
                isError = !success
                isAccepted = success
                errorString = error
                commentString = error
                stopLoading(id)
            }

        } )
    }

    override fun cancel() {
        ScenarioHelper.getInstance().stopScenario("Holder", message?.getId() ?: "", "Canceled By Me", object :
            EventActionListener {
            override fun onActionStart(action: EventAction, id: String, comment: String?) {
                startLoading(id)
            }

            override fun onActionEnd(
                action: EventAction,
                id: String,
                comment: String?,
                success: Boolean,
                error: String?
            ) {
                isError = !success
                isAccepted = success
                errorString = error
                commentString = error
                stopLoading(id)
            }

        } )
    }

    override fun getText(): String {
        return "offer sample"
    }

    override fun getTitle(): String {
        return name ?: ""
    }
}