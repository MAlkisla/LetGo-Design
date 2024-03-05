package com.example.letgo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.letgo.R
import com.example.letgo.data.entity.Categories
import com.example.letgo.databinding.FragmentCategoriesBinding
import com.example.letgo.ui.adapter.CategoriesAdapter

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        val categoryList = ArrayList<Categories>()
        val categories = resources.getStringArray(R.array.categories)
        val categoryIcons = resources.getStringArray(R.array.category_icon_drawables)

        for (index in categories.indices) {
            val categoryName = categories[index]
            val categoryIcon = categoryIcons[index]
            val category = Categories(index, categoryName, categoryIcon)
            categoryList.add(category)
        }

        val categoriesAdapter = CategoriesAdapter(requireContext(),categoryList,false)
        binding.categoriesRv2.adapter = categoriesAdapter
        binding.categoriesRv2.layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)

        binding.imageViewCloseButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }
}