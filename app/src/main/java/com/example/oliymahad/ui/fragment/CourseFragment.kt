package com.example.oliymahad.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oliymahad.R
import com.example.oliymahad.adapter.CoursesAdapter
import com.example.oliymahad.databinding.CoursesFragmentBinding
import com.example.oliymahad.resource.Resource
import com.example.oliymahad.ui.MainActivity
import com.example.oliymahad.ui.vm.CoursesViewModel
import com.google.android.material.snackbar.Snackbar

class CourseFragment : Fragment(R.layout.courses_fragment) {
    private var _binding: CoursesFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CoursesViewModel
    private lateinit var adapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoursesFragmentBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModelCourse
        adapter = CoursesAdapter()
        binding.coursesRecycler.adapter = adapter

        if (isOnline(binding.root.context)){
            viewModel.getCourses()
        }else{
            binding.pbCourseFragment.visibility=View.INVISIBLE
            binding.showOffline.visibility=View.VISIBLE
        }

        viewModel.allCourses.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    removePB(binding)
                    response.data?.let { data ->
                        adapter.differ.submitList(data.content)
                    }
                }
                is Resource.Error -> {
                    removePB(binding)
                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    showPB(binding)
                }
            }
        }
        return binding.root
    }

    private fun removePB(binding: CoursesFragmentBinding) {
        binding.pbCourseFragment.visibility = View.INVISIBLE
    }

    private fun showPB(binding: CoursesFragmentBinding) {
        binding.pbCourseFragment.visibility = View.VISIBLE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}