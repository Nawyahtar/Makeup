package com.example.makeup

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.makeup.databinding.FragmentMaybellineBinding

class MaybellineFragment : Fragment() ,MaybellineRecyclerAdapter.onDetailItemClickListener{
     private lateinit var binding:FragmentMaybellineBinding
     private lateinit var maybellineRecyclerAdapter: MaybellineRecyclerAdapter

    private val viewModel: MaybellintViewModel by viewModels<MaybellintViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_maybelline,container,false)
        maybellineRecyclerAdapter=MaybellineRecyclerAdapter(this)
        binding.rvGlossier.apply {
            adapter=maybellineRecyclerAdapter
            layoutManager=GridLayoutManager(requireContext(),2)
        }

        viewModel.dataListObservable.observe(requireActivity(), Observer{dataList->
           maybellineRecyclerAdapter.submitList(dataList)

        })


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClick(item: Data, position: Int) {
        val textView= TextView(requireContext())
        textView.text="   id:\t ${item.id} \n   brand: \t ${item.brand} \n   name:\t ${item.name} \n   price:\t ${item.price}$"
        val alertDialog= AlertDialog.Builder(requireContext())
            .setTitle("Detail Info")
            .setView(textView)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }.create()

        alertDialog.show()
    }

}