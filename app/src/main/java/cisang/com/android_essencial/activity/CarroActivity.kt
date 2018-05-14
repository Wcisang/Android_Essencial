package cisang.com.android_essencial.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.extensions.loadUrl
import cisang.com.android_essencial.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.startActivity

class CarroActivity : BaseActivity() {

    val carro by lazy { intent.getParcelableExtra("carro") as Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        setupToolbar(R.id.toolbar, carro.nome, true)

        initViews()
    }

    private fun initViews() {
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto)
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
}
