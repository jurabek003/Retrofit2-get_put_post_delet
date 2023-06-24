package uz.turgunboyevjurabek.retrofitgetputdelet.Madels


import com.google.gson.annotations.SerializedName

data class User_get(
    @SerializedName("id")
    var id:Int?,
    @SerializedName("bajarildi")
    var bajarildi: Boolean,
    @SerializedName("batafsil")
    var batafsil: String,
    @SerializedName("oxirgi_muddat")
    var oxirgiMuddat: String,
    @SerializedName("sarlavha")
    var sarlavha: String,
    @SerializedName("zarurlik")
    var zarurlik: String
)