package com.eshc.dolhareubab.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.databinding.FragmentFoodBinding
import com.eshc.dolhareubab.ui.MainViewModel
import com.eshc.dolhareubab.ui.adapter.FoodAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodFragment : Fragment() {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding

    private val mainViewModel : MainViewModel by activityViewModels()
    private val foodViewModel : FoodViewModel by viewModels()

    private val foodAdapter : FoodAdapter by lazy {
        FoodAdapter(){
            navigateToDetail(it.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.mainViewModel = mainViewModel

        initViews()
        initObserver()

        foodViewModel.getFoodList(
            mainViewModel.myLatitude,
            mainViewModel.myLongitude
        )
    }

    private fun initViews() {
        binding?.rvFood?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = foodAdapter
        }

        binding?.rgFood?.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb_lately -> {
                    foodViewModel.currentSort.value = Sort.LATELY
                }
                R.id.rb_distance -> {
                    foodViewModel.currentSort.value = Sort.DISTANCE
                }
                R.id.rb_expire -> {
                    foodViewModel.currentSort.value = Sort.EXPIRE
                }
            }
        }
    }

    private fun initObserver() {
        foodViewModel.foods.observe(viewLifecycleOwner){
            foodAdapter.submitList(it)
        }

        foodViewModel.loading.observe(viewLifecycleOwner){
            if(it) binding?.pbFood?.visibility = View.VISIBLE
            else binding?.pbFood?.visibility = View.INVISIBLE
        }

        foodViewModel.currentSort.observe(viewLifecycleOwner){
            foodViewModel.getFoodList(
                mainViewModel.myLatitude,
                mainViewModel.myLongitude
            )
        }
    }

    private fun navigateToDetail(id : Int){
        findNavController().navigate(
            FoodFragmentDirections.actionFragmentFoodToFragmentFoodDetail(
                id
            )
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}