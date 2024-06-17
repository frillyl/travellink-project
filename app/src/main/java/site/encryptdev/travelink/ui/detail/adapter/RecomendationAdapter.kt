package site.encryptdev.travelink.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.encryptdev.travelink.data.remote.response.RecomendationResponseItem
import site.encryptdev.travelink.databinding.ItemHomeBinding

class RecomendationAdapter(private val data: List<RecomendationResponseItem>):
    RecyclerView.Adapter<RecomendationAdapter.RecomHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecomHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecomHolder, position: Int) {
        holder.binding.tvJudul.text = data[position].name.toString()
        holder.binding.tvLokasi.text = data[position].city.toString()
        Glide.with(holder.itemView.context)
            .load("https://images.unsplash.com/photo-1516633630673-67bbad747022?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
            .centerCrop()
            .into(holder.binding.imageView)
    }

    class RecomHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)
}