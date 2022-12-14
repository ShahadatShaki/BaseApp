package com.acoder.imagecompressor.Activity

import android.Manifest
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.acoder.imagecompressor.Adapter.ImageAdapter
import com.acoder.imagecompressor.Base.BaseActivity
import com.acoder.imagecompressor.R
import com.acoder.imagecompressor.databinding.ActivityHomePageBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.listener.OnMultiSelectedListener
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.math.RoundingMode


/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */
class HomePage : BaseActivity() {
    lateinit var b: ActivityHomePageBinding
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_home_page
    }

    override fun initComponent() {

        b = getBinding()

        b.multipleImage.setOnClickListener {
            checkMPermissionAndCapture()
        }

        b.compress.setOnClickListener {
            compressImages()
        }

        b.saveOnStorage.setOnClickListener {
            storeInStorage()
        }

        initRecycleView()

    }

    var compressedImageSize: Double = 0.0

    var compressFiles = ArrayList<File?>()
    private fun compressImages() {
        imageList = adapter.getData()
        compressFiles.clear()
        var compressed = 0;
        for (i in imageList) {

            GlobalScope.launch {
                val file = Compressor.compress(context, File(i.path)) {
                    resolution(1280, 720)
                    quality(b.compressQuality.progress)
                }
                Log.d("compressImages", "compressImages: " + file.length())
                compressedImageSize += file.length() / 1024
                compressFiles.add(file)
                compressed++

                if (compressed == imageList.size) {
                    withContext(Dispatchers.Main) {
                        compressedImageSize /= 1024
                        compressedImageSize =
                            compressedImageSize.toBigDecimal().setScale(1, RoundingMode.UP)
                                .toDouble()

                        b.info.text = "Total selected image: ${imageList.size} and Total size: ${size} MB\n" +
                                "Total Size after compression: $compressedImageSize MB"

                        Toast.makeText(
                            context,
                            "Images compressed Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        b.saveOnStorage.visibility = View.VISIBLE
                    }
                }

            }
        }
    }

    private fun storeInStorage() {

        for (file in compressFiles) {

            val path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ).absolutePath + "/ImageCompressor"
            val newFile = File(path, file?.name)
            file?.copyTo(newFile)
        }


    }

    //<editor-fold desc="Handle Image">
    fun checkMPermissionAndCapture() {
        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                capturePhoto()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                showFailedToast("Permission Denied")
            }
        }
        TedPermission.with(context)
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check()
    }

    lateinit var adapter: ImageAdapter
    lateinit var imageList: java.util.ArrayList<Uri>

    private fun initRecycleView() {
        imageList = java.util.ArrayList<Uri>()
        val mLayoutManager = GridLayoutManager(context, 2)
        b.recycleView.layoutManager = mLayoutManager
        b.recycleView.itemAnimator = DefaultItemAnimator()
        b.recycleView.setHasFixedSize(true)
        adapter =
            ImageAdapter(context, imageList) { position: Int, viewId: Int ->
//                if (viewId == R.id.closeButton) {
//                    imageList.removeAt(position)
//                    adapter.setData(imageList)
//                }
            }
        b.recycleView.adapter = adapter
    }

    private fun capturePhoto() {
        var count = 15 - imageList.size
        TedImagePicker.with(this)
            .max(count, "You can pick max $count photos")
//                .selectedUri(selectedImages)
            .startMultiImage(object : OnMultiSelectedListener {
                override fun onSelected(uriList: List<Uri>) {
                    handleImages(uriList as java.util.ArrayList<Uri>)
                }
            })
    }

    var size: Double = 0.0;
    private fun handleImages(uriList: java.util.ArrayList<Uri>) {
        imageList.addAll(uriList)
        for (i in imageList) {
            size += File(i.path!!).length() / 1024
        }
        size /= 1024
        size = size.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        b.info.text = "Total selected image: ${imageList.size} and Total size: ${size} MB"
        adapter.setData(imageList)
    }


    //</editor-fold>

}