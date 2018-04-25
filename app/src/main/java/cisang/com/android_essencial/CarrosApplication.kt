package cisang.com.android_essencial

import android.app.Application

/**
 * Created by willi on 14/02/2018.
 */
class CarrosApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        appInstance = this
    }

    companion object {
        private var appInstance: CarrosApplication? = null
        fun getInstance(): CarrosApplication {
            if (appInstance == null)
                throw IllegalStateException("Configure a classe no manifest")
            return appInstance!!
        }
    }
}