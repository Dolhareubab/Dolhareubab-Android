package com.eshc.dolhareubab.ui.food

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.databinding.FragmentFoodBinding
import com.eshc.dolhareubab.databinding.FragmentFoodDetailBinding
import com.eshc.dolhareubab.ui.adapter.FoodAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding

    private val viewModel : FoodDetailViewModel by viewModels()
    private val args by navArgs<FoodDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = viewModel

        initViews()

        viewModel.getFoodById(args.foodId)
    }

    private fun initViews() {
        binding?.btnOk?.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW, "https://open.kakao.com/o/sF64ftTe".toUri()
            ))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}