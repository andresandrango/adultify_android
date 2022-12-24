package com.example.adultifyandroid.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adultifyandroid.databinding.FragmentHomeBinding
import com.example.adultifyandroid.gameserver.World
import com.example.adultifyandroid.ui.User.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@AndroidEntryPoint
class WorldsFragment : Fragment() {

    @Inject lateinit var worldAdapterFactory: WordAdapterFactory

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val worldViewModel: WorldViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        worldViewModel.worlds.observe(viewLifecycleOwner) { worlds ->
            setupRecyclerView(worlds)
        }

        worldViewModel.counter.observe(viewLifecycleOwner) { counter ->
            Toast.makeText(context, "fetched worlds ${counter} times", Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            update()
        }

        return root
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        // TODO use serializable directly
//        outState.putString("worlds", Json.encodeToString(worldViewModel.worlds.value))
//    }

    private fun setupRecyclerView(worlds: List<World>) {
        val worldAdapter = worldAdapterFactory.create(worlds as MutableList<World>, requireContext())
        binding.recyclerView.adapter = worldAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(worldAdapter, ItemTouchHelper.LEFT, ItemTouchHelper.LEFT))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun update() {
        // Get the data synchronously or asynchronously
        // update the view with the data
        // then: (remove the code relative to the Handler)

        userViewModel.getUser().value?.let { worldViewModel.fetchWorlds(it.id) }

        binding.swipeRefresh.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}