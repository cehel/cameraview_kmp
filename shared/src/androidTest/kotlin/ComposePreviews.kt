import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
    photos = SnapshotStateList<PhotoInfo>().also {
        it.addAll(
            listOf(
                createPhotoInfo("Test"),
                createPhotoInfo("Hello"),
                createPhotoInfo("Test"),
            )
        )
    },
    onOpenCameraButtonClicked = {},
    buttonClick = 1,
    imageHandler = defaultImageHandler()
)

fun defaultImageHandler() = object : ImageHandler {
    override fun onImageBitmapCaptured(bitmap: ImageBitmap) {}
    override fun onCancelled() {}
}

fun createPhotoInfo(text: String) = PhotoInfo(
    objectId = org.mongodb.kbson.ObjectId(),
    image = ImageBitmap(100, 200),
    descriptionBig = text,
    descriptionSmall = "",
    dateTime = currentDateTime()
)

private fun currentDateTime(): LocalDateTime {
    val currentMoment: Instant = Clock.System.now()
    return currentMoment.toLocalDateTime(TimeZone.UTC)
}