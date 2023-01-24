package com.hlabs.paguelofacildemo.view.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hlabs.paguelofacildemo.R

import com.hlabs.paguelofacildemo.databinding.FragmentSecondBinding
import com.hlabs.paguelofacildemo.domain.model.TransactionsItem


class DetailDialogFragment(private var item: TransactionsItem) : BottomSheetDialogFragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)



        binding.textViewCard.text = item.cardCode
        binding.textViewDescription.text = item.txConcept
        binding.textViewIdTransactions.text = "ID Transacción: ${item?.idTransaction}"

        if(item.cardType.equals("VISA")){
            Glide.with(requireContext()).load(R.drawable.cc_visa).into(binding.imageView)
        }

        if(item.cardType.equals("MC")){
            Glide.with(requireContext()).load(R.drawable.cc_mastercard).into(binding.imageView)
        }



        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}