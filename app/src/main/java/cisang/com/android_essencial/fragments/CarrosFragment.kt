package cisang.com.android_essencial.fragments

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cisang.com.android_essencial.R
import cisang.com.android_essencial.activity.CarroActivity
import cisang.com.android_essencial.adapter.CarroAdapter
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.domain.CarroService
import cisang.com.android_essencial.domain.TipoCarro
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.startActivity

class CarrosFragment : BaseFragment() {

    private var tipo : TipoCarro = TipoCarro.classicos
    private var carros = listOf<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipo = arguments?.getSerializable("tipo") as TipoCarro
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_carros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    private fun taskCarros() {
        this.carros = CarroService.getCarros(context!!, tipo)
        recyclerView.adapter = CarroAdapter(carros) {
            activity?.startActivity<CarroActivity>("carro" to it)
        }
    }


}
