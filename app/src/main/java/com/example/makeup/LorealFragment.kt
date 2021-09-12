package com.example.makeup

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.makeup.databinding.FragmentLorealBinding


class Loreal : Fragment(), LRecyclerAdapter.onDetailItemClickListener {
    private lateinit var binding: FragmentLorealBinding
    private lateinit var lRecyclerAdapter: LRecyclerAdapter

    private val viewModel: LViewModel by viewModels<LViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loreal, container, false)
        lRecyclerAdapter = LRecyclerAdapter(this)
        binding.rvRecycler.apply {
            adapter = lRecyclerAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.dataListObservable.observe(requireActivity(), Observer{dataList->
            lRecyclerAdapter.submitList(dataList)

        })
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClick(item: LData, position: Int) {
        val textView=TextView(requireContext())
        textView.text="   id:\t ${item.id} \n   brand: \t ${item.brand} \n   name:\t ${item.name} \n   price:\t ${item.price}$"
        val alertDialog=AlertDialog.Builder(requireContext())
            .setTitle("Detail Info")
            .setView(textView)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }.create()

        alertDialog.show()
    }


}