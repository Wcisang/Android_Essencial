package cisang.com.android_essencial.domain.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cisang.com.android_essencial.domain.Carro

/**
 * Created by WCisang on 14/05/2018.
 */
@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase : RoomDatabase(){
    abstract fun carroDAO() : CarroDAO
}