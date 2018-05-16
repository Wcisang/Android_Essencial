package cisang.com.android_essencial.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cisang.com.android_essencial.R.string.tipo
import cisang.com.android_essencial.domain.TipoCarro
import cisang.com.android_essencial.fragments.CarrosFragment
import cisang.com.android_essencial.fragments.FavoritosFragment

/**
 * Created by WCisang on 27/04/2018.
 */
class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm){


    override fun getCount(): Int = 4

    fun getTipoCarro(position: Int) = when(position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        2 -> TipoCarro.luxo
        else -> TipoCarro.favoritos
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    override fun getItem(position: Int): Fragment {
        if (position == 3)
            return FavoritosFragment()
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments?.putSerializable("tipo", tipo)
        return f
    }
}