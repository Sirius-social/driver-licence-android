package com.sirius.driverlicense.base.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirius.driverlicense.ui.activities.auth.AuthActivityModel
import com.sirius.driverlicense.ui.activities.loader.LoaderActivityModel
import com.sirius.driverlicense.ui.activities.main.MainActivityModel
import com.sirius.driverlicense.ui.activities.mainPolice.MainActivityModelPolice
import com.sirius.driverlicense.ui.activities.splash.SplashActivityModel
import com.sirius.driverlicense.ui.activities.splashPolice.SplashPoliceActivityModel
import com.sirius.driverlicense.ui.activities.tutorial.TutorialActivityModel
import com.sirius.driverlicense.ui.auth.auth_first.AuthFirstViewModel
import com.sirius.driverlicense.ui.auth.auth_fourth.AuthFourthViewModel
import com.sirius.driverlicense.ui.auth.auth_second.AuthSecondViewModel
import com.sirius.driverlicense.ui.auth.auth_second_second.AuthSecondSecondViewModel
import com.sirius.driverlicense.ui.auth.auth_third.AuthThirdViewModel
import com.sirius.driverlicense.ui.auth.auth_third_identity.AuthThirdChooseIdViewModel
import com.sirius.driverlicense.ui.auth.auth_third_identity.AuthThirdIdentityViewModel
import com.sirius.driverlicense.ui.auth.auth_third_third.AuthThirdThirdViewModel
import com.sirius.driverlicense.ui.auth.auth_zero.AuthZeroViewModel
import com.sirius.driverlicense.ui.chats.AllChatsViewModel
import com.sirius.driverlicense.ui.chats.chats.ChatsViewModel
import com.sirius.driverlicense.ui.connections.ConnectionCardViewModel
import com.sirius.driverlicense.ui.connections.ConnectionRequestDetailViewModel
import com.sirius.driverlicense.ui.contacts.ContactsViewModel
import com.sirius.driverlicense.ui.inviteUser.InviteUserFragment
import com.sirius.driverlicense.ui.inviteUser.InviteUserViewModel
import com.sirius.driverlicense.ui.main.MainDriverViewModel
import com.sirius.driverlicense.ui.main.MainPoliceViewModel
import com.sirius.driverlicense.ui.police.DocumentShareViewModel
import com.sirius.driverlicense.ui.police.PoliceRequesterViewModel
import com.sirius.driverlicense.ui.profile.MenuProfileViewModel
import com.sirius.driverlicense.ui.scan.MenuScanQrViewModel


import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * Add all viewModel Here
     */


    /**
     * Activity viewModel Here
     */


    @Binds
    @IntoMap
    @ViewModelKey(MainActivityModel::class)
    internal abstract fun bindMainActivityModel(viewModel: MainActivityModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MainActivityModelPolice::class)
    internal abstract fun bindMainActivityModelPolice(viewModel: MainActivityModelPolice): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityModel::class)
    internal abstract fun bindSplashActivityModel(viewModel: SplashActivityModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashPoliceActivityModel::class)
    internal abstract fun bindSplashPoliceActivityModel(viewModel: SplashPoliceActivityModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoaderActivityModel::class)
    internal abstract fun bindLoaderActivityModel(viewModel: LoaderActivityModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(TutorialActivityModel::class)
    internal abstract fun bindTutorialActivityModel(viewModel: TutorialActivityModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthActivityModel::class)
    internal abstract fun bindAuthActivityModel(viewModel: AuthActivityModel): ViewModel

    /**
     * Fragments viewModel Here
     */

    @Binds
    @IntoMap
    @ViewModelKey(AuthZeroViewModel::class)
    internal abstract fun bindAuthZeroViewModel(viewModel: AuthZeroViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AuthFirstViewModel::class)
    internal abstract fun bindAuthFirstViewModel(viewModel: AuthFirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthSecondViewModel::class)
    internal abstract fun bindAuthSecondViewModel(viewModel: AuthSecondViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthSecondSecondViewModel::class)
    internal abstract fun bindAuthSecondSecondViewModel(viewModel: AuthSecondSecondViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdViewModel::class)
    internal abstract fun bindAuthThirdViewModel(viewModel: AuthThirdViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdThirdViewModel::class)
    internal abstract fun bindAuthThirdThirdViewModel(viewModel: AuthThirdThirdViewModel): ViewModel




    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdIdentityViewModel::class)
    internal abstract fun bindAuthThirdIdentityViewModel(viewModel: AuthThirdIdentityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthThirdChooseIdViewModel::class)
    internal abstract fun bindAuthThirdChooseIdViewModel(viewModel: AuthThirdChooseIdViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(AuthFourthViewModel::class)
    internal abstract fun bindAuthFourthViewModel(viewModel: AuthFourthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainPoliceViewModel::class)
    internal abstract fun bindMainPoliceViewModel(viewModel: MainPoliceViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MainDriverViewModel::class)
    internal abstract fun bindMainDriverViewModel(viewModel: MainDriverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuScanQrViewModel::class)
    internal abstract fun bindMenuScanQrViewModel(viewModel: MenuScanQrViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuProfileViewModel::class)
    internal abstract fun bindMenuProfileViewModel(viewModel: MenuProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllChatsViewModel::class)
    internal abstract fun bindAllChatsViewModel(viewModel: AllChatsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    internal abstract fun bindContactsViewModel(viewModel: ContactsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ChatsViewModel::class)
    internal abstract fun bindChatsViewModel(viewModel: ChatsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ConnectionCardViewModel::class)
    internal abstract fun bindConnectionCardViewModel(viewModel: ConnectionCardViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ConnectionRequestDetailViewModel::class)
    internal abstract fun bindConnectionRequestDetailViewModel(viewModel: ConnectionRequestDetailViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(InviteUserViewModel::class)
    internal abstract fun bindInviteUserViewModel(viewModel: InviteUserViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DocumentShareViewModel::class)
    internal abstract fun bindDocumentShareViewModel(viewModel: DocumentShareViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PoliceRequesterViewModel::class)
    internal abstract fun bindPoliceRequesterViewModell(viewModel: PoliceRequesterViewModel): ViewModel



}
