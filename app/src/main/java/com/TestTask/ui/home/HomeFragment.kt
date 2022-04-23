package com.TestTask.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.TestTask.model.ListModel
import com.TestTask.R
import com.TestTask.activty.DetailActivity
import com.TestTask.adapter.RecyclerViewAdapter
import com.TestTask.databinding.FragmentHomeBinding
import com.TestTask.interfaces.ItemClickListener

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinnerList: MutableList<String?> = ArrayList<String?>()
        spinnerList.add("Bed Room")
        spinnerList.add("Living Room")
        spinnerList.add("Camera")
        spinnerList.add("Appliances")
        spinnerList.add("Storage")
        spinnerList.add("Package")
        val dataAdapterLang: ArrayAdapter<String?> = ArrayAdapter<String?>(
            requireContext(),
            R.layout.spinner_item,
            spinnerList
        )
        dataAdapterLang.setDropDownViewResource(android.R.layout.simple_list_item_1)
        binding.spinner.adapter = dataAdapterLang

        val list: MutableList<ListModel> = ArrayList()
        list.add(ListModel(R.drawable.sofa, "sofa", "$450"))
        list.add(ListModel(R.drawable.sofa, "sofa", "$450"))
        list.add(ListModel(R.drawable.sofa, "sofa", "$450"))
        list.add(ListModel(R.drawable.sofa, "sofa", "$450"))
        list.add(ListModel(R.drawable.sofa, "sofa", "$450"))

        binding.recyclerTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerRecent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerTrending.adapter = RecyclerViewAdapter(list, itemClickListener)
        binding.recyclerRecent.adapter = RecyclerViewAdapter(list, itemClickListener)
        return root
    }

    private var itemClickListener =
        object : ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {

                startActivity(
                    Intent(requireContext(), DetailActivity::class.java)
                )
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}