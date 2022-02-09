package  com.sirius.driverlicense.ui.profile

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.base.ui.OnAdapterItemClick
import com.sirius.driverlicense.base.ui.OnAdapterViewClick
import com.sirius.driverlicense.databinding.FragmentMenuProfileBinding
import com.sirius.driverlicense.models.ui.ItemCredentials
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase
import com.sirius.driverlicense.ui.connections.ConnectionCardFragment
import com.sirius.driverlicense.ui.connections.ConnectionsAdapter
import com.sirius.driverlicense.ui.contacts.ContactsFragment
import com.sirius.driverlicense.ui.inviteUser.InviteUserFragment
import com.sirius.driverlicense.ui.scan.MenuScanQrFragment
import com.sirius.driverlicense.utils.FileUtils
import com.sirius.driverlicense.utils.FileUtils.deleteDirectory
import com.sirius.library.mobile.SiriusSDK

class MenuProfileFragment : BaseFragment<FragmentMenuProfileBinding, MenuProfileViewModel>() {
    private val GALLERY_IMAGE = 2
    private val PHOTO_IMAGE = 1

    companion object {
        @JvmStatic
        fun newInstance() = MenuProfileFragment()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_menu_profile

    override fun setModel() {
        dataBinding!!.viewModel = model
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    private var adapter: ConnectionsAdapter? = null

    // val adapterCredentials  = CredentialsListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ConnectionsAdapter(model::onConnectionClick)
        adapter!!.onAdapterItemClick = OnAdapterItemClick {
            baseActivity.pushPage(ConnectionCardFragment.newInstance(it))
        }
        adapter!!.onAdapterViewClick = OnAdapterViewClick { item, id ->
            val inviteFragment = InviteUserFragment()
            inviteFragment.itemCredential = item
            baseActivity.pushPage(inviteFragment)
        }
        dataBinding.lastConnectionsContainer.adapter = adapter
    }

    override fun subscribe() {
        /*   model.connectionsLiveData.observe(this, Observer {
               adapter?.setItems(it)
               if (it.isEmpty()) {
                   dataBinding.lastConnectionsPlaceholder.visibility = View.VISIBLE
               } else {
                   dataBinding.lastConnectionsPlaceholder.visibility = View.GONE
               }
           })*/
        model.nameLiveData.observe(this, Observer {
            dataBinding.nameView.text = it
        })
        model.phoneLiveData.observe(this, Observer {
            dataBinding.phoneView.text = it
        })
        model.nicknameLiveData.observe(this, Observer {
            dataBinding.nicknameView.text = it
        })

        model.scanQrClickLiveData.observe(this, Observer {
            if (it) {
                model.scanQrClickLiveData.value = false
                baseActivity.pushPage(MenuScanQrFragment())
            }
        })

        model.contacsClickLiveData.observe(this, Observer {
            if (it) {
                model.contacsClickLiveData.value = false
                baseActivity.pushPage(ContactsFragment())
            }
        })

        model.exitClickLiveData.observe(this, Observer {
            if (it) {
                model.exitClickLiveData.value = false
                openExitAlert()
                //baseActivity.pushPage(ContactsFragment())
            }
        })


        model.avatarLiveData.observe(this, Observer {
            if(it!=null){
                val imageBytes = com.sirius.library.utils.Base64.getUrlDecoder().decode(it)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                dataBinding?.logoView?.setImageBitmap(decodedImage)
            }else{
                dataBinding?.logoView?.setImageResource(R.drawable.user)
            }
        })

        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })

        // model.avatarLiveData.observe(this, Observer {
        // dataBinding. avatarView.update(it)
        // })

        /*   model.connectionClickLiveData.observe(this, Observer {
                it?.let {
                    startActivity(Intent(context, ConnectionsRequestDetailActivity::class.java).apply {
                        putExtra(StaticFields.BUNDLE_CONNECTION_CARD, it.connectionWrapper)
                    })
                    model.connectionClickLiveData.value = null
                }
            })

            model.settingsLiveData.observe(this, Observer {
                startActivity(Intent(context, OrganizerActivity::class.java).apply {
                    putExtra(StaticFields.BUNDLE_USER, AppPref.getMyselfUser())
                })
            })*/

        //update avatar replace to where it belong
        /* model.avatarOnClickLiveData.observe(this, Observer {
             it?.let {
                 openCroper()
                 model.avatarOnClickLiveData.value = null
             }
         })*/
    }


    fun openExitAlert(){
        val  builder =   AlertDialog.Builder(requireContext())
        builder.setPositiveButton(R.string.dialog_yes, DialogInterface.OnClickListener { dialogInterface, i ->
            model.logout(requireContext())
            requireActivity().finishAffinity()
        })
        builder.setNegativeButton(R.string.dialog_no, null)
        builder.setTitle(R.string.dialog_warning)
        builder.setMessage(R.string.dialog_exit_text)
        builder.show()
    }
    private fun updateAdapter(data: List<ItemCredentials>) {
        adapter?.setDataList(data)
        adapter?.notifyDataSetChanged()
        if (data.isEmpty()) {
            dataBinding.emptyStateView.visibility = View.VISIBLE
        } else {
            dataBinding.emptyStateView.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /*  if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
              val result = CropImage.getActivityResult(data)
              if (resultCode == Activity.RESULT_OK) {
                  val resultUri = result.uri
                  val file: File = FileUtils.getFile(App.getContext(), resultUri)
                  iconView.setImageURI(resultUri)
                  model.uploadIconAndSend(file.absolutePath)
              } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                  val error = result.error
              }
          }*/
    }

    private fun openCroper() {
/*        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setFixAspectRatio(true)
            .setAllowRotation(true)
            .setAllowFlipping(true)
            .setActivityMenuIconColor(resources.getColor(R.color.white))
            .start(requireActivity(), this)*/
    }
}