package cisang.com.android_essencial.domain

import android.util.Base64
import cisang.com.android_essencial.domain.dao.DatabaseManager
import cisang.com.android_essencial.domain.retrofit.CarrosREST
import cisang.com.android_essencial.extensions.fromJson
import cisang.com.android_essencial.utils.HttpHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Created by WCisang on 25/04/2018.
 */
object CarroService {

    private val BASE_URL = "http://livrowebservices.com.br/rest/carros/"
    private var service: CarrosREST
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(CarrosREST::class.java)
    }

    fun getCarros(tipo: TipoCarro): List<Carro> {
        val call = service.getCarros(tipo.name)
        val carros = call.execute().body()
        return carros!!
    }

    fun save(carro: Carro): Response {
        val call = service.save(carro)
        val response = call.execute().body()
        return response!!
    }

    fun delete(carro: Carro) : Response {
        val url = "$BASE_URL/${carro.id}"
        val json = HttpHelper.delete(url)
        val response = fromJson<Response>(json)
        if (response.isOk()){
            val dao = DatabaseManager.getCarroDAO()
            dao.delete(carro)
        }
        return response
    }

    fun postFoto(file: File): Response {
        val url = "$BASE_URL/postFotoBase64"
        val bytes = file.readBytes()
        val base64 = Base64.encodeToString(bytes, Base64.NO_WRAP)
        val params = mapOf("fileName" to file.name, "base64" to base64)
        val json = HttpHelper.postForm(url, params)
        val response = fromJson<Response>(json)
        return response
    }

}
