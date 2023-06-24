package uz.turgunboyevjurabek.retrofitgetputdelet.Madels


import com.google.gson.annotations.SerializedName

data class User_deleted(
    @SerializedName("id")
    val id:Int,
    @SerializedName("bajarildi")
    val bajarildi: Boolean,
    @SerializedName("batafsil")
    val batafsil: String,
    @SerializedName("oxirgi_muddat")
    val oxirgiMuddat: String,
    @SerializedName("sarlavha")
    val sarlavha: String,
    @SerializedName("zarurlik")
    val zarurlik: String
)