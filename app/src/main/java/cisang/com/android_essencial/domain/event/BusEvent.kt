package cisang.com.android_essencial.domain.event

import cisang.com.android_essencial.domain.Carro

/**
 * Created by WCisang on 23/05/2018.
 */
data class SaveCarroEvent(val carro:Carro)
data class FavoritoEvent(val carro: Carro)