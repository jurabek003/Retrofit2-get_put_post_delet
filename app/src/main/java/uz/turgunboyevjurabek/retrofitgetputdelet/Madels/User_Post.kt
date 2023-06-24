package uz.turgunboyevjurabek.retrofitgetputdelet.Madels


import com.google.gson.annotations.SerializedName

data class User_Post(
    @SerializedName("sarlavha")
    val sarlavha: String,
    @SerializedName("batafsil")
    val batafsil: String,
    @SerializedName("oxirgi_muddat")
    val oxirgiMuddat: String,
    @SerializedName("zarurlik")
    val zarurlik: String,
    @SerializedName("bajarildi")
    val bajarildi: Boolean
)