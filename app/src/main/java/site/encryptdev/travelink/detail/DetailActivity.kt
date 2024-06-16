package site.encryptdev.travelink.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import site.encryptdev.travelink.R

class DetailActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var locationTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var ratingTextView: TextView

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.imageView)
        titleTextView = findViewById(R.id.textView)
        locationTextView = findViewById(R.id.textView2)
        descriptionTextView = findViewById(R.id.textView3)
        ratingTextView = findViewById(R.id.textView5)

        val detailId = intent.getIntExtra("DETAIL_ID", -1)
        if (detailId != -1) {
            detailViewModel.fetchDetail(detailId)
            detailViewModel.detail.observe(this, Observer { detailResponse ->
                detailResponse?.let {
                    Glide.with(this)
                        .load(it.imageUrl)
                        .into(imageView)
                    titleTextView.text = it.title
                    locationTextView.text = it.location
                    descriptionTextView.text = it.description
                    ratingTextView.text = it.rating.toString()
                } ?: run {
                    Toast.makeText(this, "Failed to load details", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Invalid detail ID", Toast.LENGTH_SHORT).show()
        }
    }
}