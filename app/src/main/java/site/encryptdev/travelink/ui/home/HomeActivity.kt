package site.encryptdev.travelink.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import site.encryptdev.travelink.R
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.data.remote.response.PopularResponseItem
import site.encryptdev.travelink.databinding.ActivityHomeBinding
import site.encryptdev.travelink.ui.home.adapter.HomeAdapter
import site.encryptdev.travelink.ui.home.adapter.PopularAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvHome.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false);
        binding.rvPopular.layoutManager = LinearLayoutManager(this@HomeActivity)
        viewModel.getPopular()
        viewModel.data.observe(this){
            initHomeData(it)
        }
        viewModel.getAllPlaces()
        viewModel.popularData.observe(this){
            initPopularData(it)
        }

    }

    fun initHomeData(data : List<PopularResponseItem>){
        val adapter = HomeAdapter(data)
        binding.rvHome.adapter = adapter

    }

    fun  initPopularData(data : List<AllPlacesResponseItem>){
        val adapter = PopularAdapter(data)
        binding.rvPopular.adapter = adapter
    }
}