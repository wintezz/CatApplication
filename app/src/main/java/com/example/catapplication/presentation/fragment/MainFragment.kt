package com.example.catapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapplication.MainApp
import com.example.catapplication.R
import com.example.catapplication.databinding.FragmentMainBinding
import com.example.catapplication.presentation.adapter.CatAdapter
import com.example.catapplication.presentation.model.CatViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val catBinding get() = binding!!
    private val catViewModel: CatViewModel by activityViewModels {
        CatViewModel.MainViewModelFactory(MainApp.dataBase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(
            inflater, container, false
        )

        val itemAdapter = CatAdapter(
            clickListener = { catViewModel.onItemClick(it) },
            favoriteClickListener = { catViewModel.onFavoriteItemClick(it) }
        )

        itemAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        initRecyclerView(itemAdapter)
        initAdapter(itemAdapter)
        initObservers()

        return catBinding.root
    }

    private fun initObservers() {
        catViewModel.navigate.observe(viewLifecycleOwner) {
            when (it) {
                is CatViewModel.Navigate.ToDetail -> parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.card_flip_left_in,
                        R.anim.card_flip_left_out
                    )
                    .addToBackStack(null)
                    .replace(
                        R.id.fragmentContainer,
                        CatInfoFragment.newInstance(it.cat)
                    )
                    .commit()
                is CatViewModel.Navigate.Back -> TODO()
            }
        }
    }

    private fun initAdapter(itemAdapter: CatAdapter) {
        lifecycleScope.launch {
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

    companion object {
        @JvmStatic
        fun newInstance(listCat: ArrayList<String>) =
            CatInfoFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("listCat", listCat)
                }
            }
    }
}
