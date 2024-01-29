import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.PhotoInfo
import ui.rose

@Composable
fun PhotoCard(photo: PhotoInfo) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier.padding(10.dp).width(180.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
        ) {
            Image(
                bitmap = photo.image,
                contentDescription = null
            )
//            Image(
//                painter = painterResource(id = flower.image),
//                contentDescription = null,
//                modifier = Modifier.size(140.dp),
//            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = photo.description,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "xx",
                        style = TextStyle(
                            color = rose,
                            fontSize = 16.sp
                        )
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(
                        color = rose,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    Icon(Icons.Default.Edit, tint = Color.White, contentDescription = null)
                }
            }
        }
    }
}