package com.example.pokemonapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.pokemonapp.ui.theme.Blue1
import com.example.pokemonapp.ui.theme.Blue2

@Composable
fun CardItem(image: String, name : String, nickname : String?, onClickReleaseButton : () -> Unit,
             onClickRenameButton : () -> Unit, onClickCard : () -> Unit) {
    Card(modifier = Modifier
        .wrapContentSize()
        .padding(24.dp)
        .clickable { onClickCard() },
        colors = CardDefaults.cardColors(Blue2),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(20)) {
        Column() {
            Text(text = name, textAlign = TextAlign.Center, fontSize = 18.sp, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), color = Blue1)
            AsyncImage(model = image, contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(120.dp).align(CenterHorizontally), contentDescription = "Image Card",
                alignment = Alignment.Center)

            if (!nickname.isNullOrEmpty()) {
                Text(text = "Nickname = $nickname", textAlign = TextAlign.Center, fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp), color = Blue1)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    SmallButton(color = Color.Green, text = "Rename") {
                        onClickRenameButton()
                    }
                    SmallButton(color = Color.Blue, text = "Release") {
                        onClickReleaseButton()
                    }
                }
            }
        }
    }
}

@Composable
fun ResponseDialog(
    onDismiss : () -> Unit,
    dismissTitle : String,
    onQueryChange : (String) -> Unit,
    confirmTitle : String,
    title : String,
    image : String?,
    imageVector: ImageVector?,
    nickname: String?,
    onConfirmationRequest : () -> Unit,
){
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), textAlign = TextAlign.Center,
                    text = title, style = MaterialTheme.typography.titleLarge,
                    color = if (title == "Success") Color.Green else Color.Red)
                if (title == "Success") {
                    AsyncImage(modifier = Modifier.size(100.dp), model = image!!, contentDescription = "Image")
                    OutlinedTextField(modifier = Modifier.padding(16.dp), value = nickname!!,
                        onValueChange = onQueryChange ,
                        placeholder = { Text(text = "Input Nickname")},
                        label = { Text(text = "Nickname")})
                } else {
                    Image(modifier = Modifier.fillMaxWidth(), imageVector = imageVector!!, contentDescription = null)
                }

                SmallButton(color = Color.Red, text = dismissTitle) {
                    onDismiss()
                }
                SmallButton(color = Color.Green, text = confirmTitle) {
                    onConfirmationRequest()
                }
            }
        }
    }
}
@Composable
fun SmallButton(color : Color, text : String, onButtonClick : ()->Unit) {
    Button(colors = ButtonDefaults.buttonColors(containerColor = color), onClick = { onButtonClick() }) {
        Text(text = text)
    }
}

@Preview(showSystemUi = true)
@Composable
fun SharedComponentPreview() {

}

@Composable
fun TextWithBackgroundColor(text:String, color: Color, textColor : Color) {
    Box(
        modifier = Modifier
            .background(color = color, shape = MaterialTheme.shapes.medium).padding(8.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun NoDataDisplay(text:String, modifier: Modifier){
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = text)
    }
}