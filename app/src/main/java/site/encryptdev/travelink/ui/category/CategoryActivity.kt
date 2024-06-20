package site.encryptdev.travelink.ui.category

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import site.encryptdev.travelink.R
import site.encryptdev.travelink.data.remote.response.AllPlacesResponseItem
import site.encryptdev.travelink.databinding.ActivityCategoryBinding
import site.encryptdev.travelink.ui.home.adapter.PopularAdapter

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding
    private val viewModel : CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        viewModel.getData(intent.getStringExtra(EXTRA_DATA)!!)
        binding.tvCategory.text = "Wisata Category ${intent.getStringExtra(EXTRA_DATA)}"
        viewModel.data.observe(this){
            initData(it)
        }

    }

    fun initData(data: List<AllPlacesResponseItem>){
        val adapter = PopularAdapter(data)
        binding.rvCategory.adapter = adapter
    }

     companion object{
         const val EXTRA_DATA = "extra_data"
     }
}