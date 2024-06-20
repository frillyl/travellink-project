package site.encryptdev.travelink.ui.detail.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.encryptdev.travelink.data.remote.response.RecomendationResponseItem
import site.encryptdev.travelink.databinding.ItemHomeBinding
import site.encryptdev.travelink.dto.Places
import site.encryptdev.travelink.ui.detail.DetailActivity

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
            .load(data[position].image.toString())
            .centerCrop()
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            val places = Places(data[position].placeId,data[position].image,
                data[position].name,data[position].city,
                data[position].description,
                data[position].rating)
            val move = Intent(holder.itemView.context, DetailActivity::class.java)
            move.putExtra(DetailActivity.EXTRA_DATA, places)
            holder.itemView.context.startActivity(move)
        }

    }

    class RecomHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)
}