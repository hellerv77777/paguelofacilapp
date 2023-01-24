package com.hlabs.paguelofacildemo.view.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlabs.paguelofacildemo.databinding.FragmentFirstBinding
import com.hlabs.paguelofacildemo.domain.model.TransactionsItem
import com.hlabs.paguelofacildemo.view.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


const val AUTH_CAPTURE = "txType::AUTH_CAPTURE"
const val T3DS = "txType::3DS"

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemListener {

    private var _binding: FragmentFirstBinding? = null
    private  val mViewModel: AppViewModel by viewModels()
    private lateinit var mAdapter: TransactionsViewAdapter
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        swipeController()

        mViewModel.list.observe(viewLifecycleOwner) {
            it?.let {

                if(it.isEmpty()){

                    binding.recyclerViewItems.visibility  = View.GONE
                    binding.linearEmpty.visibility = View.VISIBLE
                }else{
                    binding.linearEmpty.visibility = View.GONE
                    binding.recyclerViewItems.visibility  = View.VISIBLE
                    binding.swipe.isRefreshing = false
                    mAdapter.updateItems(it)
                }
            }
        }

        mViewModel.date.observe(viewLifecycleOwner) {

            it?.let {
                binding.buttonDate.text =it
            }
        }

        mViewModel.errorServices.observe(viewLifecycleOwner) {

            it?.let {

                if(it){

                    DialogErrorFragment().show(parentFragmentManager,"dialog-error")
                    binding.recyclerViewItems.visibility = View.GONE
                    binding.linearEmpty.visibility = View.GONE

                    mViewModel.clearServicesError()
                }
            }
        }

        viewControllers()
    }

    private fun viewControllers() {


        binding.buttonDate.setOnClickListener {

           showCalendar()
        }

        binding.fab.setOnClickListener {

            if(binding.containerFilter.visibility == View.GONE){

                binding.containerFilter.visibility =View.VISIBLE
            }else{
                binding.containerFilter.visibility = View.GONE
            }

        }

        binding.buttonPayments.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked){

                binding.buttonDate.text = "Seleccionar Fecha"
                mViewModel.filterType = AUTH_CAPTURE
                mViewModel.filterDate = ""
                mViewModel.getData()
            }
        }

        binding.buttonAll.setOnClickListener {

            if(binding.buttonAll.isChecked){
                binding.buttonDate.text = "Seleccionar Fecha"
                mViewModel.filterType = ""
                mViewModel.filterDate = ""
                mViewModel.getData()
            }
        }

        binding.buttonInvoices.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                mViewModel.filterDate = ""
                mViewModel.filterType = T3DS
                mViewModel.getData()
            }
        }
    }

    private fun showCalendar() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, yearOf, monthOfYear, dayOfMonth ->

            mViewModel.setFilterDate(yearOf,monthOfYear,dayOfMonth)
            binding.buttonAll.isChecked  = true
            mViewModel.getData()

        }, year, month, day)

        datePickerDialog.show()
    }

    private fun swipeController() {

        binding.swipe.setOnRefreshListener {

            mViewModel.getData()
        }
    }

    private fun initRecyclerView() {

        mAdapter = TransactionsViewAdapter(this)

        binding.recyclerViewItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }

        binding.recyclerViewItems.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClicKItem(item: TransactionsItem) {

        DetailDialogFragment(item).show(parentFragmentManager,"dialog-bottom-info")
    }
}