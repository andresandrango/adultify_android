package com.example.adultifyandroid.ui.citizen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adultifyandroid.databinding.FragmentCitizenBinding
import com.example.adultifyandroid.gameserver.Citizen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CitizenFragment : Fragment() {

    @Inject lateinit var citizenAdapterFactory: CitizenAdapterFactory

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
            val citizenAdapter = citizenAdapterFactory.create(citizens as MutableList<Citizen>)
            binding.recyclerView.adapter = citizenAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)

            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(citizenAdapter, ItemTouchHelper.LEFT, ItemTouchHelper.LEFT))
            itemTouchHelper.attachToRecyclerView(binding.recyclerView)
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