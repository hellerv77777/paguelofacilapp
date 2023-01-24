package com.hlabs.paguelofacildemo.domain.model

data class TransactionsItem (
    var type:String="",
    var amount:String="",
    var paymentMethod:String="",
    var paymentDescription: String="",
    var date:String="",
    var ico:Int=0,
    var amountColor:Int=0,
    var typeView:Int?=0,
    var cardCode:String?=null,
    var txConcept:String?=null,
    var idTransaction:String?=null,
    var cardType:String?=null
    )