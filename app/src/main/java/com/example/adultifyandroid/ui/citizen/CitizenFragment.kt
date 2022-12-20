package com.example.adultifyandroid.ui.citizen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adultifyandroid.databinding.FragmentCitizenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitizenFragment : Fragment() {

    private var _binding: FragmentCitizenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CitizenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCitizenBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.citizens.observe(viewLifecycleOwner) { citizens ->
            val citizenAdapter = CitizenAdapter(citizens)
            binding.recyclerView.adapter = citizenAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        binding.swipeRefreshCitizens.setOnRefreshListener {
            update()
        }

        return root
    }

    private fun update() {
        // Get the data synchronously or asynchronously
        // update the view with the data
        // then: (remove the code relative to the Handler)

        viewModel.refresh()

        binding.swipeRefreshCitizens.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}