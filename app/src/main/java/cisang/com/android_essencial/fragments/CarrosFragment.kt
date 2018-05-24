package cisang.com.android_essencial.fragments

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cisang.com.android_essencial.R
import cisang.com.android_essencial.activity.CarroActivity
import cisang.com.android_essencial.adapter.CarroAdapter
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.domain.CarroService
import cisang.com.android_essencial.domain.TipoCarro
import cisang.com.android_essencial.domain.event.SaveCarroEvent
import cisang.com.android_essencial.utils.AndroidUtils
import kotlinx.android.synthetic.main.fragment_carros.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

open class CarrosFragment : BaseFragment() {

    private var tipo : TipoCarro = TipoCarro.classicos
    protected var carros = listOf<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            tipo = arguments!!.getSerializable("tipo") as TipoCarro
        }
        EventBus.getDefault().register(this)
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        taskCarros()
    }

    open fun taskCarros() {
        doAsync {
            if (AndroidUtils.isNetworkAvailable(activity!!.applicationContext))
                carros = CarroService.getCarros(tipo)
            else
                carros = mutableListOf()

            uiThread {
                recyclerView.adapter = CarroAdapter(carros) {
                    onClickCarro(it)
                }
            }
        }

    }

    open fun onClickCarro(carro: Carro) {
        activity?.startActivity<CarroActivity>("carro" to carro)
    }

    @Subscribe
    fun onRefresh(event: SaveCarroEvent){
        taskCarros()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
