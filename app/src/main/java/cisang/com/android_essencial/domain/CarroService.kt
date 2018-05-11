package cisang.com.android_essencial.domain

import cisang.com.android_essencial.R
import cisang.com.android_essencial.extensions.fromJson
import cisang.com.android_essencial.extensions.getText
import cisang.com.android_essencial.extensions.getXml
import java.net.URL

/**
 * Created by WCisang on 25/04/2018.
 */
object CarroService {

    fun getCarros(tipo: TipoCarro): List<Carro> {
        val url = "http://livrowebservices.com.br/rest/carros/tipo/${tipo.name}"
        val json = URL(url).readText()
        val carros = fromJson<List<Carro>>(json)
        return carros
    }

    fun getArquivoRaw(tipo: TipoCarro) = when (tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

    fun parseXml(xmlString: String): List<Carro> {
        val carros = mutableListOf<Carro>()
        val xml = xmlString.getXml()
        val nodeCarros = xml.getChildren("carro")
        for (node in nodeCarros) {
            val c = Carro()
            c.nome = node.getText("nome")
            c.desc = node.getText("desc")
            c.urlFoto = node.getText("url_foto")
            carros.add(c)
        }
        return carros
    }


}
