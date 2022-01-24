package com.sirius.driverlicense.base.dagger;

import android.app.Application
import com.sirius.driverlicense.repository.MessageRepository
import com.sirius.driverlicense.ui.activities.auth.AuthActivity
import com.sirius.driverlicense.ui.activities.loader.LoaderActivity
import com.sirius.driverlicense.ui.activities.main.MainActivity
import com.sirius.driverlicense.ui.activities.mainPolice.MainActivityPolice
import com.sirius.driverlicense.ui.activities.splash.SplashActivity
import com.sirius.driverlicense.ui.activities.splashPolice.SplashPoliceActivity
import com.sirius.driverlicense.ui.activities.tutorial.TutorialActivity
import com.sirius.driverlicense.ui.auth.auth_first.AuthFirstFragment
import com.sirius.driverlicense.ui.auth.auth_fourth.AuthFourthFragment
import com.sirius.driverlicense.ui.auth.auth_second.AuthSecondFragment
import com.sirius.driverlicense.ui.auth.auth_second_second.AuthSecondSecondFragment
import com.sirius.driverlicense.ui.auth.auth_third.AuthThirdFragment
import com.sirius.driverlicense.ui.auth.auth_third_identity.AuthThirdIChooseIdFragment
import com.sirius.driverlicense.ui.auth.auth_third_identity.AuthThirdIdentityFragment
import com.sirius.driverlicense.ui.auth.auth_third_third.AuthThirdThirdFragment
import com.sirius.driverlicense.ui.auth.auth_zero.AuthZeroFragment
import com.sirius.driverlicense.ui.chats.AllChatsFragment
import com.sirius.driverlicense.ui.main.MainDriverFragment
import com.sirius.driverlicense.ui.main.MainPoliceFragment
import com.sirius.driverlicense.ui.profile.MenuProfileFragment
import com.sirius.driverlicense.ui.scan.MenuScanQrFragment


import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelModule::class])
interface AppComponent {

    @dagger.Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application?): Builder?
        fun build(): AppComponent?
    }


    fun provideMessageRepository(): MessageRepository?
    /**
     * Add all fragment with ViewModel Here
     */
    //Activities
    fun inject(activity: MainActivity)
    fun inject(activity: MainActivityPolice)
    fun inject(activity: AuthActivity)
    fun inject(activity: TutorialActivity)
    fun inject(activity: SplashActivity)
    fun inject(activity: SplashPoliceActivity)
    fun inject(activity: LoaderActivity)

    //Fragments
    fun inject(fragment: AuthZeroFragment)
    fun inject(fragment: AuthFirstFragment)
    fun inject(fragment: AuthSecondFragment)
    fun inject(fragment: AuthSecondSecondFragment)
    fun inject(fragment: AuthThirdFragment)
    fun inject(fragment: AuthThirdThirdFragment)
    fun inject(fragment: AuthThirdIdentityFragment)
    fun inject(fragment: AuthThirdIChooseIdFragment)
    fun inject(fragment: AuthFourthFragment)

    fun inject(fragment: MainPoliceFragment)
    fun inject(fragment: MainDriverFragment)
    fun inject(fragment: MenuScanQrFragment)
    fun inject(fragment: MenuProfileFragment)
    fun inject(fragment: AllChatsFragment)


}