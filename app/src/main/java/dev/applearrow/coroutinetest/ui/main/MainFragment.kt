package dev.applearrow.coroutinetest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.applearrow.coroutinetest.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        initObservers()
        initUi()
        viewModel.launchLikeCount()
        return binding.root
    }

    fun initUi() {
        binding.incButton.setOnClickListener {
            viewModel.incrementLikes()
        }
    }

    fun initObservers() {
        viewModel.likesLiveData.observe(viewLifecycleOwner) {
            binding.message.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}