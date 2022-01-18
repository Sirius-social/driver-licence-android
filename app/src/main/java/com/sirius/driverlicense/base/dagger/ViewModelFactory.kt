package com.sirius.driverlicense.base.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirius.driverlicense.ui.activities.auth.AuthActivityModel
import com.sirius.driverlicense.ui.activities.main.MainActivityModel
import com.sirius.driverlicense.ui.activities.splash.SplashActivityModel
import com.sirius.driverlicense.ui.activities.tutorial.TutorialActivityModel


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
    @ViewModelKey(SplashActivityModel::class)
    internal abstract fun bindSplashActivityModel(viewModel: SplashActivityModel): ViewModel


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

/*
    @Binds
    @IntoMap
    @ViewModelKey(AuthZeroViewModel::class)
    internal abstract fun bindAuthZeroViewModel(viewModel: AuthZeroViewModel): ViewModel
*/

}
