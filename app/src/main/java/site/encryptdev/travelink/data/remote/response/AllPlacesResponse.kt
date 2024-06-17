package site.encryptdev.travelink.data.remote.response

import com.google.gson.annotations.SerializedName

data class AllPlacesResponse(

	@field:SerializedName("AllPlacesResponse")
	val allPlacesResponse: List<AllPlacesResponseItem>
)

data class AllPlacesResponseItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("coordinate")
	val coordinate: Coordinate,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("place_id")
	val placeId: Int
)

