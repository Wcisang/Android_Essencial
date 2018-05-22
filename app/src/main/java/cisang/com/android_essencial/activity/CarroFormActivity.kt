package cisang.com.android_essencial.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import cisang.com.android_essencial.R
import cisang.com.android_essencial.domain.Carro
import cisang.com.android_essencial.domain.CarroService
import cisang.com.android_essencial.domain.TipoCarro
import cisang.com.android_essencial.extensions.loadUrl
import cisang.com.android_essencial.extensions.setupToolbar
import cisang.com.android_essencial.utils.CameraHelper
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_form_contents.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class CarroFormActivity : AppCompatActivity() {
    val camera = CameraHelper()
    val carro:Carro? by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro_form)

        setupToolbar(R.id.toolbar, carro?.nome?:getString(R.string.novo_carro))
        initViews()
        camera.init(savedInstanceState)
    }

    private fun initViews() {
        appBarImg.setOnClickListener { onClickAppBarImg() }
        carro?.apply {
            appBarImg.loadUrl(carro?.urlFoto)
            tDesc.setText(desc)
            tNome.setText(nome)

            when(tipo) {
                "classicos" -> radioTipo.check(R.id.tipoClassico)
                "esportivos" -> radioTipo.check(R.id.tipoEsportivo)
                "luxo" -> radioTipo.check(R.id.tipoLuxo)
            }
        }
    }

    private fun onClickAppBarImg() {
        val ms = System.currentTimeMillis()
        val fileName = if (carro != null) "foto_carro_${carro?.id}.jpg" else "foto_carro_${ms}.jpg"
        val intent = camera.open(this, fileName)
        startActivityForResult(intent, 0)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let { camera.onSavedInstanceState(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form_carro, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val bitmap = camera.getBitmap(600, 600)
            if (bitmap != null) {
                camera.save(bitmap)
                appBarImg.setImageBitmap(bitmap)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_salvar -> taskSalvar()
            R.id.action_deletar -> {
                alert(R.string.msg_confirma_excluir_carro, R.string.app_name){
                    positiveButton(R.string.sim) {
                        taskExcluir()
                    }
                    negativeButton(R.string.nao) {

                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun taskExcluir(){
        doAsync {
            val response = CarroService.delete(carro!!)
            uiThread {
                toast(response.msg)
                finish()
            }
        }
    }

    fun taskSalvar(){
        if (tNome.text.isEmpty()){
            tNome.error = getString(R.string.msg_error_form_nome)
            return
        }

        if (tNome.text.isEmpty()){
            tNome.error = getString(R.string.msg_error_form_nome)
            return
        }

        doAsync {
            val c = carro?: Carro()
            c.nome = tNome.text.toString()
            c.desc = tDesc.text.toString()
            c.tipo = when (radioTipo.checkedRadioButtonId) {
                R.id.tipoClassico -> TipoCarro.classicos.name
                R.id.tipoEsportivo -> TipoCarro.esportivos.name
                else -> TipoCarro.luxo.name
            }
            val file = camera.file
            if (file != null && file.exists()){
                val response = CarroService.postFoto(file)
                if (response.isOk()) {
                    c.urlFoto = response.url
                }
            }

            val response = CarroService.save(c)
            uiThread {
                toast(response.msg)
                finish()
            }
        }
    }
}
