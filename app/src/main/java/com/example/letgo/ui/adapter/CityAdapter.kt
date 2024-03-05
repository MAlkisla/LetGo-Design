package com.example.letgo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letgo.data.entity.City
import com.example.letgo.databinding.CityDesignBinding

class CityAdapter(private val mContext: Context, private val cityList: List<City>) :
    RecyclerView.Adapter<CityAdapter.CardViewHolder>() {

    inner class CardViewHolder(val binding: CityDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CityDesignBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val city = cityList[position]
        with(holder.binding) {
            textViewCityName.text = city.city_name
            /*
            root.setOnClickListener {
                // Example navigation action
                val action = HomepageFragmentDirections.navigateToCityDetail(city.id)
                Navigation.findNavController(root).navigate(action)
            }
            */
        }
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}
