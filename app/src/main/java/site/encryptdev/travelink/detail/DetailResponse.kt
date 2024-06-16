package site.encryptdev.travelink.detail

data class DetailResponse(
    val imageUrl: String,
    val title: String,
    val location: String,
    val description: String,
    val rating: Int,
    val recommendation: Float
)