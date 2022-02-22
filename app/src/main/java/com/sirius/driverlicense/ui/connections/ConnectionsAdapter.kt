package com.sirius.driverlicense.ui.connections

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.driverlicense.models.ui.ItemCredentials


private const val CONNECTION_ITEM = 1
private const val TITLE_ITEM = 2


class ConnectionsAdapter(private val onConnectionClick: (ItemCredentials) -> Unit) :
    SimpleBaseRecyclerViewAdapter<ItemCredentials, ConnectionsAdapter.ConnectionsViewHolder>() {

    var isShowLoading = false
    var isProgress = false
 /*   override fun onBind(holder: CredentialsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
    }


    class CredentialsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsCredentialsBinding? = DataBindingUtil.bind<ViewItemsCredentialsBinding>(itemView)
        fun bind(item: ItemCredentials) {
            binding?.item = item
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(item.detailList)
            binding?.detailsList?.isNestedScrollingEnabled = false
            binding?.detailsList?.adapter = adapter
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_credentials
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): CredentialsViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return CredentialsViewHolder(view)
    }
*/
    inner class ConnectionsViewHolder(itemView: View) : ViewHolder(itemView) {

        val  bottomTextView : TextView = itemView.findViewById(R.id.bottomTextView)
        val  iconView : ImageView = itemView.findViewById(R.id.iconView)
        val  shareView : ImageView = itemView.findViewById(R.id.shareView)
        val  success : ImageView = itemView.findViewById(R.id.success)
        val  progressbar : ProgressBar = itemView.findViewById(R.id.progressbar)
        /*  override val containerView: View?
              get() = itemView


         */
        fun bind(connectionItem: ItemCredentials) {

            //   itemView.topTextView.text = connectionItem.type
            bottomTextView.text = connectionItem.title
            if(connectionItem.title?.contains("driver",true)==true){
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            }else if(connectionItem.title?.contains("passport",true)==true){
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            } else if(connectionItem.title?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(connectionItem.title?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else{
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }

            shareView.setOnClickListener {
                onAdapterViewClick.onItemClick(connectionItem, it.id)
            }

            itemView.setOnClickListener {
                onAdapterItemClick?.onItemClick(connectionItem)
            }

          if(isShowLoading){
              if(isProgress){
                  progressbar.visibility = View.VISIBLE
                  success.visibility = View.GONE
              }else{
                  progressbar.visibility = View.GONE
                  success.visibility = View.VISIBLE
              }
          }else{
              progressbar.visibility = View.GONE
              success.visibility = View.GONE
          }
        }
    }

    override fun getLayoutRes(): Int {
       return R.layout.item_connection
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): ConnectionsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return ConnectionsViewHolder(view)
    }

    override fun onBind(holder: ConnectionsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
    }

}
