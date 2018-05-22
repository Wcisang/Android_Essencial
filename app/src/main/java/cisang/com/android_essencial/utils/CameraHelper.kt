package cisang.com.android_essencial.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import java.io.File
import java.io.FileOutputStream

/**
 * Created by WCisang on 20/05/2018.
 */
class CameraHelper {
    companion object {
        private val TAG = "camera"
    }
    var file: File? = null
        private set

    fun init(icicle: Bundle?){
        if (icicle != null){
            file = icicle.getSerializable("file") as File
        }
    }

    fun onSavedInstanceState(outState: Bundle){
        if (file != null)
            outState.putSerializable("file", file)
    }

    fun open(context: Context, fileName: String): Intent {
        file = getSdCardFile(context, fileName)
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val uri = file?.let { FileProvider.getUriForFile(context, context.applicationContext.packageName+".provider", it) }
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        return i
    }

    fun getSdCardFile(context: Context, fileName: String): File{
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!dir.exists())
            dir.mkdir()
        return File(dir, fileName)
    }

    fun getBitmap(w: Int, h: Int): Bitmap? {
        file?.apply {
            if (exists()){
                val bitmap = ImageUtils.resize(this,w,h)
                return bitmap
            }
        }
        return null
    }

    fun save(bitmap: Bitmap){
        file?.apply {
            val out = FileOutputStream(this)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.close()
        }
    }
}