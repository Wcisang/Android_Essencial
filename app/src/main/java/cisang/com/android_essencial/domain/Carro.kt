package cisang.com.android_essencial.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by WCisang on 25/04/2018.
 */
@Entity(tableName = "carro")
class Carro() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""
    var latitude = ""
        get() = if (field.trim().isEmpty()) "0.0" else field
    var longitude = ""
        get() = if (field.trim().isEmpty()) "0.0" else field

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        tipo = parcel.readString()
        nome = parcel.readString()
        desc = parcel.readString()
        urlFoto = parcel.readString()
        urlInfo = parcel.readString()
        urlVideo = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
    }

    override fun toString(): String {
        return "Carro(nome='$nome')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(tipo)
        parcel.writeString(nome)
        parcel.writeString(desc)
        parcel.writeString(urlFoto)
        parcel.writeString(urlInfo)
        parcel.writeString(urlVideo)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Carro> {
        override fun createFromParcel(parcel: Parcel): Carro {
            return Carro(parcel)
        }

        override fun newArray(size: Int): Array<Carro?> {
            return arrayOfNulls(size)
        }
    }
}