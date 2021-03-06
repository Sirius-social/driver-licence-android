package com.sirius.driverlicense.base.data.api

import androidx.lifecycle.LiveData
import com.sirius.driverlicense.base.AppExecutors
import com.sirius.driverlicense.base.data.api.livedata.AbsentLiveData
import com.sirius.driverlicense.base.data.models.ApiResponse


abstract class DatabaseBoundResource<ResultType, RequestType>(appExecutors: AppExecutors) : NetworkBoundResource<ResultType, RequestType>(appExecutors) {
    override fun saveCallResult(item: RequestType) {

    }

    override fun shouldFetch(data: ResultType?): Boolean {
        return false
    }

    override fun createCall(): LiveData<ApiResponse<RequestType>> {
       return AbsentLiveData<ApiResponse<RequestType>>()
    }
}