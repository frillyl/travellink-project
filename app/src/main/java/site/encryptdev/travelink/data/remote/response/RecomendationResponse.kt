package site.encryptdev.travelink.data.remote.response

import com.google.gson.annotations.SerializedName


data class RecomendationResponseItem(

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("place_id")
	val placeId: Int,

	@field:SerializedName("price")
	val price: Any,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("similarity")
	val similarity: Any,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("rank")
	val rank: Int,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("lat")
	val lat: Any,

	@field:SerializedName("long")
	val jsonMemberLong: Any
)
