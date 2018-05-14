package cisang.com.android_essencial.domain.retrofit

import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.domain.Response
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by WCisang on 13/05/2018.
 */
interface CarrosREST {

    @GET("tipo/{tipo}")
    fun getCarros(@Path("tipo") tipo: String): Call<List<Carro>>

    @POST("./")
    fun save(@Body carro: Carro) : Call<Response>

    @DELETE
    fun delete(@Path("id") id: Long) : Call<Response>
}