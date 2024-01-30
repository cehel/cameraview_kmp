import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import model.PhotoInfo
import view.photolist.ImageHandler
import view.photolist.PhotoCard
import view.photolist.PhotoListScreenContent

@Preview
@Composable
fun PreviewPhotoList() = PhotoCard(
    photo = createPhotoInfo("World "),
)

@Preview
@Composable
fun PreviewCrackContent() = PhotoListScreenContent(
    showCamera = false,
    photos = listOf(
        createPhotoInfo("Test"),
        createPhotoInfo("Hello"),
        createPhotoInfo("Test"),
    ),
    onOpenCameraButtonClicked = {},
    buttonClick = 1,
    imageHandler = defaultImageHandler()
)

fun defaultImageHandler() =  object : ImageHandler {
    override fun onImageBitmapCaptured(bitmap: ImageBitmap) {}
    override fun onCancelled() {}
}

fun createPhotoInfo(text: String) = PhotoInfo(
    image = ImageBitmap(100, 200),
    description = text
)