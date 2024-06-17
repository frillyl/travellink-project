package site.encryptdev.travelink.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.encryptdev.travelink.data.remote.response.PopularResponseItem
import site.encryptdev.travelink.databinding.ItemHomeBinding

class HomeAdapter(private val listData: List<PopularResponseItem>) :
    RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.tvJudul.text = listData[position].placeName.toString()
        holder.binding.tvLokasi.text = listData[position].city.toString()
        Glide.with(holder.itemView.context)
            .load(listData[position].image)
            .centerCrop()
            .into(holder.binding.imageView)
    }

    class HomeHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)
}