package com.example.letgo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letgo.data.entity.Categories
import com.example.letgo.databinding.CategoryDesignBinding
import com.example.letgo.databinding.CategoryDesing2Binding

class CategoriesAdapter(
    private val mContext: Context,
    private val categoryList: List<Categories>,
    private val isFragmentOne: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    sealed class ViewHolderType(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        class CategoryViewHolder(val binding: CategoryDesignBinding) : ViewHolderType(binding.root)
        class CategoryViewHolder2(val binding: CategoryDesing2Binding) : ViewHolderType(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CATEGORY -> {
                val binding = CategoryDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
                ViewHolderType.CategoryViewHolder(binding)
            }
            else -> {
                val binding = CategoryDesing2Binding.inflate(LayoutInflater.from(mContext), parent, false)
                ViewHolderType.CategoryViewHolder2(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = categoryList[position]
        when (holder) {
            is ViewHolderType.CategoryViewHolder -> {
                holder.binding.textViewCategoryName.text = category.category_name
                holder.binding.imageViewCategoryIcon.setImageResource(
                    mContext.resources.getIdentifier(
                        category.category_icon, "drawable", mContext.packageName
                    )
                )
            }
            is ViewHolderType.CategoryViewHolder2 -> {
                holder.binding.textViewCategoryName.text = category.category_name
                holder.binding.imageViewCategoryIcon.setImageResource(
                    mContext.resources.getIdentifier(
                        category.category_icon, "drawable", mContext.packageName
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = categoryList.size

    override fun getItemViewType(position: Int): Int = if (isFragmentOne) VIEW_TYPE_CATEGORY else VIEW_TYPE_CATEGORY_2

    companion object {
        private const val VIEW_TYPE_CATEGORY = 0
        private const val VIEW_TYPE_CATEGORY_2 = 1
    }
}
