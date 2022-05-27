package com.example.catapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapplication.domain.CatViewModel
import com.example.catapplication.R
import com.example.catapplication.presentation.adapter.CatAdapter
import com.example.catapplication.databinding.FragmentMainBinding
import com.example.catapplication.presentation.interfaces.Navigator
import kotlinx.coroutines.flow.collectLatest

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val catBinding get() = binding!!
    private val catViewModel by viewModels<CatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(
            inflater, container, false)

        val itemAdapter = CatAdapter(object : Navigator {
            override fun openCatInfoFragment(parameter1: String, parameter2: String) {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.card_flip_left_in,
                        R.anim.card_flip_left_out
                    )
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer,
                        CatInfoFragment.newInstance(parameter1, parameter2)
                    )
                    .commit()
            }
        })

        itemAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        initRecyclerView(itemAdapter)
        initAdapter(itemAdapter)

        return catBinding.root
    }

    private fun initAdapter(itemAdapter: CatAdapter) {
        lifecycleScope.launchWhenCreated {
            catViewModel.catList.collectLatest {
                itemAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView(itemAdapter: CatAdapter) {
        catBinding.recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}