package com.example.catapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catApllication.R
import com.example.catApllication.databinding.FragmentMainBinding
import com.example.catapplication.presentation.adapter.CatAdapter
import com.example.catapplication.presentation.model.CatUiModel
import com.example.catapplication.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding ?: throw Throwable("MainFragment binding is not initialized")

    private val catViewModel: CatViewModel by viewModels()

    private val catAdapter = CatAdapter(
        clickListener = ::initObservers,
        favoriteClickListener = { catViewModel.onFavoriteItemClick(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserverCat()
        initRecyclerView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initObservers(item: CatUiModel) {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.card_flip_left_in,
                R.anim.card_flip_left_out
            )
            .addToBackStack("backStack")
            .replace(
                R.id.fragmentContainer,
                DetailFragment.newInstance(item)
            )
            .commit()
    }

    private fun setupObserverCat() {
        viewLifecycleOwner.lifecycleScope.launch {
            catViewModel.catList.collectLatest {
                catAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = catAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(listCat: ArrayList<String>) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("listCat", listCat)
                }
            }
    }
}