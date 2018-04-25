package cisang.com.android_essencial.activity

import android.os.Bundle
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.TipoCarro
import cisang.com.android_essencial.extensions.addFragment
import cisang.com.android_essencial.extensions.setupToolbar
import cisang.com.android_essencial.fragments.CarrosFragment

class CarrosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)
        setupToolbar(R.id.toolbar, title, true)
        if (savedInstanceState == null)
            addFragment(R.id.container, CarrosFragment())
    }
}
