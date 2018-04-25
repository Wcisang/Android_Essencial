package cisang.com.android_essencial.activity

import android.os.Bundle
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.extensions.loadUrl
import cisang.com.android_essencial.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity() {

    val carro by lazy { intent.getSerializableExtra("carro") as Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        setupToolbar(R.id.toolbar, carro.nome, true)

        initViews()
    }

    private fun initViews() {
        tDesc.text = carro.desc
        img.loadUrl(carro.urlFoto)
    }
}
