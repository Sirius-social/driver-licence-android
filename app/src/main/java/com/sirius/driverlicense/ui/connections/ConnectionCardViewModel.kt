package com.sirius.driverlicense.ui.connections

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.models.ui.ItemCredentials
import com.sirius.driverlicense.ui.connections.items.DetailsBaseItem
import javax.inject.Inject


class ConnectionCardViewModel @Inject constructor(
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {


     var connection: ItemCredentials? = null

    val connectionUserLiveData = MutableLiveData<String>()
    val connectionUserNameLiveData = MutableLiveData<String>()
    val connectionDateLiveData = MutableLiveData<String>()
    val connectionStatusLiveData = MutableLiveData<String>()
    val connectionDescriptionLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String>()

    override fun setupViews() {
        super.setupViews()

    //    bottomTextView.text = connectionItem.title




    }



/*

 //   private var connection: ConnectionsWrapper? = null
    val connectionDetailsLiveData = MutableLiveData<List<DetailsBaseItem>>()

    val connectionTypeLiveData = MutableLiveData<String>()



    val showDetailsLiveData = MutableLiveData<Boolean>()
    val detailsTitleLiveData = MutableLiveData<String>()
    val topViewColorLiveData = MutableLiveData<ColorStateList>()
    val topIconLiveData = MutableLiveData<Drawable>()
    val topIconPaddingLiveData = MutableLiveData<Int>()

    val showAction1LiveData = MutableLiveData<String>()
    val showAction2LiveData = MutableLiveData<String>()
    val showAction3LiveData = MutableLiveData<String>()
    val action3LiveData = MutableLiveData<ConnectionDetailsWrapper>()
    val messageSuccessLiveData = MutableLiveData<Boolean?>()
    val messageErrorLiveData = MutableLiveData<Boolean?>()
    val messageStartObservLiveData = MutableLiveData<Boolean?>()
    val essagesInDBLiveData = messagesRepository.messagesInDBLiveData
    override fun onViewCreated() {
        super.onViewCreated()

        connection?.let {
            fillCard(it)
        }
    }


    private fun mapWorkspacesToInfo(attributes: List<AttributesAttach>): MutableList<DetailsInfoItem> {
        val itemsToAdd: MutableList<DetailsInfoItem> = ArrayList()
        */
/*     val infoItemContacts = DetailsInfoItem("Количество контактов","10")
             itemsToAdd.add(infoItemContacts)

             val infoItemGroups = DetailsInfoItem("Количество групповых чатов","10")
             itemsToAdd.add(infoItemGroups)*//*

        */
/*  attributes.forEachIndexed { index, attr ->
              val infoItem = DetailsInfoItem.map(attr)
              itemsToAdd.add(infoItem)
          }*//*

        return itemsToAdd
    }

    private fun mapAttributesToInfo(attributes: List<AttributesAttach>): MutableList<DetailsInfoItem> {
        val itemsToAdd: MutableList<DetailsInfoItem> = ArrayList()
        attributes.forEachIndexed { index, attr ->
            val infoItem = DetailsInfoItem.map(attr)
            itemsToAdd.add(infoItem)
        }
        return itemsToAdd
    }

    private fun mapAttributesToProof(attributes: List<FirebaseIndyFields>): MutableList<DetailsBaseItem> {
        val itemsToAdd: MutableList<DetailsBaseItem> = ArrayList()
        attributes.forEachIndexed { index, attr ->
            val item = DetailsBaseItem.mapTo(attr)
            itemsToAdd.add(item)
        }
        return itemsToAdd
    }

    fun fillCard(connection: ConnectionsWrapper) {

        connectionUserLiveData.value = connection.userName.orEmpty()
        connectionStatusLiveData.value = connection.credName.orEmpty()
        connectionDescriptionLiveData.value = connection.comment
        val creentialtitle =
            App.getContext().getString(R.string.credential_message).replace("[", "")
                .replace("]", "");
        val verification =
            App.getContext().getString(R.string.proof_request_message).replace("[", "")
                .replace("]", "");
        val workspaces = App.getContext().getString(R.string.workspaces_message).replace("[", "")
            .replace("]", "");
        val orders =
            App.getContext().getString(R.string.orders_message).replace("[", "").replace("]", "");
        val credentialProposeTitle =
            App.getContext().getString(R.string.propose_credential_message).replace("[", "")
                .replace("]", "");

        if (connection.type == ConnectionsWrapper.ConnectionType.proofrequest) {
            val items = mapAttributesToProof(connection.indyFields)
            connectionDetailsLiveData.value = items
            topViewColorLiveData.value =
                App.getContext().resources.getColorStateList(R.color.cardConnectionCredentials)
            topIconLiveData.value =
                App.getContext().resources.getDrawable(R.drawable.ic_connection_verification)

            connectionTypeLiveData.value = verification
            detailsTitleLiveData.value =
                resourceProvider.getString(R.string.proof_request_details).toUpperCase()
        } else if (connection.type == ConnectionsWrapper.ConnectionType.credentilas) {
            val items = mapAttributesToInfo(connection.fileds)
            connectionDetailsLiveData.value = items
            topViewColorLiveData.value =
                App.getContext().resources.getColorStateList(R.color.cardOrange)
            topIconLiveData.value =
                App.getContext().resources.getDrawable(R.drawable.ic_connection_credentials2)
            connectionTypeLiveData.value = creentialtitle
            detailsTitleLiveData.value =
                resourceProvider.getString(R.string.proof_request_details).toUpperCase()
        } else if (connection.type == ConnectionsWrapper.ConnectionType.workspaces) {
            val items = mapWorkspacesToInfo(connection.fileds)
            connectionDetailsLiveData.value = items
            topViewColorLiveData.value =
                App.getContext().resources.getColorStateList(R.color.cardConnectionGreen)
            topIconLiveData.value =
                App.getContext().resources.getDrawable(R.drawable.ic_connection_chat)
            connectionTypeLiveData.value = workspaces
            detailsTitleLiveData.value =
                resourceProvider.getString(R.string.proof_request_details).toUpperCase()
        } else if (connection.type == ConnectionsWrapper.ConnectionType.orders) {
            val items = mapAttributesToInfo(connection.fileds)
            connectionDetailsLiveData.value = items
            topViewColorLiveData.value =
                App.getContext().resources.getColorStateList(R.color.cardBlue)
            topIconLiveData.value =
                App.getContext().resources.getDrawable(R.drawable.ic_connection_credentials2)
            connectionTypeLiveData.value = orders
            detailsTitleLiveData.value =
                resourceProvider.getString(R.string.proof_request_details).toUpperCase()
        } else if (connection.type == ConnectionsWrapper.ConnectionType.proposeCredentials) {
            val items = mapAttributesToInfo(connection.fileds)
            connectionDetailsLiveData.value = items
            topViewColorLiveData.value =
                App.getContext().resources.getColorStateList(R.color.cardViolet)
            topIconLiveData.value =
                App.getContext().resources.getDrawable(R.drawable.send_credentials_mini)
            connectionTypeLiveData.value = credentialProposeTitle
            detailsTitleLiveData.value =
                resourceProvider.getString(R.string.proof_request_details).toUpperCase()
        }
        //Для proofrequest комментарий при отказе
        */
/*credStatus.setText(R.string.proof_request_error);
        String commnet = "";
        if (!TextUtils.isEmpty(messages.getCommentCode())) {
            commnet = messages.getCommentCode();
        }
        if (!TextUtils.isEmpty(commnet) && !TextUtils.isEmpty(messages.getComment())) {
            commnet = commnet + " : " + messages.getComment();
        }
        credName.setText(commnet);
        *//*

        var status = ""
        if (connection.status == ConnectionsWrapper.ConnectionStatus.not_accepted) {
            if (connection.type == ConnectionsWrapper.ConnectionType.proofrequest) {
                status = App.getContext().getString(R.string.proof_request_error)
                showAction1LiveData.value =
                    resourceProvider.getString(R.string.proof_request_send_proof_btn)
            } else {
                showAction1LiveData.value = resourceProvider.getString(R.string.accept)
                status = App.getContext().getString(R.string.accepted_not)
            }
            showAction2LiveData.value = resourceProvider.getString(R.string.cancel)
            if (connection.type == ConnectionsWrapper.ConnectionType.workspaces) {
                showAction2LiveData.value = null
                showAction3LiveData.value =
                    resourceProvider.getString(R.string.proof_request_details)
            }
            if (connection.type == ConnectionsWrapper.ConnectionType.proposeCredentials) {
                showAction1LiveData.value =
                    resourceProvider.getString(R.string.credential_propose_btn_accept)
                showAction3LiveData.value = null

            }
        } else if (connection.status == ConnectionsWrapper.ConnectionStatus.accepted) {
            if (connection.type == ConnectionsWrapper.ConnectionType.proofrequest) {
                status = App.getContext().getString(R.string.proof_request_success)
            } else {
                status = App.getContext().getString(R.string.accepted)
            }
            showAction1LiveData.value = null
            showAction2LiveData.value = null
            showAction3LiveData.value = resourceProvider.getString(R.string.proof_request_details)
            */
/*  if (connection.type == ConnectionsWrapper.ConnectionType.workspaces) {
                   showAction2LiveData.value =  resourceProvider.getString(R.string.workspaces_leave)
               }*//*

        } else if (connection.status == ConnectionsWrapper.ConnectionStatus.canceled) {
            if (connection.type == ConnectionsWrapper.ConnectionType.proofrequest) {
                status = App.getContext().getString(R.string.proof_request_error)
            } else {
                status = App.getContext().getString(R.string.canceled)
            }
            showAction1LiveData.value = null
            showAction2LiveData.value = null
            showAction3LiveData.value = resourceProvider.getString(R.string.proof_request_details)
            topViewColorLiveData.value =
                App.getContext().resources.getColorStateList(R.color.errorColor)
            errorLiveData.value = connection.errorComment ?: ""

        }
        connectionDateLiveData.value = status + ":" + connection.parseToDateTime()


        var isAllCredInside = true
        var isSelfAttestedCredInside = false
        if (connection.type == ConnectionsWrapper.ConnectionType.proofrequest) {
            val indyFields = connection?.indyFields.orEmpty()
            if (indyFields.isEmpty()) {
                isAllCredInside = false
            }
            for (i in indyFields.indices) {
                val value = indyFields[i].getValue()

                if (TextUtils.isEmpty(value)) {
                    isAllCredInside = false
                }
                if (indyFields[i].isSelfAttested) {
                    if (TextUtils.isEmpty(value)) {
                        isSelfAttestedCredInside = true
                    }
                }
            }
        }

        if (!isAllCredInside) {
            errorLiveData.value = resourceProvider.getString(R.string.proof_request_cred_not_good)
            if (isSelfAttestedCredInside) {
                errorLiveData.value =
                    resourceProvider.getString(R.string.proof_request_cred_not_good_type_all)
            }
            showAction3LiveData.value = resourceProvider.getString(R.string.proof_request_details)
            showAction1LiveData.value = null
        } else {
          //  showAction3LiveData.value = null
            errorLiveData.value = ""
        }

    }


    fun setConnection(connection: ConnectionsWrapper?) {
        this.connection = connection
    }

    fun onRadioClick(item: DetailsRadioItem) {

    }

    fun onCheckboxCLick(item: DetailsCheckboxItem) {

    }

    fun onValueListener(item: DetailsSelfAttestedItem) {
        val filed = connection?.indyFields?.firstOrNull { item.title == it.description }
        filed?.value = item.value
        connection?.let {
            fillCard(it)
        }
    }

    fun onAction1ButtonClick(v: View?) {
        if (connection?.isTimeOut() == true) {
            onShowToastLiveData.postValue(resourceProvider.getString(R.string.proof_request_time_expire))
            return
        }
        if (connection?.type == ConnectionsWrapper.ConnectionType.proofrequest) {
            messagesInDBLiveData.value = MessageActionResourse.wait()
            sendProof()
        } else if (connection?.type == ConnectionsWrapper.ConnectionType.credentilas) {
            createCredIdConnection(
                connection?.messageRef?.indy_transport
                    ?: "", connection?.credDefId ?: ""
            )
        } else if (connection?.type == ConnectionsWrapper.ConnectionType.workspaces) {
            showProgressDialog()
            utilsRepository.getDynamicUrl(connection?.invitation).observeOnce(this) {
                hideProgressDialog()
                if (it == false) {
                    onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
                } else {
                    Log.d("mylog2000", "getDynamicUrl response it=" + it)
                    val messages1: MessagesNew? =
                        messagesRepository.readMessageWith(connection?.id ?: "");
                    messages1?.isAccepted = true
                    messagesRepository.changeCredProofStatusMessageInDB(messages1)
                    onBackClickLiveData.postValue(true)
                }
            }
        } else if (connection?.type == ConnectionsWrapper.ConnectionType.orders) {
            val messages1: MessagesNew? = messagesRepository.readMessageWith(connection?.id ?: "");
            messages1?.isAccepted = true
            messagesRepository.changeCredProofStatusMessageInDB(messages1)
            onBackClickLiveData.postValue(true)
        } else if (connection?.type == ConnectionsWrapper.ConnectionType.proposeCredentials) {
            createSendCredRequest(
                connection?.messageRef?.indy_transport
                    ?: "", connection?.credDefId ?: ""
            )
        }
    }

    fun onAction2ButtonClick(v: View?) {
        if (connection?.isTimeOut() == true) {
            onShowToastLiveData.postValue(resourceProvider.getString(R.string.proof_request_time_expire))
            return
        } else if (connection?.type == ConnectionsWrapper.ConnectionType.workspaces) {
            */
/*  chatsRepository.leaveWorkspaces(connection?.credDefId ).observeOnce(this){
                  if(it == false){
                      onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
                  }else{
                      val messages1: MessagesNew? = messagesRepository.readMessageWith(connection?.id ?: "");
                      messages1?.isAccepted = false
                      messagesRepository.changeCredProofStatusMessageInDB(messages1)
                      onBackClickLiveData.postValue(true)
                  }
              }*//*

        } else {
            showProgressDialog()
            sendProblem(
                connection?.did1
                    ?: "",
                connection?.messageRef,
                Feature0035.REQUEST_NOT_ACCEPTED,
                "User manually cancel operation"
            );
        }
    }

    fun onAction3ButtonClick(v: View?) {
        val gson = Gson()
        var text = ""
        val indyCipherResponse: IndyCipherResponse? = gson.fromJson(
            connection?.messageRef?.indy_transport
                ?: "", IndyCipherResponse::class.java
        )
        if (indyCipherResponse != null) {
            val message: String? = indyCipherResponse.getMessage()
            if (connection?.type == ConnectionsWrapper.ConnectionType.proofrequest) {
                val offerCredentialMessage =
                    gson.fromJson(message, MessageRequestPresentation::class.java)
                text = offerCredentialMessage.deSerializePretty();
            } else if (connection?.type == ConnectionsWrapper.ConnectionType.credentilas) {
                val offerCredentialMessage =
                    gson.fromJson(message, MessageOfferCredential::class.java);
                text = offerCredentialMessage.deSerializePretty();
            } else if (connection?.type == ConnectionsWrapper.ConnectionType.orders) {
                val offerCredentialMessage =
                    gson.fromJson(message, MessageOfferCredential::class.java);
                text = offerCredentialMessage.deSerializePretty();
            } else if (connection?.type == ConnectionsWrapper.ConnectionType.workspaces) {
                val offerCredentialMessage = gson.fromJson(message, WorkspacesMessage::class.java);
                text = offerCredentialMessage?.deSerializePretty() ?: ""
            }
        }

        var icon = R.drawable.ic_group_work
        var color = R.color.cardViolet
        if (connection?.type == ConnectionsWrapper.ConnectionType.proofrequest) {
            color = R.color.cardConnectionCredentials
            icon = R.drawable.ic_connection_verification
        }
        if (connection?.type == ConnectionsWrapper.ConnectionType.credentilas) {
            color = R.color.cardOrange
            icon = R.drawable.ic_connection_credentials2
        }
        if (connection?.type == ConnectionsWrapper.ConnectionType.workspaces) {
            color = R.color.cardConnectionGreen
            icon = R.drawable.ic_connection_chat
        }
        if (connection?.type == ConnectionsWrapper.ConnectionType.orders) {
            color = R.color.cardBlue
            icon = R.drawable.ic_connection_credentials2
        }
        if (connection?.status == ConnectionsWrapper.ConnectionStatus.canceled) {
            color = R.color.errorColor

        }
        val name = connection?.credName

        val details = ConnectionDetailsWrapper(text, name, color, icon)
        action3LiveData.postValue(details)
    }

    private fun createSendCredRequest(finalData: String, credDefId: String) {
        showProgressDialog()
        Feature0036(false).sendOfferCredential(
            connection?.did1,
            finalData,
            credDefId,
            object : ConnectionProtocol.OnSendIndyMessage {
                override fun onSucces() {
                    hideProgressDialog()
                    val messages1: MessagesNew? =
                        messagesRepository.readMessageWith(connection?.id ?: "");
                    messages1?.isAccepted = true
                    messagesRepository.changeCredProofStatusMessageInDB(messages1)
                    onBackClickLiveData.postValue(true)
                }

                override fun onFail() {
                    onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
                    hideProgressDialog()
                }

                override fun onProblemReport(code: String, comment: String) {
                    val messages: MessagesNew? =
                        messagesRepository.readMessageWith(connection?.messageRef?.id ?: "");
                    sendProblem(connection?.did1 ?: "", messages, code, comment);
                }

            })

    }

    private fun createCredIdConnection(finalData: String, credDefId: String) {
        showProgressDialog()
        try {
            Feature0036(true).handleOfferCredential(
                connection?.did1,
                connection?.messageRef?.id,
                finalData,
                credDefId,
                object : ConnectionProtocol.OnSendIndyMessage {
                    override fun onSucces() {
                        val messages: MessagesNew? =
                            messagesRepository.readMessageWith(connection?.messageRef?.id ?: "");
                        if (messages != null) {
                            messages.setAccepted(true);
                            val gson = GsonBuilder().disableHtmlEscaping().create();
                            val messageData = gson.toJson(messages, MessagesNew::class.java);
                            IndyWallet.storeCredDefMessage(credDefId, messageData);

                            messagesRepository.changeCredProofStatusMessageInDB(messages)
                        }
                        hideProgressDialog()
                        onBackClickLiveData.postValue(true)

                    }


                    override fun onFail() {
                        onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
                        hideProgressDialog()
                    }


                    override fun onProblemReport(code: String, comment: String) {
                        val messages: MessagesNew? =
                            messagesRepository.readMessageWith(connection?.messageRef?.id ?: "");
                        sendProblem(connection?.did1 ?: "", messages, code, comment);
                    }
                });
        } catch (e: IndyException) {
            e.printStackTrace();
        } catch (e: ExecutionException) {
            e.printStackTrace();
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }
    }

    public fun sendProof() {
        // progressBar2.setVisibility(View.VISIBLE);
        showProgressDialog()
        var isSended = true;
        try {
            //   String masterSecret = "test";
            val masterSecret = HashUtils.generateHash(AppPref.getInstance().getUserResourses());
            var proof = "";

             Utils.logLongText("mylog2080", "masterSecret=" + masterSecret);
             Utils.logLongText("mylog2080", "schemaJson=" + connection?.schemaJson);
              Utils.logLongText("mylog2080", "credDefJson=" + connection?.credDefJson);
             Utils.logLongText("mylog2080", "proofRequest=" + connection?.proofRequest);
             Utils.logLongText("mylog2080", "prover_requested_creds=" + connection?.prover_requested_creds);
            connection?.generateSelfAttested()
            proof = Anoncreds.proverCreateProof(
                IndyWallet.getMyWallet(),
                connection?.proofRequest,
                connection?.prover_requested_creds.toString(),
                masterSecret,
                connection?.schemaJson.toString(),
                connection?.credDefJson.toString(),
                "{}"
            ).get();


            val feature0037 = Feature0037(true);


            val baseIndyMessage = MessagePresentation(feature0037.presentation_type);
             baseIndyMessage.setId(connection?.id);
            val presentatioAttachList = ArrayList<OffersAttach>();
            val offersAttach = OffersAttach();
            //    JSONObject jsonObjectProof = new JSONObject(proof);
            //     jsonObjectProof.put("requested_proof", new JSONObject());
            //    JSONArray identifiersArray = jsonObjectProof.getJSONArray("identifiers");
            //     identifiersArray.getJSONObject(0).put("schema_id","9090");
            //     jsonObjectProof.put("identifiers",identifiersArray);
            offersAttach.setId("libindy-presentation-" + baseIndyMessage.getId());
            val base64Data = Base64Data(proof);
            offersAttach.setData(base64Data);
            presentatioAttachList.add(offersAttach);
            baseIndyMessage.addPleaseAckDecorator()

     */
/*       var threadId = connection?.id
            val messageMain = connection?.messageRef?.indy_transport
                val gson = Gson()
                val indyCipherResponse: IndyCipherResponse =
                    gson.fromJson(messageMain, IndyCipherResponse::class.java)
                if (indyCipherResponse != null) {
                    val message: String = indyCipherResponse.getMessage()
                   val  indyMessage = gson.fromJson(message, BaseIndyMessage::class.java)
                    if(indyMessage!=null){
                        threadId =  indyMessage.id
                    }
                    if(indyMessage.pleaseAck!=null){
                        threadId =  indyMessage.pleaseAck.message_id
                    }
                }


            baseIndyMessage.responseThread = ResponseThread(threadId)*//*


            baseIndyMessage.setPresentationsAttach(presentatioAttachList);


            feature0037.send_message_to_agent(connection?.did1, baseIndyMessage.deSerialize(), null,
                true, false, object : ConnectionProtocol.OnSendIndyMessage {
                    override fun onSucces() {
                        //   hideProgressDialog()
                        Log.d("mylog2090", "OnSuccess startTimer");
                        messageStartObservLiveData.postValue(true)
                        val timer = Timer();
                        timer.schedule(object : TimerTask() {
                            override fun run() {
                                try {
                                    Log.d("mylog2090", "timer ALREADY");
                                    hideProgressDialog()
                                    val messages1: MessagesNew? =
                                        messagesRepository.readMessageWith(connection?.id ?: "");
                                    if (messages1 != null) {
                                        if (!messages1.isAccepted() && !messages1.isCanceled()) {
                                            onShowToastLiveData.postValue(
                                                resourceProvider.getString(
                                                    R.string.proof_request_time_expire
                                                )
                                            )
                                            onBackClickLiveData.postValue(true)
                                        }
                                    }
                                    Log.d("mylog2090", "timer hideProgressDialog");
                                    */
/*  appExecutors.mainThread().execute {
                                          Runnable() {
                                              fun run() {



                                              }
                                          }
                                      }*//*


                                } catch (e: Exception) {
                                    e.printStackTrace();
                                    hideProgressDialog()
                                    Log.d("mylog2090", "timer e=" + e.localizedMessage);
                                }


                            }
                        }, 60 * 1000);
                    }


                    override fun onFail() {
                        Log.d("mylog2090", "onFail");
                        hideProgressDialog()
                        onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
                    }


                    override fun onProblemReport(code: String, comment: String) {
                        Log.d("mylog2090", "onProblemReport");
                        */
/* val messages:MessagesNew? = DaoUtils.readMessage(connection?.messageRef?.id);
                         sendProblem(connection?.did1 ?: "", messages, code, comment);*//*

                        onShowToastLiveData.postValue(resourceProvider.getString(R.string.proof_request_error) + ": " + comment)
                        hideProgressDialog()
                    }
                });

        } catch (ex: InterruptedException) {
            ex.printStackTrace();
            isSended = false;
        } catch (ex: ExecutionException) {
            ex.printStackTrace();
            isSended = false;
        } catch (ex: IndyException) {
            ex.printStackTrace();
            isSended = false;
        } catch (e: Exception) {
            e.printStackTrace();
            isSended = false;
        }
        if (!isSended) {
            hideProgressDialog()
            onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
        }
    }


    private fun sendProblem(did1: String, messages: MessagesNew?, code: String, comment: String) {
        val feature0036 = Feature0036(true);
        val feature0037 = Feature0037(true);
        var messID = "1";
        if (messages != null) {
            messID = messages.getId();
        }
        var problemMessage = ProblemMessage(
            feature0036.getProblemProtocol(feature0036.getProtocol()), code,
            comment, messID
        );
        if (connection?.type == ConnectionsWrapper.ConnectionType.proofrequest) {
            problemMessage = ProblemMessage(
                feature0037.getProblemProtocol(feature0037.getProtocol()), code,
                comment, messID
            );
        }
        feature0036.send_message_to_agent(
            did1,
            problemMessage.deSerialize(),
            null,
            true,
            false,
            object : ConnectionProtocol.OnSendIndyMessage {

                override fun onSucces() {
                    try {
                        if (messages != null) {
                            messages.setCanceled(true);
                            messages.setComment(comment);
                            messages.setCommentCode(code);
                            messagesRepository.changeCredProofStatusMessageInDB(messages)
                        }

                    } catch (e: Exception) {
                        e.printStackTrace();
                    }
                    hideProgressDialog()
                    onBackClickLiveData.postValue(true)
                }


                override fun onFail() {
                    try {
                        hideProgressDialog()
                        onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
                    } catch (e: Exception) {
                        e.printStackTrace();
                    }

                }

                override fun onProblemReport(code: String, comment: String) {
                    hideProgressDialog()
                }
            });
    }

    fun messageCompare(data: MessagesNew?): Boolean {
        if (data?.id == connection?.id) {
            Log.d("mylo890", "messageCompare=" + data)
            if (data?.isAccepted == true) {
                messageSuccessLiveData.postValue(true)
            } else {
                messageErrorLiveData.postValue(true)
            }
            return true
        }
        return false
    }
*/

}