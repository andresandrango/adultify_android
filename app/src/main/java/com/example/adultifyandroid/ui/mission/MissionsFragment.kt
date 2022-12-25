package com.example.adultifyandroid.ui.mission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adultifyandroid.R
import com.example.adultifyandroid.databinding.FragmentMissionsBinding
import com.example.adultifyandroid.gameserver.Mission
import com.example.adultifyandroid.gameserver.World
import com.example.adultifyandroid.ui.User.UserViewModel
import com.example.adultifyandroid.ui.world.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MissionsFragment : Fragment() {

    @Inject lateinit var missionAdapterFactory: MissionAdapterFactory

    private var _binding: FragmentMissionsBinding? = null
    private val binding get() = _binding!!

    private val missionViewModel: MissionViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMissionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        missionViewModel.missions.observe(viewLifecycleOwner) { missions ->
            setupRecyclerView(missions)
        }

        binding.missionsSwipeRefresh.setOnRefreshListener {
            update()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // VM
        navController = Navigation.findNavController(view)

        binding.addMissionFloatingButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_missions_to_addMissionFragment)
        }
    }

    private fun setupRecyclerView(missions: List<Mission>) {
        val missionAdapter = missionAdapterFactory.create(missions as MutableList<Mission>)
        binding.missionsRecyclerView.adapter = missionAdapter
        binding.missionsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun update() {
        userViewModel.getUser().value?.let { missionViewModel.fetchMissions(it.id) }

        binding.missionsSwipeRefresh.isRefreshing = false
    }
}