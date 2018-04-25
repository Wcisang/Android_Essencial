package cisang.com.android_essencial.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.extensions.loadUrl
import kotlinx.android.synthetic.main.adapter_carro.view.*

/**
 * Created by WCisang on 25/04/2018.
 */
class CarroAdapter (
    val carros: List<Carro>,
    val onClick: (Carro) -> Unit) :
        RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>(){

    class CarrosViewHolder(view: View): RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carro, parent, false)
        return CarrosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carros.size
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val carro = carros[position]
        val view = holder.itemView
        with(view){
            tNome.text = carro.nome
            img.loadUrl(carro.urlFoto, progress)
            setOnClickListener{ onClick(carro) }
        }

    }
}