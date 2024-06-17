package site.encryptdev.travelink.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.databinding.ItemPopularBinding
import site.encryptdev.travelink.dto.Places
import site.encryptdev.travelink.ui.detail.DetailActivity

class PopularAdapter(private val data: List<AllPlacesResponseItem>): RecyclerView.Adapter<PopularAdapter.PopularHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.PopularHolder {
        val binding =  ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularHolder(binding);
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularHolder, position: Int) {
        holder.binding.tvJudul.text = data[position].placeName.toString()
        holder.binding.tvLokasi.text = data[position].city.toString()
        holder.binding.tvDeskripsi.text = data[position].description.toString()
        Glide.with(holder.itemView.context)
            .load(data[position].image)
            .centerCrop()
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            val places = Places(data[position].placeId,data[position].image,
                data[position].placeName,data[position].city,
                data[position].description,
                data[position].rating)
            val move = Intent(holder.itemView.context, DetailActivity::class.java)
            move.putExtra(DetailActivity.EXTRA_DATA, places)
            holder.itemView.context.startActivity(move)
        }

    }

    override fun getItemCount(): Int = data.size

    class PopularHolder(var binding: ItemPopularBinding): RecyclerView.ViewHolder(binding.root) {

    }
}