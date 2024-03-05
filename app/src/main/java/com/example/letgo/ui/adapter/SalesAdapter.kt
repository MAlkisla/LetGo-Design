package com.example.letgo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.letgo.data.entity.SaleType
import com.example.letgo.data.entity.Sales
import com.example.letgo.databinding.SalesDesignBinding
import com.example.letgo.ui.fragment.HomepageFragmentDirections

class SalesAdapter(var mContext: Context, var saleList: List<Sales>) :
    RecyclerView.Adapter<SalesAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarim: SalesDesignBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = SalesDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val sale = saleList.get(position)
        val t = holder.tasarim

        if (sale.saleType == SaleType.CAR) {
            // Configure UI for car sale
            t.imageViewBadgeOneCikan.visibility = View.GONE
            t.imageViewBadgeOtoPlus.visibility = View.VISIBLE
            t.textViewcCardLocate.visibility = View.GONE
            t.textViewYearAndKm.visibility = View.VISIBLE
            t.buttonCall.visibility = View.VISIBLE
        } else {
            // Configure UI for product sale
            t.imageViewBadgeOneCikan.visibility = View.VISIBLE
            t.imageViewBadgeOtoPlus.visibility = View.GONE
            t.textViewYearAndKm.visibility = View.GONE
            t.buttonCall.visibility = View.GONE
            t.textViewcCardLocate.visibility = View.VISIBLE
        }

        t.textViewDescription.text = sale.saleDesp

        t.textViewMoney.text = sale.saleMoneyText + " TL"

        t.imageViewSalesImage.setImageResource(
            mContext.resources.getIdentifier(
                sale.saleImageList[0],
                "drawable",
                mContext.packageName
            )
        )

        // Handle click event based on sale type
        t.cardViewSale.setOnClickListener {
            val direction = if (sale.saleType == SaleType.CAR) {
                HomepageFragmentDirections.carDetailTransition(sale = sale)
            } else {
                HomepageFragmentDirections.productDetailTransition(sale = sale)
            }
            Navigation.findNavController(it).navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return saleList.size
    }
}
