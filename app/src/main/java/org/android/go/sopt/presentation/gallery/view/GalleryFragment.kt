package org.android.go.sopt.presentation.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.presentation.gallery.viewmodel.GalleryViewModel
import org.android.go.sopt.util.ContentUriRequestBody

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "not null" }

    private val viewModel by viewModels<GalleryViewModel>()
    private val launcher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        it->
        it?.let { it1 -> ContentUriRequestBody(requireContext(), it1) }
            ?.let { it2 -> viewModel.setRequestBody(it2) }
    }

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
        /*binding.pagerHome.adapter = GalleryAdapter().apply {
            setItemList(listOf(R.drawable.snoopy, R.drawable.snoopy, R.drawable.snoopy))
        }*/
        binding.btnGalleryPickImage.setOnClickListener{
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
        viewModel.uploadProfileImage()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}