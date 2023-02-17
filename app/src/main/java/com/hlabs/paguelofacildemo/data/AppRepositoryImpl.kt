package com.hlabs.paguelofacildemo.data

import com.hlabs.paguelofacildemo.api.ApiResultData
import com.hlabs.paguelofacildemo.api.ApiServices
import com.hlabs.paguelofacildemo.api.BaseRepository
import com.hlabs.paguelofacildemo.domain.model.PagueloFacilResponse
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(override val apiClient: ApiServices): AppRepository, BaseRepository() {

    override suspend fun getMovements(s: String, filter:String,date:String): ApiResultData<PagueloFacilResponse> {

        return try {

            val dataResponse = apiClient.getMovements(s,filter,date)

            if(dataResponse.body()?.headerStatus?.code==200){
                ApiResultData.Success(dataResponse.body()!!)
            }else{

            }

        }catch (e : Exception){

            ApiResultData.Error(e)
        }
    }
}