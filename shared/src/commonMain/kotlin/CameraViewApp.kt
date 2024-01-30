import androidx.compose.runtime.Composable
import view.photolist.ImageHandler

@Composable
expect fun takePictureNativeView(imageHandler: ImageHandler, redraw: Int = 0)

expect fun getPlatformName(): String
