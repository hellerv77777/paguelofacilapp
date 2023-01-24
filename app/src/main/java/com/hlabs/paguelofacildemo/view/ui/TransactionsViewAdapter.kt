package com.hlabs.paguelofacildemo.view.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hlabs.paguelofacildemo.databinding.TransactionsItemLayoutBinding
import com.hlabs.paguelofacildemo.databinding.TransactionsItemShimmerBinding
import com.hlabs.paguelofacildemo.domain.model.TransactionsItem
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class TransactionsViewAdapter(private var mItemListener:ItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val numberFormat: NumberFormat = NumberFormat.getNumberInstance(Locale.GERMAN)
    private val decimalFormat = numberFormat as DecimalFormat

    private lateinit var context:Context
    private  var mList = emptyList<TransactionsItem>()

    companion object {
        const val GENERAL: Int = 0
        const val SHIMMER: Int = 1
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].typeView!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)

        return when(viewType){
            GENERAL->{
                TransactionsViewHolder(TransactionsItemLayoutBinding.inflate(layoutInflater, parent, false))
            }
            else -> {
                TransactionsShimmerViewHolder(TransactionsItemShimmerBinding.inflate(layoutInflater, parent, false))
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TransactionsViewHolder -> bindTransactionsViewHolder(holder, position)
            is TransactionsShimmerViewHolder -> bindTransactionsShimmerViewHolder(holder, position)
        }
    }

    private fun bindTransactionsShimmerViewHolder(holder: TransactionsViewAdapter.TransactionsShimmerViewHolder, position: Int) {

        with(holder){
            shimmer.startShimmer()
        }
    }

    private fun bindTransactionsViewHolder(holder: TransactionsViewAdapter.TransactionsViewHolder, position: Int) {

        val item = mList[position]

        with(holder){

            textViewMethodPayment.text = item.paymentMethod
            textViewDate.text = item.date
            textViewAmount.text =  "$ ${decimalFormat.format(item.amount.toDouble())}"
            textViewPaymentDescription.text = item.paymentDescription
            Glide.with(context).load(item.ico).into(imageViewPayment)

            textViewAmount.setTextColor(ContextCompat.getColor(context,item.amountColor))

            card.setOnClickListener {

                mItemListener.onClicKItem(item)
            }
        }
    }

    override fun getItemCount(): Int = mList.size

    fun updateItems(list: List<TransactionsItem>) {
        this.mList = list
        notifyDataSetChanged()
    }

    inner class TransactionsViewHolder(binding: TransactionsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageViewPayment = binding.imageViewPayment
        val textViewMethodPayment = binding.textViewType
        val textViewPaymentDescription = binding.textViewPaymentDescription
        val textViewAmount = binding.textViewAmount
        val textViewDate = binding.textViewDate
        val card = binding.card
    }

    inner class TransactionsShimmerViewHolder(binding: TransactionsItemShimmerBinding) : RecyclerView.ViewHolder(binding.root) {
        val shimmer = binding.shimmer
    }

}