package com.example.letgo.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.letgo.R
import com.example.letgo.data.entity.Categories
import com.example.letgo.data.entity.City
import com.example.letgo.data.entity.SaleType
import com.example.letgo.data.entity.Sales
import com.example.letgo.data.entity.SalesCreator
import com.example.letgo.databinding.FragmentHomapageBinding
import com.example.letgo.ui.adapter.CategoriesAdapter
import com.example.letgo.ui.adapter.CityAdapter
import com.example.letgo.ui.adapter.SalesAdapter

class HomepageFragment : Fragment() {
    private lateinit var binding: FragmentHomapageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomapageBinding.inflate(inflater, container, false)

        setupCategoriesRecyclerView()
        setupSalesRecyclerView()
        setupSearchView()
        setupTextViewSeeAll()
        setupTextViewLocate()

        return binding.root
    }

    private fun setupCategoriesRecyclerView() {
        val categories = resources.getStringArray(R.array.categories)
        val categoryIcons = resources.getStringArray(R.array.category_icon_drawables)

        val categoryList = ArrayList<Categories>().apply {
            categories.forEachIndexed { index, category ->
                add(Categories(index, category.uppercase(), categoryIcons[index]))
            }
        }

        binding.categoriesRv.apply {
            adapter = CategoriesAdapter(requireContext(), categoryList, true)
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        }
    }

    private fun setupSalesRecyclerView() {
        val saleList = ArrayList<Sales>().apply {
            add(
                SalesCreator.createCarSale(
                    1,
                    "Tesla Türkiye Bayi Garantili 2024 Model Tesla S",
                    listOf("${R.drawable.tesla1}",
                        "${R.drawable.tesla2}",
                        "${R.drawable.tesla3}",
                        "${R.drawable.tesla4}",
                        "${R.drawable.tesla5}"),
                    "Tesla", "S", "Performance", "2024", "Elektrik",
                    "6001", "Otomatik", "3.000.000", "Ankara", "",
                    "01.03.2024"
                )
            )
            add(
                SalesCreator.createProductSale(
                    2,
                    "Apple türkiye garantili sıfır kutusu açılmamış Iphone 15 Pro Max 1 TB Siyah",
                    listOf("${R.drawable.iphone15_pro_max_1}",
                        "${R.drawable.iphone15_pro_max_2}",
                        "${R.drawable.iphone15_pro_max_3}"),
                    "Iphone 15 Pro Max", "89.000", "Keçiören, Ankara", "Sıfır",
                    "22.02.2024"
                )
            )
            add(
                SalesCreator.createCarSale(
                    3,
                    "Honda GL1800 Gold Wing Bagger Motor 2024 ",
                    listOf("${R.drawable.gl1800_gold_wing_bagger_motocyle_1}",
                        "${R.drawable.gl1800_gold_wing_bagger_motocyle_2}",
                        "${R.drawable.gl1800_gold_wing_bagger_motocyle_3}"),
                    "Honda", "GL1800", "Gold Wing Bagger", "2024",
                    "Benzin", "9876", "Otomatik", "1.250.000", "Ankara", "",
                    "04.03.2024"
                )
            )
            add(
                SalesCreator.createProductSale(
                    4,
                    "Apple türkiye garantili sıfır kutusu açılmamış Airpods Pro",
                    listOf("${R.drawable.airpods_pro_1}",
                        "${R.drawable.airpods_pro_2}",
                        "${R.drawable.airpods_pro_3}",
                        "${R.drawable.airpods_pro_4}"),
                    "Apple Airpods Pro", "5.999", "Mamak, Ankara", "Sıfır",
                    "20.02.2024"
                )
            )
            add(
                SalesCreator.createProductSale(
                    5,
                    "Apple türkiye garantili sıfır kutusu açılmamış Apple Watch Ultra 2 49mm",
                    listOf("${R.drawable.apple_watch_49mm_alpine_ultra2_1}",
                        "${R.drawable.apple_watch_49mm_alpine_ultra2_2}",
                        "${R.drawable.apple_watch_49mm_alpine_ultra2_3}"),
                    "Apple Watch Ultra 2 49mm", "25.999", "Çayyolu, Ankara", "Sıfır",
                    "18.02.2024"
                )
            )
            add(
                SalesCreator.createProductSale(
                    6,
                    "Diesel fitness nexus plus koşu bandı",
                    listOf("${R.drawable.diesel_fitness_nexus_plus_kosu_bandi_1}",
                        "${R.drawable.diesel_fitness_nexus_plus_kosu_bandi_2}",
                        "${R.drawable.diesel_fitness_nexus_plus_kosu_bandi_3}"),
                    "Diesel Koşu Bandı", "12.999", "Sincan, Ankara", "Yeni Gibi",
                    "04.02.2024"
                )
            )
        }


        binding.salesRv.apply {
            adapter = SalesAdapter(requireContext(), saleList)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun setupSearchView() {
        binding.searchView.apply {
            setOnSearchClickListener {
                binding.textViewSearchView.visibility = View.GONE
            }

            setOnCloseListener {
                binding.textViewSearchView.visibility = View.VISIBLE
                false
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.textViewSearchView.visibility = View.GONE
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    binding.textViewSearchView.visibility = View.GONE
                    return true
                }
            })
        }
    }

    private fun setupTextViewSeeAll() {
        binding.textViewSeeAll.apply {
            val mSpannableString = SpannableString("Tümünü gör").apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(UnderlineSpan(), 0, length, 0)
            }
            text = mSpannableString
            setOnClickListener {
                val transition = HomepageFragmentDirections.categoriesTransition()
                Navigation.findNavController(it).navigate(transition)
            }
        }
    }

    private fun setupTextViewLocate() {
        binding.textViewLocate.setOnClickListener {
            val transition = HomepageFragmentDirections.locateTransition()
            Navigation.findNavController(it).navigate(transition)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.textViewSearchView.visibility = View.VISIBLE
    }
}



