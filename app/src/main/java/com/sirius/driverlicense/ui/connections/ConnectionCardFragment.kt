package com.sirius.driverlicense.ui.connections

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App

import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentConnectionCardBinding
import com.sirius.driverlicense.models.ui.ItemCredentials
import com.sirius.driverlicense.ui.credentials.CredentialsDetailAdapter
import com.sirius.driverlicense.ui.inviteUser.InviteUserFragment


private const val CONNECTION_ITEM = "CONNECTION_ITEM"

class ConnectionCardFragment :
    BaseFragment<FragmentConnectionCardBinding, ConnectionCardViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(connection: ItemCredentials) = ConnectionCardFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CONNECTION_ITEM, connection)
            }
        }
    }


    val adapter = CredentialsDetailAdapter()


    override fun setupViews() {
        val connection = arguments?.getSerializable(CONNECTION_ITEM) as? ItemCredentials
        model.connection = connection
        super.setupViews()

        dataBinding.shareView.setOnClickListener {
            baseActivity.pushPage(InviteUserFragment())
        }

        if (connection?.title?.contains("driver", true) == true) {
            dataBinding.connectionIconView.setImageResource(R.drawable.ic_driver_license)
            val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
            val csl = ColorStateList.valueOf(colorInt)
            dataBinding.connectionIconView.imageTintList = csl
            dataBinding.connectionIconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
        } else if (connection?.title?.contains("passport", true) == true) {
            dataBinding.connectionIconView.setImageResource(R.drawable.ic_pass)
            val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
            val csl = ColorStateList.valueOf(colorInt)
            dataBinding.connectionIconView.imageTintList = csl
            dataBinding.connectionIconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
        } else if(connection?.title?.contains("vehicle", true) == true) {
            dataBinding.connectionIconView.setImageResource(R.drawable.ic_rent_car)
            val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
            val csl = ColorStateList.valueOf(colorInt)
            dataBinding.connectionIconView.imageTintList = csl
            dataBinding.connectionIconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
        }else if(connection?.title?.contains("diploma", true) == true) {
            dataBinding.connectionIconView.setImageResource(R.drawable.ic_diploma)
            val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
            val csl = ColorStateList.valueOf(colorInt)
            dataBinding.connectionIconView.imageTintList = csl
            dataBinding.connectionIconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
        }else {
            dataBinding.connectionIconView.setImageResource(R.drawable.documents)
            val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
            val csl = ColorStateList.valueOf(colorInt)
            dataBinding.connectionIconView.imageTintList = csl
            dataBinding.connectionIconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
        }

       /* if (connection?.title?.contains("driver", true) == true) {
            dataBinding.connectionIconView.setImageResource(R.drawable.ic_driver_license)
        } else if (connection?.title?.contains("passport", true) == true) {
            dataBinding.connectionIconView.setImageResource(R.drawable.ic_pass)
        } else {
            dataBinding.connectionIconView.setImageResource(R.drawable.documents)
        }*/
        adapter.setDataList(model.connection?.detailList)

        dataBinding.detailsRecyclerView.adapter = adapter
        dataBinding.connectionUserTextView.text = model.connection?.title
        dataBinding.connectionDescriptionTextView.text = model.connection?.credRecord?.schema_id
        dataBinding.connectionSubheaderTextView.text = model.connection?.credRecord?.cred_def_id

        /*       val view =   LayoutInflater.from(it.context).inflate(R.layout.dialog_connection_card,null,false)
               val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
               val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
               val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
               val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
               val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)




               recyclerView.adapter = adapter


               nameTextView.text = item.name
               connectionDescriptionTextView.text = item.hint
       */
    }

    override fun getLayoutRes(): Int = R.layout.fragment_connection_card


    override fun setModel() {
        dataBinding!!.viewModel = model
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {

    }

    /*



    private var adapter: ConnectionDetailsAdapter? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.setConnection(arguments?.getParcelable(CONNECTION_ITEM))
        super.onViewCreated(view, savedInstanceState)
        adapter = ConnectionDetailsAdapter(model::onRadioClick, model::onCheckboxCLick, model ::onValueListener)
        adapter!!.fragmentManager = parentFragmentManager
        dataBinding. detailsRecyclerView.adapter = adapter
        dataBinding.detailsRecyclerView.isNestedScrollingEnabled = false
        try{
            dataBinding. scrollLayout.post {
                //  scrollLayout.fullScroll(View.FOCUS_DOWN)
                dataBinding.  scrollLayout.scrollY = 0
            }
        }catch (e :Exception){
            e.printStackTrace()
        }
    }

    override fun subscribe() {
    *//*    model.onBackClickLiveData.observe(this, Observer {
            try{
                onBackPressed()
            }catch (e :Exception){

                e.printStackTrace()
            }

        })*//*
        model.connectionDetailsLiveData.observe(this, Observer {
            adapter?.setItems(it)
            dataBinding.scrollLayout.post {

                //scrollLayout.fullScroll(View.FOCUS_DOWN)
                dataBinding. scrollLayout.scrollY = 0
            }
        })
        model.connectionDateLiveData.observe(this, Observer {
            dataBinding.topViewName.text = it
        })
        model.connectionUserLiveData.observe(this, Observer {
            dataBinding. connectionUserTextView.text = it
        })
        model.connectionTypeLiveData.observe(this, Observer {
            dataBinding.connectionTypeTextView.text = it
        })
        model.connectionStatusLiveData.observe(this, Observer {
            dataBinding.connectionSubheaderTextView.text = it
        })
        model.connectionDescriptionLiveData.observe(this, Observer {
            dataBinding.connectionDescriptionTextView.text = it
        })
        model.showDetailsLiveData.observe(this, Observer {
            if (it) dataBinding.detailsContainer.visibility = View.VISIBLE else View.GONE
        })
        model.errorLiveData.observe(this, Observer {
            dataBinding.warningTextView.visibility = View.VISIBLE
            dataBinding.warningTextView.text = it
        })
        model.showAction1LiveData.observe(this, Observer {
            if(it == null){
                dataBinding. actionButton1.visibility = View.GONE
            }else{
                dataBinding. actionButton1.visibility = View.VISIBLE
                dataBinding. actionButton1.text = it
            }

        })
        model.showAction2LiveData.observe(this, Observer {
            if(it == null){
                dataBinding. actionButton2.visibility = View.GONE
            }else{
                dataBinding.  actionButton2.visibility = View.VISIBLE
                dataBinding.  actionButton2.text = it
            }

        })
        model.showAction3LiveData.observe(this, Observer {
            if(it == null){
                dataBinding. actionButton3.visibility = View.GONE
            }else{
                dataBinding.  actionButton3.visibility = View.VISIBLE
                dataBinding.  actionButton3.text = it
            }

        })
        model.topViewColorLiveData.observe(this, Observer {
            dataBinding. topView.backgroundTintList = it
            dataBinding.  connectionIconView.backgroundTintList = it
        })

        model.topIconLiveData.observe(this, Observer {
            dataBinding. connectionIconView.setImageDrawable(it)
        })

        model.topIconPaddingLiveData.observe(this, Observer {
            dataBinding. connectionIconView.setPadding(it,it,it,it)
        })

        model.detailsTitleLiveData.observe(this, Observer {
            dataBinding.detailsTitleTextView.text = it
        })

        model.action3LiveData.observe(this, Observer {
           *//* it?.let {
                baseActivity.pushPage(ConnectionRequestDetailFragment.newInstance(it))
                model.action3LiveData.value = null
            }
*//*
        })

        model.messageStartObservLiveData.observe(this, Observer {
           *//* model.messagesInDBLiveData.observe(this, Observer {
                if (it.action == MessageAction.UPDATE) {
                   val isMy =  model.messageCompare(it.data)
                    if (isMy){
                        model.messageStartObservLiveData.removeObservers(this)
                        model.messagesInDBLiveData.removeObservers(this)
                    }
                }

            })*//*
        })



        model.messageSuccessLiveData.observe(this, Observer {
            it?.let {
                hideProgressDialog()
                Log.d("mylo890","messageSuccessLiveData=")
               // model.onShowToastLiveData.postValue(App.getContext().getString(R.string.proof_request_success))
               // model.onBackClickLiveData.postValue(true)
                model.messageSuccessLiveData.value = null
            }

        })

        model.messageErrorLiveData.observe(this, Observer {
            it?.let {
                hideProgressDialog()
                Log.d("mylo890","messageErrorLiveData=")
                //model.onShowToastLiveData.postValue(App.getContext().getString(R.string.proof_request_error))
               // model.onBackClickLiveData.postValue(true)
                model.errorLiveData.value = ""
            }

        })
    }*/


}