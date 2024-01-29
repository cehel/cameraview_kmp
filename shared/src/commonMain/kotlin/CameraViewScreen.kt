import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import model.PhotoInfo

@Composable
fun CrackDetailScreen() {

    val viewModel = getViewModel(Unit, viewModelFactory { CameraViewViewModel() })

    val showCamera by viewModel.showCameraView.collectAsState()
    val photos by viewModel.photoInfos.collectAsState()

    var buttonClick: Int by rememberSaveable {
        mutableStateOf<Int>(0)
    }

    val imageHandler = remember {
        object : ImageHandler {
            override fun onImageBitmapCaptured(bitmap: ImageBitmap) =
                viewModel.onImageBitmapCaptured(bitmap)

            override fun onCancelled() = viewModel.onCaptureCancelled()
        }
    }

    CrackDetailContent(
        showCamera = showCamera,
        photos = photos,
        onOpenCameraButtonClicked = {
            viewModel.showCameraView()
            buttonClick++
        },
        buttonClick = buttonClick,
        imageHandler = imageHandler
    )


}

@Composable
fun CrackDetailContent(
    showCamera: Boolean,
    photos: List<PhotoInfo>,
    onOpenCameraButtonClicked: () -> Unit,
    buttonClick: Int,
    imageHandler: ImageHandler
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        PhotoCardList(photos)
        Button(onClick = {
            onOpenCameraButtonClicked.invoke()
        }) {
            Text("Capture photo")
        }
        if (showCamera) {
            CameraScreen(
                buttonClick,
                imageHandler
            )
        }
    }
}

@Composable
private fun PhotoCardList(photos: List<PhotoInfo>) {
    LazyRow {
        items(photos.size) {
            PhotoCard(photos[it])
        }
    }
}

@Composable
fun CameraScreen(
    buttonClick: Int,
    imageHandler: ImageHandler
) {
    Column(modifier = Modifier.height(50.dp).fillMaxWidth()) {
        // Your overlay content goes here$

        takePictureNativeView(imageHandler, buttonClick)
    }
}


@Composable
expect fun takePictureNativeView(imageHandler: ImageHandler, redraw: Int = 0)

expect fun getPlatformName(): String

