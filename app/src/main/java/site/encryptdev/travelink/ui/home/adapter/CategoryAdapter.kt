package site.encryptdev.travelink.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.encryptdev.travelink.databinding.ItemCategoryBinding

class CategoryAdapter(private val data: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.binding.button.text = data[position].toString()
    }

    class CategoryHolder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}