package com.hlabs.paguelofacildemo.domain.model

data class Data(
    val address: String,
    val amount: Double,
    val authAmount: String,
    val authCardCountryCode: String?=null,
    val authCardCurrency: String?=null,
    val authCurrency: Int,
    val authCvv2: String?=null,
    val authDateGmt: String,
    val authStatus: String,
    val avs: String,
    val binBank: String,
    val binCountry: String,
    val blockedFunds: Boolean,
    val cardType: String,
    val cardholderFullName: String,
    val codAuth: String,
    val codOper: String,
    val containOpenClaim: Boolean,
    val containsClaim: String?=null,
    val customFields: Any,
    val dateTms: String,
    val displayCardNum: String,
    val email: String,
    val expirationDate: String?=null,
    val fraudInfo: FraudInfo,
    val idMerchant: Int,
    val idRelatedTransaction: String?=null,
    val idTransaction: Int,
    val idUsr: Int,
    val inRevision: Boolean,
    val interCom: Double,
    val ip: String,
    val ipCountry: String,
    val ipSendedCheck: String,
    val merchantName: String,
    val messageSys: String,
    val payExpired: Boolean,
    val phone: String,
    val reserveIsLiberated: Boolean,
    val reserveLiberatedManually: Boolean,
    val reserveLiberationDate: String,
    val reserveLiberationReason: String,
    val revisionApprovedDate: String?=null,
    val revisionApprovedIdUsr: String?=null,
    val revisionLevel: String?=null,
    val revisionOptions: String?=null,
    val status: Int,
    val subTotalCom: Double,
    val tax: Double,
    val taxAmount: Double,
    val taxRetention: Double,
    val totalCom: Double,
    val totalCost: Double,
    val totalReserve: Double,
    val txConcept: String,
    val txDescription: String,
    val txDescriptor: String,
    val txType: String,
    val urlString: String,
    val verificationDate: String?=null,
    val verificationRequested: String?=null
)