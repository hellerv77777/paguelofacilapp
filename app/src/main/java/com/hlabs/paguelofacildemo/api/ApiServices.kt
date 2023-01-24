package com.hlabs.paguelofacildemo.api

import com.hlabs.paguelofacildemo.domain.model.PagueloFacilResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET("api/v1/MerchantTransactions")
    suspend fun getMovements(
        @Header("authorization") auth: String,
        @Query("filter") type:String,
        @Query("conditional") conditional:String
    ): Response<PagueloFacilResponse>

}