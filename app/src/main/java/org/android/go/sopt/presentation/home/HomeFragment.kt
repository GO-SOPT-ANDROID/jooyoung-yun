package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.adapter.ProfileAdapter
import org.android.go.sopt.adapter.TitleAdapter
import org.android.go.sopt.data.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "이렇게 쓰면 안됩니다~" }

    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileAdapter = ProfileAdapter(requireContext())
        val titleAdapter = TitleAdapter(requireContext())

        profileAdapter.setProfileList(viewModel.mockProfileList)

        val concatAdapter = ConcatAdapter(titleAdapter, profileAdapter)

        binding.recyclerview.adapter = concatAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}