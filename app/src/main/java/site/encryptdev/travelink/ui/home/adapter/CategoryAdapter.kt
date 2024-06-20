package site.encryptdev.travelink.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import site.encryptdev.travelink.databinding.ItemCategoryBinding
import site.encryptdev.travelink.ui.category.CategoryActivity

class CategoryAdapter(private val data: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.binding.button.text = data[position].toString()

        holder.binding.button.setOnClickListener{
            val intent = Intent(holder.itemView.context, CategoryActivity::class.java)
            intent.putExtra(CategoryActivity.EXTRA_DATA, data[position].toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    class CategoryHolder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}