package cisang.com.android_essencial.domain

import android.content.Context
import cisang.com.android_essencial.R
import cisang.com.android_essencial.extensions.getText
import cisang.com.android_essencial.extensions.getXml

/**
 * Created by WCisang on 25/04/2018.
 */
object CarroService {

    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
       val raw = getArquivoRaw(tipo)
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use {
            val xml = it.readText()
            val carros = parseXml(xml)
            return carros
        }
    }

    fun getArquivoRaw(tipo: TipoCarro) = when(tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

    fun parseXml(xmlString: String): List<Carro> {
        val carros = mutableListOf<Carro>()
        val xml = xmlString.getXml()
        val nodeCarros = xml.getChildren("carro")
        for (node in nodeCarros){
            val c = Carro()
            c.nome = node.getText("nome")
            c.desc = node.getText("desc")
            c.urlFoto = node.getText("url_foto")
            carros.add(c)
        }
        return carros
    }
}
