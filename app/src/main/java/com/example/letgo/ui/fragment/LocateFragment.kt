package com.example.letgo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.letgo.R
import com.example.letgo.data.entity.City
import com.example.letgo.databinding.FragmentLocateBinding
import com.example.letgo.ui.adapter.CityAdapter

class LocateFragment : Fragment() {
    private lateinit var binding: FragmentLocateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocateBinding.inflate(inflater, container, false)

        val cityList = ArrayList<City>()
        val turkishCities = resources.getStringArray(R.array.turkish_cities)
        for ((index, cityName) in turkishCities.withIndex()) {
            val city = City(index, cityName)
            cityList.add(city)
        }
        val cityAdapter = CityAdapter(requireContext(), cityList)
        binding.cityRv.adapter = cityAdapter
        binding.cityRv.layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)

        binding.imageViewCloseButton.setOnClickListener {
            val transition = LocateFragmentDirections.homepageTransition()
            Navigation.findNavController(it).navigate(transition)
        }
        return binding.root
    }
}