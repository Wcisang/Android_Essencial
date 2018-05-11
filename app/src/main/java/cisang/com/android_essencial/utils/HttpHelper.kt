package cisang.com.android_essencial.utils

import okhttp3.*
import java.io.IOException

/**
 * Created by WCisang on 10/05/2018.
 */
object HttpHelper {
    val JSON = MediaType.parse("application/json; charset=utf-8")
    var client = OkHttpClient()

    fun get(url: String) : String{
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    fun post (url: String, json: String) : String {
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    fun postForm(url: String, params: Map<String,String>) : String {
        val builder = FormBody.Builder()
        for ((key, value) in params) {
            builder.add(key,value)
        }
        val body = builder.build()
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    fun delete(url: String): String {
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }

    private fun getJson(request: Request?): String{
        val response = client.newCall(request).execute()
        val responseBody = response.body()
        if (responseBody != null) {
            val json = responseBody.string()
            return json
        }
        throw IOException("ERRO NO REQUEST")
    }
}