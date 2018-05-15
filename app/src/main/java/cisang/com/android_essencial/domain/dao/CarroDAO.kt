package cisang.com.android_essencial.domain.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import cisang.com.android_essencial.domain.Carro

/**
 * Created by WCisang on 14/05/2018.
 */
@Dao
interface CarroDAO {

    @Query("SELECT * FROM carro WHERE id = :id")
    fun getById(id: Long) : Carro?

    @Query("SELECT * FROM carro")
    fun findAll() : List<Carro>

    @Insert
    fun insert(carro: Carro)

    @Delete
    fun delete(carro: Carro)
}