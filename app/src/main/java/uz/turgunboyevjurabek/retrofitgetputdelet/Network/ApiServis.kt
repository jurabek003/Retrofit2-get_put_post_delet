package uz.turgunboyevjurabek.retrofitgetputdelet.Network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_Post
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_Put
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_deleted
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_get

interface ApiServis {

    @GET("plan/")
    fun getAllItem():Call<ArrayList<User_get>>

    @PUT("plan/{id}/")
    fun editItem(@Path("id") id: Int, @Body userPut: User_Put): Call<User_Put>

    @DELETE("plan/{id}/")
    fun deleteItem(@Path("id") id: Int):Call<User_deleted>

    @POST("plan/")
    fun postItem(@Body userPost: User_Post):Call<User_get>

}