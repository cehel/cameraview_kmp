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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun CrackDetailScreen() {

    val viewModel = getViewModel(Unit, viewModelFactory { CameraViewViewModel() })

    val showCamera by viewModel.showCameraView.collectAsState()

    var buttonClick =  remember {
        mutableStateOf<Int>(0)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        PhotoCardList(viewModel)
        Button(onClick = {
            viewModel.showCameraView()
            buttonClick.value++
        }) {
            Text("Capture photo")
        }
        if (showCamera) {
            CameraScreen(viewModel, buttonClick.value)
        }

    }
}

@Composable
private fun PhotoCardList(viewModel: CameraViewViewModel) {
    val photos by viewModel.photoInfos.collectAsState()
    LazyRow {
        items(photos.size) {
            PhotoCard(photos[it])
        }
    }
}

@Composable
fun CameraScreen(viewModel: CameraViewViewModel, buttonClick: Int) {
    Column(modifier = Modifier.height(50.dp).fillMaxWidth()) {
        // Your overlay content goes here$
        takePictureNativeView(viewModel, buttonClick)
    }
}



@Composable
expect fun takePictureNativeView(imageHandler: ImageHandler, redraw: Int = 0)

expect fun getPlatformName(): String

