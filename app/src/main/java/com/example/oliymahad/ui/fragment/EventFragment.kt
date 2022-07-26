package com.example.oliymahad.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.oliymahad.R
import com.example.oliymahad.databinding.EventsFragmentBinding

class EventFragment:Fragment(R.layout.events_fragment) {
    private var _binding: EventsFragmentBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = EventsFragmentBinding.inflate(inflater, container, false)
        val imageList=ArrayList<SlideModel>()
        imageList.add(SlideModel("https://hackerguys.com/wp-content/uploads/2021/11/Duolingo-Mod-APK.jpg", "oliy Mahad 1"))
        imageList.add(SlideModel("https://oliymahad.uz/wp-content/uploads/2022/07/639A1987-700x430.jpg", "oliy Mahad 2"))
        imageList.add(SlideModel("https://oliymahad.uz/wp-content/uploads/2022/02/Birorta-mazhabga-bogliq-bulmasdan-amal-qilish-1.jpg", "oliy Mahad 3"))

        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)

        return binding.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}