import androidx.compose.ui.graphics.ImageBitmap
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.PhotoInfo

class CameraViewViewModel : ViewModel() {

    private val _photoInfos = MutableStateFlow<List<PhotoInfo>>(listOf())
    val photoInfos: StateFlow<List<PhotoInfo>> = _photoInfos

    private val _showCameraView = MutableStateFlow(false)
    val showCameraView: StateFlow<Boolean> = _showCameraView

    private val _photos: MutableList<PhotoInfo> = mutableListOf()
    private var photoId = 0

    fun onImageBitmapCaptured(bitmap: ImageBitmap) {
        _photos.add(PhotoInfo(image= bitmap, description = "Photo ${photoId++}"))
        _photoInfos.value = _photos.toList()
        println("bitmap captured")
    }

    fun onCaptureCancelled() {
        _showCameraView.value = false
        println("Camera View was closed")
    }

    fun showCameraView() {
        _showCameraView.value = true
        println("Camera View opens")
    }

}
