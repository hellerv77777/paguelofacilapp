package com.hlabs.paguelofacildemo.data

import com.hlabs.paguelofacildemo.api.ApiResultData
import com.hlabs.paguelofacildemo.domain.model.PagueloFacilResponse

interface AppRepository {

    suspend fun getMovements(s: String,filter:String,date:String): ApiResultData<PagueloFacilResponse>
}