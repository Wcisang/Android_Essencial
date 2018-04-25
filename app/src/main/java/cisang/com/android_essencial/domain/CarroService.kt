package cisang.com.android_essencial.domain

import android.content.Context

/**
 * Created by WCisang on 25/04/2018.
 */
object CarroService {
    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
        val tipoString = context.getString(tipo.string)
        val carros = mutableListOf<Carro>()
        for (i in 1..20) {
            val c = Carro()
            c.nome = "Carro $tipoString: $i"
            c.desc = "Desc " + i
            c.urlFoto = "https://image.webmotors.com.br/_fotos/anunciousados/gigante/2018/201802/20180228/ferrari-458-italia-4.5-v8-gasolina-f1dct-wmimagem08420621742.jpg"
            carros.add(c)
        }
        return carros
    }
}
