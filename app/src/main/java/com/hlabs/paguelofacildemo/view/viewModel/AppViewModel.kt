package com.hlabs.paguelofacildemo.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hlabs.paguelofacildemo.api.onError
import com.hlabs.paguelofacildemo.api.onSuccess
import com.hlabs.paguelofacildemo.domain.model.TransactionsItem
import com.hlabs.paguelofacildemo.domain.usecase.GetMovementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(private var mGetMovementsUseCase: GetMovementsUseCase): ViewModel() {

    private val _list = MutableLiveData<List<TransactionsItem>>()
    val list: LiveData<List<TransactionsItem>> get() = _list

    private val _errorServices = MutableLiveData<Boolean?>()
    val errorServices: LiveData<Boolean?> get() = _errorServices

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    var filterType:String = ""
    var filterDate:String = ""

    private val queryTime = "dateTms${'$'}bt"

    init {
        getData()
    }

    fun getData() {

        _list.postValue(getShimmerData())
       viewModelScope.launch {

           mGetMovementsUseCase.invoke(filterType,filterDate).onSuccess {

               _list.postValue(mGetMovementsUseCase.mapMovements(it.data))

           }.onError {
               _errorServices.postValue(true)
           }
       }
    }

    fun getShimmerData(): List<TransactionsItem> {

        val list = arrayListOf<TransactionsItem>()
        for (i in 1..15){
            list.add(TransactionsItem(typeView = 1))
        }
        return list
    }


    fun setFilterDate(yearOf: Int, monthOfYear: Int, dayOfMonth: Int) {

        var monthOfYear2 = ""

        if(monthOfYear<=8){
            monthOfYear2 =  "0${(monthOfYear+1)}"
        }

        val date = "$yearOf-${(monthOfYear2)}-$dayOfMonth"

        _date.postValue(date)


        filterDate =  queryTime  + date + "T00:00:00::"+date+"T23:59:59"

        filterType = ""

    }

    fun clearServicesError() {

        _errorServices.value = null
    }


}