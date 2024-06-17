package site.encryptdev.travelink.ui.detail

data class DetailResponse(
    val imageUrl: String,
    val title: String,
    val location: String,
    val description: String,
    val rating: Int,
    val recommendation: Float
)