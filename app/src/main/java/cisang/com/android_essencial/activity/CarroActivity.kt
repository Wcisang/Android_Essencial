package cisang.com.android_essencial.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.domain.FavoritosService
import cisang.com.android_essencial.extensions.loadUrl
import cisang.com.android_essencial.extensions.setupToolbar
import cisang.com.android_essencial.fragments.MapaFragment
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class CarroActivity : BaseActivity() {

    val carro by lazy { intent.getParcelableExtra("carro") as Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        setupToolbar(R.id.toolbar, carro.nome, true)

        initViews()
        fab.setOnClickListener { onClickFavoritar(carro) }
    }

    private fun onClickFavoritar(carro: Carro) {
        doAsync {
            val favoritado = FavoritosService.favoritar(carro)
            uiThread {
                setFavoriteColor(favoritado)
                toast(if (favoritado) R.string.msg_carro_favoritado
                else R.string.msg_carro_desfavoritado)
            }
        }
    }

    private fun initViews() {
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto)
        img.loadUrl(carro.urlFoto)
        imgPlayVideo.setOnClickListener {
            val url = carro.urlVideo
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            startActivity(intent)
        }

        val mapaFragment = MapaFragment()
        mapaFragment.arguments = intent.extras
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mapaFragment, mapaFragment)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_editar -> {
                startActivity<CarroFormActivity>("carro" to carro)
            }
            R.id.action_deletar -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setFavoriteColor(favorito: Boolean) {
        val fundo = ContextCompat.getColor(this, if (favorito) R.color.favorito_on else R.color.favorito_off)
        val cor = ContextCompat.getColor(this, if (favorito) R.color.yellow else R.color.favorito_on)
        fab.backgroundTintList = ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(fundo))
        fab.setColorFilter(cor)
    }
}
