package cisang.com.android_essencial.domain

/**
 * Created by WCisang on 10/05/2018.
 */
data class Response (val id: Long, val status: String, val msg:String, val url:String){
    fun isOk() = "OK".equals(status, ignoreCase = true)
}