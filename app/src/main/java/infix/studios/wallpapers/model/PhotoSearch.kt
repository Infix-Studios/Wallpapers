package infix.studios.wallpapers.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PhotoSearch(
    val results: List<Result>,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
): Parcelable {
    @Parcelize
    data class Result(
            @SerializedName("alt_description")
        val altDescription: String,
            val categories: @RawValue List<Any>,
            val color: String,
            @SerializedName("created_at")
        val createdAt: String,
            @SerializedName("current_user_collections")
        val currentUserCollections: @RawValue List<Any>,
            val description:@RawValue Any,
            val height: Int,
            val id: String,
            @SerializedName("liked_by_user")
        val likedByUser: Boolean,
            val likes: Int,
            val links: Links,
            @SerializedName("promoted_at")
        val promotedAt: @RawValue Any,
            val sponsorship: Sponsorship,
            val tags: List<Tag>,
            @SerializedName("updated_at")
        val updatedAt: String,
            val urls: Urls,
            val user: User,
            val width: Int
    ) : Parcelable {
        @Parcelize
        data class Links(
            val download: String,
            @SerializedName("download_location")
            val downloadLocation: String,
            val html: String,
            val self: String
        ): Parcelable

        @Parcelize
        data class Sponsorship(
            @SerializedName("impression_urls")
            val impressionUrls: List<String>,
            val sponsor:@RawValue Sponsor,
            val tagline: String,
            @SerializedName("tagline_url")
            val taglineUrl: String
        ): Parcelable {
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
        data class Tag(
            val source: @RawValue Source,
            val title: String,
            val type: String
        ): Parcelable {
            data class Source(
                val ancestry: Ancestry,
                @SerializedName("cover_photo")
                val coverPhoto: CoverPhoto,
                val description: String,
                @SerializedName("meta_description")
                val metaDescription: String,
                @SerializedName("meta_title")
                val metaTitle: String,
                val subtitle: String,
                val title: String
            ) {
                data class Ancestry(
                    val category: Category,
                    val subcategory: Subcategory,
                    val type: Type
                ) {
                    data class Category(
                        @SerializedName("pretty_slug")
                        val prettySlug: String,
                        val slug: String
                    )

                    data class Subcategory(
                        @SerializedName("pretty_slug")
                        val prettySlug: String,
                        val slug: String
                    )

                    data class Type(
                        @SerializedName("pretty_slug")
                        val prettySlug: String,
                        val slug: String
                    )
                }

                data class CoverPhoto(
                    @SerializedName("alt_description")
                    val altDescription: String,
                    val categories: List<Any>,
                    val color: String,
                    @SerializedName("created_at")
                    val createdAt: String,
                    @SerializedName("current_user_collections")
                    val currentUserCollections: List<Any>,
                    val description: Any,
                    val height: Int,
                    val id: String,
                    @SerializedName("liked_by_user")
                    val likedByUser: Boolean,
                    val likes: Int,
                    val links: Links,
                    @SerializedName("promoted_at")
                    val promotedAt: String,
                    val sponsorship: Any,
                    @SerializedName("updated_at")
                    val updatedAt: String,
                    val urls: Urls,
                    val user: User,
                    val width: Int
                ) {
                    data class Links(
                        val download: String,
                        @SerializedName("download_location")
                        val downloadLocation: String,
                        val html: String,
                        val self: String
                    )

                    data class Urls(
                        val full: String,
                        val raw: String,
                        val regular: String,
                        val small: String,
                        val thumb: String
                    )

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
            }
        }

        @Parcelize
        data class Urls(
            val full: String,
            val raw: String,
            val regular: String,
            val small: String,
            val thumb: String
        ): Parcelable

        @Parcelize
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
            val lastName: @RawValue Any,
            val links: @RawValue Links,
            val location: @RawValue Any,
            val name: String,
            @SerializedName("portfolio_url")
            val portfolioUrl: String,
            @SerializedName("profile_image")
            val profileImage: @RawValue ProfileImage,
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
        ): Parcelable {
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