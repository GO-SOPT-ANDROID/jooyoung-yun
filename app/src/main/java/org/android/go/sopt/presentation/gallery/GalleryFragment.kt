package org.android.go.sopt.presentation.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.adapter.GalleryAdapter

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "not null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerHome.adapter = GalleryAdapter().apply {
            setItemList(listOf(R.drawable.snoopy, R.drawable.snoopy, R.drawable.snoopy))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}