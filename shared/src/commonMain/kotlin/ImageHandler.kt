import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.skia.Bitmap

interface ImageHandler {

    fun onImageBytesCaptured(byteArray: ByteArray?)
    fun onImageBitmapCaptured(bitmap: ImageBitmap)
    fun onCancelled()
}
