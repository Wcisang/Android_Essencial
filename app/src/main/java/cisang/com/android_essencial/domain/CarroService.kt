package cisang.com.android_essencial.domain

import cisang.com.android_essencial.extensions.fromJson
import cisang.com.android_essencial.extensions.toJson
import cisang.com.android_essencial.utils.HttpHelper

/**
 * Created by WCisang on 25/04/2018.
 */
object CarroService {

    private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

    fun getCarros(tipo: TipoCarro): List<Carro> {
        val url = "$BASE_URL/tipo/${tipo.name}"
        val json = HttpHelper.get(url)
        val carros = fromJson<List<Carro>>(json)
        return carros
    }

    fun save(carro: Carro): Response {
        val json = HttpHelper.post(BASE_URL, carro.toJson())
        val response = fromJson<Response>(json)
        return response
    }

    fun delete(carro: Carro) : Response {
        val url = "$BASE_URL/${carro.id}"
        val json = HttpHelper.delete(url)
        val response = fromJson<Response>(json)
        return response
    }

}
