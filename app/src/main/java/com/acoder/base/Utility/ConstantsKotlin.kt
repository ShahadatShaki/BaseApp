package com.acoder.base.Utility

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.acoder.base.R
import com.makeramen.roundedimageview.RoundedDrawable
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ConstantsKotlin : Constants() {

    fun compressImage(context: Context, actualImageFile: File): File? {
        var file: File? = null
        val fileLen = (actualImageFile.length() / 1024).toInt()
        var convertQuality = 75
        if (fileLen > 10000) {
            convertQuality = 30
        } else if (fileLen > 3000) convertQuality = 50
        try {

            GlobalScope.launch {
                file = Compressor.compress(context, actualImageFile) {
                    resolution(1280, 720)
                    quality(convertQuality)
                }
            }

        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Unable to process the image, Please change or recapture the image",
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }
        return file
    }

    fun getFileFromUri(context: Context, imageUri: Uri): File? {
        return try {
            val path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File(path, "picture_${Random().nextInt()}.jpg")

            try {
                if (!file.exists()) {
                    file.createNewFile()
                }
            } catch (e: IOException) {
            }
            val fos = FileOutputStream(file)
            val `is` = context!!.contentResolver.openInputStream(imageUri)
            val buffer = ByteArray(1024)
            var len = 0
            len = `is`!!.read(buffer)
            while (len != -1) {
                fos.write(buffer, 0, len)
                len = `is`.read(buffer)
            }
            fos.close()

            file
        } catch (e: Exception) {
            showFailedToast(context, e.localizedMessage)
            null
        }
    }

    companion object {
        private val TAG = "shaki-tag"

        fun storeInLocalStorage(imageView: ImageView, url: String) {

            GlobalScope.launch {
                try {
                    var bitmap: Bitmap = try {
                        val bitmapDrawable = imageView.drawable as RoundedDrawable
                        bitmapDrawable.sourceBitmap
                    } catch (e: java.lang.Exception) {
                        Log.i(TAG, "bitmapDrawable: " + e.localizedMessage)
                        val bitmapDrawable = imageView.drawable as BitmapDrawable
                        bitmapDrawable.bitmap
                    }
                    val finalBitmap = bitmap
                    val file = getFileNameFromUrl(imageView.context, url)
                    if (!file?.exists()!!) file?.createNewFile()
                    val out = FileOutputStream(file)
                    finalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
                    out.flush()
                    out.close()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    Log.i(TAG, "storeInLocalStorage: " + e.localizedMessage)
                }
            }

        }

        fun loadAndStoreImage(imageView: ImageView, url: String?) {
            if (url == null || url.isEmpty()) {
                return
            }

            GlobalScope.launch {
                val file = getFileNameFromUrl(imageView.context, url)
                if (file != null) {
                    if (file.length() != 0L) {
                        withContext(Dispatchers.Main) {
                            imageView.setImageURI(Uri.fromFile(file))
                        }
                        Log.i(TAG, "File length:  ${file.length()} \nurl: $url")
                    } else {
                        Log.i(TAG, "File not found $url")
                        withContext(Dispatchers.Main) {
                            loadAndStoreImageFromPicasso(imageView, url)
                        }
                    }
                }
            }

        }

        fun loadAndStoreImageFromPicasso(imageView: ImageView?, url: String) {
            try {
                Log.i(TAG, "Loading image: $url")
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.generic_placeholder)
                    .error(R.drawable.generic_placeholder)
                    .into(imageView, object : Callback {
                        override fun onSuccess() {
                            storeInLocalStorage(imageView!!, url)
                        }

                        override fun onError(e: java.lang.Exception) {
                            Log.d(TAG, "Image loading error " + e.localizedMessage)
                        }
                    })
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "loadAndStoreImageFromPicasso " + e.localizedMessage)
            }
        }

        //    private static void storeInLocalStorage(ImageView imageView, String url) {
        //        try {
        //            RoundedDrawable bitmapDrawable = ((RoundedDrawable) imageView.getDrawable());
        //            Bitmap finalBitmap = bitmapDrawable.getSourceBitmap();
        //
        //            File file = getFileNameFromUrl(url);
        //
        //            if (!file.exists())
        //                file.createNewFile();
        //
        //            FileOutputStream out = new FileOutputStream(file);
        //            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        //            out.flush();
        //            out.close();
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //    }
        fun getFileNameFromUrl(context: Context, url: String): File? {
            var file: File? = null
            file = try {
                val root =
                    ContextWrapper(context).getDir("Image", Context.MODE_PRIVATE).absolutePath
                val myDir = File("$root/cache")
                myDir.mkdirs()
                val part = url.split("/").toTypedArray()
                val part2 = part[part.size - 1].split("v=").toTypedArray()
                val imageName = part2[0].replace("?", "")
                File(myDir, imageName)
            } catch (e: java.lang.Exception) {
                return null
            }
            return file
        }


    }


}