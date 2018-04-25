package cisang.com.android_essencial.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.TipoCarro

class CarrosFragment : BaseFragment() {

    private var tipo : TipoCarro = TipoCarro.classicos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipo = arguments?.getSerializable("tipo") as TipoCarro
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_carros, container, false)
        val textView = view?.findViewById<TextView>(R.id.text)
        val tipoString = getString(tipo.string)
        textView?.text = "Carros $tipoString"
        return view
    }


}
