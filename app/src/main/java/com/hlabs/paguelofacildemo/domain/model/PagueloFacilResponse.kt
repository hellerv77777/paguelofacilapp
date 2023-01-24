package com.hlabs.paguelofacildemo.domain.model

data class PagueloFacilResponse(
    val data: List<Data>,
    val headerStatus: HeaderStatus,
    val message: Any,
    val requestId: String,
    val serverTime: String,
    val success: Boolean
)