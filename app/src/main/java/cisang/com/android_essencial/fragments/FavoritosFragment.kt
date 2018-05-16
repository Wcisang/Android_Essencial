package cisang.com.android_essencial.fragments

import cisang.com.android_essencial.activity.CarroActivity
import cisang.com.android_essencial.adapter.CarroAdapter
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.domain.FavoritosService
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.startActivity

/**
 * Created by WCisang on 15/05/2018.
 */
class FavoritosFragment : CarrosFragment() {

    override fun taskCarros() {
        doAsync {
            carros = FavoritosService.getCarros()

            uiThread {
                recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it)}
            }
        }
    }

    override fun onClickCarro(carro: Carro) {
        activity?.startActivity<CarroActivity>("carro" to carro)
    }

}