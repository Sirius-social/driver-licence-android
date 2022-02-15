package com.sirius.driverlicense.ui.loading

import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.repository.UserRepository
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase
import javax.inject.Inject

class LoadingViewModel  @Inject constructor(
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider)  {

}