package uz.turgunboyevjurabek.retrofitgetputdelet.Madels


import com.google.gson.annotations.SerializedName

data class User_Put(

    @SerializedName("sarlavha")
    var sarlavha: String,
    @SerializedName("batafsil")
    var batafsil: String,
    @SerializedName("oxirgi_muddat")
    var oxirgiMuddat: String,
    @SerializedName("zarurlik")
    var zarurlik: String,
    @SerializedName("bajarildi")
    var bajarildi: Boolean
)