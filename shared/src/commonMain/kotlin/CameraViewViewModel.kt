import androidx.compose.ui.graphics.ImageBitmap
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.PhotoInfo

class CameraViewViewModel : ViewModel(), ImageHandler {
    private val _imageBytes = MutableStateFlow<ImageBitmap?>(null)
    val imageBytes: StateFlow<ImageBitmap?> = _imageBytes

    private val _photoInfos = MutableStateFlow<List<PhotoInfo>>(listOf())
    val photoInfos: StateFlow<List<PhotoInfo>> = _photoInfos

    private val _showCameraView = MutableStateFlow<Boolean>(false)
    val showCameraView: StateFlow<Boolean> = _showCameraView

    private val _photos: MutableList<PhotoInfo> = mutableListOf()
    private var photoId = 0

    override fun onImageBytesCaptured(byteArray: ByteArray?) {
        //_imageBytes.value = byteArray
        println("Image bytearray is captured size: ${byteArray?.size}")
        _showCameraView.value = false
    }

    override fun onImageBitmapCaptured(bitmap: ImageBitmap) {
        _photos.add(PhotoInfo(image= bitmap, description = "Photo ${photoId++}"))
        _photoInfos.value = _photos.toList()
        _imageBytes.value = bitmap
        println("bitmap captured")
    }

    override fun onCancelled() {
        _showCameraView.value = false
        println("Camera View was closed")
    }

    fun showCameraView() {
        _showCameraView.value = true
    }

}
