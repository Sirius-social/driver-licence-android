package com.sirius.driverlicense.base.dagger;

import android.app.Application
import com.sirius.driverlicense.ui.activities.auth.AuthActivity
import com.sirius.driverlicense.ui.activities.main.MainActivity
import com.sirius.driverlicense.ui.activities.splash.SplashActivity
import com.sirius.driverlicense.ui.activities.tutorial.TutorialActivity


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


   // fun provideMessageRepository(): MessageRepository?
    /**
     * Add all fragment with ViewModel Here
     */
    //Activities
    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: TutorialActivity)
    fun inject(activity: SplashActivity)


    //Fragments
   // fun inject(fragment: AuthZeroFragment)


}