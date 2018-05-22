package cisang.com.android_essencial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.Carro
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by WCisang on 21/05/2018.
 */
class MapaFragment : BaseFragment(), OnMapReadyCallback {

    private var map: GoogleMap? = null
    val carro: Carro by lazy { arguments!!.getParcelable<Carro>("carro") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mapa, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(p0: GoogleMap?) {
        this.map = p0
        val location = LatLng(carro.latitude.toDouble(), carro.longitude.toDouble())
        val update = CameraUpdateFactory.newLatLngZoom(location, 13f)
        map?.moveCamera(update)
        map?.addMarker(MarkerOptions()
                .title(carro.nome)
                .snippet(carro.desc)
                .position(location))

        map?.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

}