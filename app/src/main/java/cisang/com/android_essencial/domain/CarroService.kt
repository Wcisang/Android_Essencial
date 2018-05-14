package cisang.com.android_essencial.domain

import cisang.com.android_essencial.domain.retrofit.CarrosREST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        val call = service.delete(carro.id)
        val response = call.execute().body()
        return response!!
    }

}
