package com.example.catapplication.presentation.fragment

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.Coil
import coil.load
import coil.request.ImageRequest
import com.example.catapplication.databinding.FragmentCatInfoBinding
import com.example.catapplication.repository.retrofit.entity.CatApiService.Companion.CAT_ID
import com.example.catapplication.repository.retrofit.entity.CatApiService.Companion.CAT_URL
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class CatInfoFragment : Fragment() {

    private var parameter: String = ""
    private var binding: FragmentCatInfoBinding? = null
    private val catBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            parameter = it.getString(CAT_URL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatInfoBinding.inflate(inflater, container, false)
        return catBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(catBinding) {
            imageCat.load(parameter)
            saveCatButton.setOnClickListener { getImageAndSave(parameter) }
            backInGalleryButton.setOnClickListener { activity?.onBackPressed() }

        }
    }

    private fun getImageAndSave(bitmapURL: String) = lifecycleScope.launch {
        val imageLoader = Coil.imageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data(parameter)
            .build()

        try {
            val downloadedBitmap = (imageLoader.execute(request).drawable as BitmapDrawable).bitmap
            saveToGallery(downloadedBitmap)

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun saveToGallery(bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context?.contentResolver?.also { resolver ->

                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }

        } else {
            val imagesDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDirectory, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Saved to Photos", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(parameter1: String, parameter2: String) =
            CatInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(CAT_URL, parameter1)
                    putString(CAT_ID, parameter2)
                }
            }
    }
}
