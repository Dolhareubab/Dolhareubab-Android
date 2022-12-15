package com.eshc.dolhareubab.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.databinding.FragmentMypageBinding
import com.eshc.dolhareubab.ui.adapter.MypageFoodAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding

    private val viewModel : MypageViewModel by viewModels()

    private val mypageFoodAdapter : MypageFoodAdapter by lazy {
        MypageFoodAdapter(){
            viewModel.updateFood(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = viewModel

        initViews()
        initObserver()

    }

    private fun initViews() {
        binding?.rvMypage?.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = mypageFoodAdapter
        }

        binding?.tlMypage?.let { tabLayout ->
            resources.getStringArray(R.array.mypage_tab).forEach {
                tabLayout.addTab(tabLayout.newTab().setText(it))
            }
            tabLayout.selectTab(tabLayout.getTabAt(-1))
            tabLayout.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if(tab?.position == 0) viewModel.tabState.value = MypageTabUiState.SHARE
                    else viewModel.tabState.value = MypageTabUiState.FOOD
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }

    private fun initObserver() {
        viewModel.mypageFoods.observe(viewLifecycleOwner){
            mypageFoodAdapter.submitList(
                it
            )
        }
        viewModel.tabState.observe(viewLifecycleOwner){
            viewModel.getFoodList(it)
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it) binding?.pbMypage?.visibility = View.VISIBLE
            else binding?.pbMypage?.visibility = View.INVISIBLE
        }

        viewModel.changedFoodIndex.observe(viewLifecycleOwner){
            mypageFoodAdapter.notifyItemChanged(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}