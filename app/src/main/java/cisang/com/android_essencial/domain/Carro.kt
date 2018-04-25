package cisang.com.android_essencial.domain

import java.io.Serializable

/**
 * Created by WCisang on 25/04/2018.
 */
class Carro : Serializable{
    var id:Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""
    var latitude = ""
    var longitude = ""

    override fun toString(): String {
        return "Carro(nome='$nome')"
    }
}