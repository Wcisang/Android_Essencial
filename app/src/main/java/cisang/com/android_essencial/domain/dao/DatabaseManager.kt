package cisang.com.android_essencial.domain.dao

import android.arch.persistence.room.Room
import cisang.com.android_essencial.CarrosApplication

/**
 * Created by WCisang on 14/05/2018.
 */
object DatabaseManager {
    private var dbInstance: CarrosDatabase
    init {
        val appContext = CarrosApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext,
                CarrosDatabase::class.java,
                "carros.sqlite"
        ).build()
    }

    fun getCarroDAO() : CarroDAO {
        return dbInstance.carroDAO()
    }
}