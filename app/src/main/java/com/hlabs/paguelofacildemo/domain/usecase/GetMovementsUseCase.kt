package com.hlabs.paguelofacildemo.domain.usecase

import com.hlabs.paguelofacildemo.R
import com.hlabs.paguelofacildemo.api.ApiResultData
import com.hlabs.paguelofacildemo.data.AppRepository
import com.hlabs.paguelofacildemo.domain.model.Data
import com.hlabs.paguelofacildemo.domain.model.PagueloFacilResponse
import com.hlabs.paguelofacildemo.domain.model.TransactionsItem
import javax.inject.Inject

const val TOKEN = "brEyQRSzMm2UwQa5v0NsobRa3U8nH5xT|DIRu5WzfrUaBaJfPAcThoj2d2"

class GetMovementsUseCase @Inject constructor(private var mRepository: AppRepository){

    suspend fun invoke(type:String, date:String) : ApiResultData<PagueloFacilResponse> {

        return mRepository.getMovements(TOKEN,type,date)
    }

    fun mapMovements(data: List<Data>) : List<TransactionsItem>{

        val list = arrayListOf<TransactionsItem>()

        data.forEach {

            var typeTransactions = ""
            var ico = 0
            var amountColor = 0

            when(it.txType){
                "AUTH_CAPTURE" -> {
                    typeTransactions = "Pago por punto de venta"
                    ico = R.drawable.arrow_up_solid
                    amountColor = R.color.green
                }
                "3DS" ->{
                    ico = R.drawable.wallet_invoice
                    typeTransactions = "Solicitud de Pago"
                    amountColor = R.color.red
                }
            }

            val item = TransactionsItem("",it.amount.toString(),typeTransactions,it.txDescription,it.dateTms.split("T1")[0],ico,amountColor,0,it.displayCardNum,it.txConcept,it.idTransaction.toString(),it.cardType)
            list.add(item)
        }

        return list
    }

}