package infix.studios.wallpapers.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

class Photo : ArrayList<Photo.PhotoItem>(){

    @Parcelize
    data class PhotoItem(
        @SerializedName("alt_description")
        val altDescription: String,
        val categories: List<@RawValue Any>,
        val color: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("current_user_collections")
        val currentUserCollections: List<@RawValue Any>,
        val description: String,
        val height: Int,
        val id: String,
        @SerializedName("liked_by_user")
        val likedByUser: Boolean,
        val likes: Int,
        val links: @RawValue Links,
        @SerializedName("promoted_at")
        val promotedAt: String,
        val sponsorship: @RawValue Sponsorship,
        @SerializedName("updated_at")
        val updatedAt: String,
        val urls: Urls,
        val user: @RawValue User,
        val width: Int
    ) : Parcelable {
        data class Links(
            val download: String,
            @SerializedName("download_location")
            val downloadLocation: String,
            val html: String,
            val self: String
        )

        data class Sponsorship(
            @SerializedName("impression_urls")
            val impressionUrls: List<String>,
            val sponsor: Sponsor,
            val tagline: String,
            @SerializedName("tagline_url")
            val taglineUrl: String
        ) {
            data class Sponsor(
                @SerializedName("accepted_tos")
                val acceptedTos: Boolean,
                val bio: String,
                @SerializedName("first_name")
                val firstName: String,
                val id: String,
                @SerializedName("instagram_username")
                val instagramUsername: String,
                @SerializedName("last_name")
                val lastName: Any,
                val links: Links,
                val location: Any,
                val name: String,
                @SerializedName("portfolio_url")
                val portfolioUrl: String,
                @SerializedName("profile_image")
                val profileImage: ProfileImage,
                @SerializedName("total_collections")
                val totalCollections: Int,
                @SerializedName("total_likes")
                val totalLikes: Int,
                @SerializedName("total_photos")
                val totalPhotos: Int,
                @SerializedName("twitter_username")
                val twitterUsername: String,
                @SerializedName("updated_at")
                val updatedAt: String,
                val username: String
            ) {
                data class Links(
                    val followers: String,
                    val following: String,
                    val html: String,
                    val likes: String,
                    val photos: String,
                    val portfolio: String,
                    val self: String
                )
    
                data class ProfileImage(
                    val large: String,
                    val medium: String,
                    val small: String
                )
            }
        }

        @Parcelize
        data class Urls(
            val full: String,
            val raw: String,
            val regular: String,
            val small: String,
            val thumb: String
        ) : Parcelable

        data class User(
            @SerializedName("accepted_tos")
            val acceptedTos: Boolean,
            val bio: String,
            @SerializedName("first_name")
            val firstName: String,
            val id: String,
            @SerializedName("instagram_username")
            val instagramUsername: String,
            @SerializedName("last_name")
            val lastName: String,
            val links: Links,
            val location: String,
            val name: String,
            @SerializedName("portfolio_url")
            val portfolioUrl: String,
            @SerializedName("profile_image")
            val profileImage: ProfileImage,
            @SerializedName("total_collections")
            val totalCollections: Int,
            @SerializedName("total_likes")
            val totalLikes: Int,
            @SerializedName("total_photos")
            val totalPhotos: Int,
            @SerializedName("twitter_username")
            val twitterUsername: Any,
            @SerializedName("updated_at")
            val updatedAt: String,
            val username: String
        ) {
            data class Links(
                val followers: String,
                val following: String,
                val html: String,
                val likes: String,
                val photos: String,
                val portfolio: String,
                val self: String
            )
    
            data class ProfileImage(
                val large: String,
                val medium: String,
                val small: String
            )
        }
    }
}