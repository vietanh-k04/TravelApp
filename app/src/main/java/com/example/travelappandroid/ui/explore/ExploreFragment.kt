package com.example.travelappandroid.ui.explore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.travelappandroid.NavGraphDirections
import com.example.travelappandroid.data.model.RegionItem
import com.example.travelappandroid.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding

    private val viewModel: ExploreViewModel by viewModels()

    private lateinit var adapter: PlaceStaggeredAdapter

    private lateinit var regionAdapter: RegionAdapter

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
        setUpRegion()
    }

    fun setUpAdapter() {
        adapter = PlaceStaggeredAdapter { place ->
            val action = NavGraphDirections.actionGlobalPlaceDetailFragment(place?.id ?: "")
            findNavController().navigate(action)
        }
        binding.exploreList.rvPlaceStaggered.adapter = adapter
    }

    fun setUpObserve() {
        viewModel.places.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    private fun setUpSearchLogic() {
        binding.exploreHeader.edtSearchExplore.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchPlaces(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setUpRegion() {
        regionAdapter = RegionAdapter(dataRegionList()) { regionCode->
            viewModel.filterByRegion(regionCode?.regionCode ?: "")
        }

        binding.exploreList.rvRegion.adapter = regionAdapter
    }

    private fun dataRegionList() : List<RegionItem> {
        return listOf(
            RegionItem(
                title = "Miền Bắc",
                imageUrl = "https://res.cloudinary.com/djngmdetx/image/upload/v1765196150/bac_evevwg.webp",
                regionCode = "North"
            ),
            RegionItem(
                title = "Miền Trung",
                imageUrl = "https://res.cloudinary.com/djngmdetx/image/upload/v1765196150/trung_iletz9.webp",
                regionCode = "Central"
            ),
            RegionItem(
                title = "Miền Nam",
                imageUrl = "https://res.cloudinary.com/djngmdetx/image/upload/v1765196150/nam_yvnxni.webp",
                regionCode = "South"
            )
        )
    }
}