package infix.studios.wallpapers.util

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import infix.studios.wallpapers.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false

    val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager
            .getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun setWallpaperDialog(context: Context, urlPath: String) {
    val items = arrayOf("Home", "Lock", "Both")
    MaterialAlertDialogBuilder(context)
            .setTitle("Set wallpaper")
            .setItems(items) { dialog, which ->
                // Respond to item chosen
                when(items[which]) {
                    "Home" -> {
                        setWallpaperHome(urlPath)
                    }
                    "Lock" -> {
                        setWallpaperLock(urlPath)
                    }
                    "Both" -> {
                        setWallpaperBoth(urlPath)
                    }
                }
            }
            .show()
}

fun setWallpaperLock(urlPath: String) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val displayMetrics = DisplayMetrics()

            val windowManager =
                BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val width = displayMetrics.widthPixels
            val urlString: String?
            val url: URL?


            urlString = urlPath


            url = URL(urlString)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

            val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(BaseApplication.appContext)
            wallpaperManager.setWallpaperOffsetSteps(1f, 1f)
            wallpaperManager.suggestDesiredDimensions(width, height)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setBitmap(newBitmap, null, false, WallpaperManager.FLAG_LOCK)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun setWallpaperHome(urlPath: String) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val displayMetrics = DisplayMetrics()

            val windowManager =
                BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val width = displayMetrics.widthPixels
            val urlString: String?
            val url: URL?


            urlString = urlPath


            url = URL(urlString)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

            val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(BaseApplication.appContext)
            wallpaperManager.setWallpaperOffsetSteps(1f, 1f)
            wallpaperManager.suggestDesiredDimensions(width, height)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setBitmap(newBitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun setWallpaperBoth(urlPath: String) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val displayMetrics = DisplayMetrics()

            val windowManager =
                BaseApplication.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val width = displayMetrics.widthPixels
            val urlString: String?
            val url: URL?


            urlString = urlPath


            url = URL(urlString)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

            val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(BaseApplication.appContext)
            wallpaperManager.setWallpaperOffsetSteps(1f, 1f)
            wallpaperManager.suggestDesiredDimensions(width, height)

            wallpaperManager.setBitmap(newBitmap)


        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}


fun getLocalBitmapUri(imageView: ImageView, context: Context): Uri? {
    // Extract Bitmap from ImageView drawable
    val drawable: Drawable = imageView.drawable
    var bmp: Bitmap? = null
    bmp = if (drawable is BitmapDrawable) {
        (imageView.drawable as BitmapDrawable).bitmap
    } else {
        return null
    }
    // Store image to default external storage directory
    var bmpUri: Uri? = null
    try {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png")
        val out = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
        out.close()
        bmpUri = Uri.fromFile(file)
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return bmpUri
}

