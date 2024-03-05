package com.example.letgo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.letgo.R
import com.example.letgo.data.entity.ImageItem
import com.example.letgo.databinding.FragmentCarDetailBinding
import com.example.letgo.ui.adapter.ImageAdapter
import java.util.UUID
import android.util.Log
class CarDetailFragment : Fragment() {
    private lateinit var binding: FragmentCarDetailBinding
    private lateinit var viewpager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply { setMargins(8, 0, 8, 0) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        viewpager2 = binding.viewpager2
        val bundle: CarDetailFragmentArgs by navArgs()
        val sale = bundle.sale
        val saleImageList = sale.saleImageList
        val imageList = arrayListOf<ImageItem>()


        saleImageList.forEach { imageUrl ->
            imageList.add(ImageItem(UUID.randomUUID().toString(), "android.resource://" + requireContext().packageName +"/" + imageUrl))
        }

        val imageAdapter = ImageAdapter()
        viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val sliderDotLL = binding.slider
        val dotsImage = Array(imageList.size){
            ImageView(this.requireContext())
        }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            sliderDotLL.addView(it,params)
        }

        //default first dot seleted
        dotsImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position==index){
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    }
                    else{
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }

        viewpager2.registerOnPageChangeCallback(pageChangeListener)

        binding.textViewCarKm.text = sale.saleCarKm
        binding.textViewCarDesp.text = sale.saleDesp
        binding.textViewBrandName.text = sale.saleBrandName
        binding.textViewCarFuelType.text = sale.saleFuelType
        binding.textViewCarGearType.text = sale.saleGearType
        binding.textViewCarLocate.text = sale.saleLocate
        binding.textViewCarMoney.text = sale.saleMoneyText + " TL"
        binding.textViewCarSaleDate.text = sale.saleSaleDate
        binding.textViewCarYear.text = "[ " + sale.saleCarYear + " ]"
        binding.textViewModelName.text = sale.saleCarModel
        binding.textViewCarPackageName.text = sale.saleCarPackage

        binding.imageViewBackPage.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewpager2.unregisterOnPageChangeCallback(pageChangeListener)
    }
}