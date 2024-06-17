package site.encryptdev.travelink.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import site.encryptdev.travelink.R
import site.encryptdev.travelink.data.remote.response.RecomendationResponseItem
import site.encryptdev.travelink.databinding.ActivityDetailBinding
import site.encryptdev.travelink.dto.Places
import site.encryptdev.travelink.ui.detail.adapter.RecomendationAdapter

class DetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding
    val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainDetail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val place = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Places>(EXTRA_DATA, Places::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Places>(EXTRA_DATA)
        }

        binding.tvJudul.text = place?.placeName.toString()
        binding.tvLokasi.text = place?.city.toString()
        binding.tvDeskripsi.text = place?.description.toString()
        Glide.with(this)
            .load(place?.photo.toString())
            .centerCrop()
            .into(binding.imageView)
        binding.textView5.text = place?.rating.toString()

        binding.recyclerView2.layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false);
        viewModel.getRecomendation(place?.placeId.toString())
        viewModel.recomendation.observe(this){
            initRecom(it)
        }

    }

    fun initRecom(data : List<RecomendationResponseItem>){
        val adapter = RecomendationAdapter(data)
        binding.recyclerView2.adapter = adapter
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}