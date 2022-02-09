package com.sirius.driverlicense.ui.credentials

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.driverlicense.databinding.ViewItemsCredentialsDetailBinding
import com.sirius.driverlicense.models.ui.ItemCredentialsDetails
import com.sirius.library.agent.aries_rfc.Utils


class CredentialsDetailAdapter :
    SimpleBaseRecyclerViewAdapter<ItemCredentialsDetails, CredentialsDetailAdapter.CredentialsDetailsViewHolder>() {



    class CredentialsDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsCredentialsDetailBinding? = DataBindingUtil.bind<ViewItemsCredentialsDetailBinding>(itemView)
        fun bind(item: ItemCredentialsDetails) {

            if(item.name == "first_name"){
                item.name = "First Name"
            }
            if(item.name == "last_name"){
                item.name = "Last Name"
            }
            if(item.name == "categories"){
                item.name = "Categories"
            }
            if(item.name == "birthday"){
                item.name = "Birthday"
            }
            if(item.name == "place_of_birth"){
                item.name = "Place of birth"
            }
            if(item.name == "photo"){
                item.name = "Photo"
            }
            if(item.name == "issue_date"){
                item.name = "Issue Date"
            }

            if(item.name == "expiry_date"){
                item.name = "Expiry Date"
            }




            binding?.item = item



            if(item.value.isNullOrEmpty()){
                binding?.textDivider?.visibility = View.GONE
            }else{
                if(item.mimeType.contains("image") || item.name == "photo" ||item.name == "Pc   cchoto"  ){
                    binding?.hint?.text = ""
                    binding?.hint?.visibility = View.GONE
                    binding?.image?.visibility = View.VISIBLE
                    Log.d("mylog2090","imageBytes="+item.value)
                    val imageBytes = com.sirius.library.utils.Base64.getUrlDecoder().decode(item.value!!)
                    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    binding?.image?.setImageBitmap(decodedImage)
                }
            }
            Log.d("mylog2090","mimetype="+item.mimeType)

            if(item.value == "available"){
                binding?.hint?.setBackgroundResource(R.drawable.bg_accent_rounded_button_green)
                binding?.hint?.setTextColor(App.getContext().getColor(R.color.white))
                binding?.hint?.setPadding(20)
           /*     binding?.hint?.width = ViewGroup.LayoutParams.WRAP_CONTENT
                binding?.hint?.gravity = Gravity.CENTER
                binding?.hint?.layoutParams?.width = ViewGroup.LayoutParams.WRAP_CONTENT
                val params =  binding?.hint?.layoutParams
                params?.width =  ViewGroup.LayoutParams.WRAP_CONTENT
                binding?.hint?.layoutParams = params*/
            }else if(item.value == "not available"){
                binding?.hint?.setBackgroundResource(R.drawable.bg_accent_rounded_button_red)
                binding?.hint?.setTextColor(App.getContext().getColor(R.color.white))
                binding?.hint?.setPadding(20)
             /*   binding?.hint?.width = ViewGroup.LayoutParams.WRAP_CONTENT
                binding?.hint?.gravity = Gravity.CENTER
               val params =  binding?.hint?.layoutParams
                params?.width =  ViewGroup.LayoutParams.WRAP_CONTENT
                binding?.hint?.layoutParams = params*/
            }
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_credentials_detail
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): CredentialsDetailsViewHolder {
        return CredentialsDetailsViewHolder(getInflatedView(getLayoutRes(),parent, false))
    }

    override fun onBind(holder: CredentialsDetailsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
    }

}