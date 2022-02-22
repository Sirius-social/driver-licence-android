package com.sirius.driverlicense.ui.police

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.base.ui.OnAdapterItemClick
import com.sirius.driverlicense.databinding.PoliceRequesterFragmentBinding
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.models.ui.ItemCredentials
import com.sirius.driverlicense.ui.chats.chats.ChatsFragment
import com.sirius.driverlicense.ui.connections.ConnectionCardFragment
import com.sirius.driverlicense.ui.connections.ConnectionsAdapter
import com.sirius.driverlicense.ui.credentials.CredentialsDetailAdapter

class PoliceRequesterFragment  : BaseFragment<PoliceRequesterFragmentBinding,PoliceRequesterViewModel>(){

    companion object {
        fun newInstance(item: ItemContacts? = null): PoliceRequesterFragment {
            val args = Bundle()
            args.putSerializable("item", item)
            val fragment = PoliceRequesterFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun getLayoutRes(): Int {
        return R.layout.police_requester_fragment
    }

    private var adapter: ConnectionsAdapter? = null
    override fun subscribe() {
        model.policemanLoadingEndLiveData.observe(this, Observer {
            if(it){
                model.policemanLoadingEndLiveData.value = false
                animateLogo()
            }
        })
        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })

        model.sharingDocumentSuccessLiveData.observe(this, Observer {
            adapter?.isProgress=false
            adapter?.notifyDataSetChanged()
        })
    }

    private fun updateAdapter(data: List<ItemCredentials>) {
        adapter?.setDataList(data)
        adapter?.notifyDataSetChanged()

    }

    fun animateLogo(){
        dataBinding.policeLayout.animate()
            .x(0f)
            .scaleY(0.5f)
            .scaleX(0.5f)
            .y(0f)
            .setDuration(600L)
            .setInterpolator(LinearInterpolator())
            .start()
    }
    override fun setModel() {
        dataBinding.viewModel = model
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun setupViews() {
        super.setupViews()
        model.item = arguments?.getSerializable("item") as? ItemContacts
        adapter = ConnectionsAdapter(model::onConnectionClick)
        dataBinding.lastConnectionsContainer.adapter = adapter
        adapter!!.isShowLoading = true
        adapter!!.isProgress = true
        adapter!!.onAdapterItemClick = OnAdapterItemClick {
            val builder = AlertDialog.Builder(context)
            val view =   LayoutInflater.from(context).inflate(R.layout.dialog_connection_card,null,false)
            val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
            val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
            val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
            val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
            val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)

            //  var adapter: ConnectionDetailsAdapter? = null
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(it.detailList)
            //   binding?.attachList?.isNestedScrollingEnabled = false
            recyclerView.adapter = adapter

            if (it.title?.contains("driver", true) == true) {
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            } else if (it.title?.contains("passport", true) == true) {
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            }  else if(it.title?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(it.title?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else {
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }

            nameTextView.text = it.title
            connectionDescriptionTextView.text = it.credRecord?.schema_id


            builder.setView(view)
            builder.show()
        }
        dataBinding.verifyTitle.text =  String.format(App.getContext().getString(R.string.verify_documents_of),model.item?.title ?:"")
        model.policemanTextLiveData.postValue(String.format(App.getContext().getString(R.string.verify_police_policeman), model.item ?.title?:""))


    }
}