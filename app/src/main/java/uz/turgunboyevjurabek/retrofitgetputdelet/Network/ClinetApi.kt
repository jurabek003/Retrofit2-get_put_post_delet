package uz.turgunboyevjurabek.retrofitgetputdelet.Network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ClinetApi {
    const val BASE_URL="https://plans1.pythonanywhere.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitServis(context: Context):ApiServis{
        return getRetrofit().create(ApiServis::class.java)
    }
}