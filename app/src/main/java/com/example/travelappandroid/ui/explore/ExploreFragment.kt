package com.example.travelappandroid.ui.explore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.travelappandroid.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding

    private val viewModel: ExploreViewModel by viewModels()

    private lateinit var adapter: PlaceStaggeredAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObserve()
        setUpSearchLogic()
    }

    fun setUpAdapter() {
        adapter = PlaceStaggeredAdapter()
        binding.explorePlaces.rvPlaceStaggered.adapter = adapter
    }

    fun setUpObserve() {
        viewModel.places.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    private fun setUpSearchLogic() {
        binding.exploreTitle.btnSearchPlace.setOnClickListener {
            val isVisible = binding.exploreSearch.searchContainer.isVisible
            binding.exploreSearch.searchContainer.isVisible = !isVisible

            if (!isVisible) {
                binding.exploreSearch.edtSearchExplore.requestFocus()
            } else {
                binding.exploreSearch.edtSearchExplore.text.clear()
            }
        }

        binding.exploreSearch.edtSearchExplore.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchPlaces(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}