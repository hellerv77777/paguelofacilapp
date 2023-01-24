package com.hlabs.paguelofacildemo.domain.model

data class FraudInfo(
    val credit_card: CreditCard,
    val risk_score: Double
)