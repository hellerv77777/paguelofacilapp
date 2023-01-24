package com.hlabs.paguelofacildemo.view.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.hlabs.paguelofacildemo.R
import com.hlabs.paguelofacildemo.databinding.FragmentDialogErrorBinding


class DialogErrorFragment : DialogFragment() {


    private lateinit var binding:FragmentDialogErrorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDialogErrorBinding.inflate(inflater,container,false)

        binding.buttonAceptar.setOnClickListener {
            dismiss()
        }

        return binding.root
    }


}