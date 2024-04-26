package com.example.kurrirapps.ui.component

import android.net.Uri
import android.service.autofill.UserData
import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.tools.FirebaseHelper.Companion.uploadImageToFirebaseStorage
import com.example.kurrirapps.ui.theme.KurrirAppsTheme

@Composable
fun Konfirmasi(onNavigateToPesananMasukScreen:()->Unit,userData :com.example.kurrirapps.presentation.auth.UserData) {
    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.EMPTY)
    }

    Button(
        modifier = Modifier
            .height(50.dp)
            .width(180.dp),
        onClick = {
            if (selectedImageUri?.path!!.isEmpty()) {
                Toast.makeText(context, "Select an Image", Toast.LENGTH_SHORT).show()
            } else {
                uploadImageToFirebaseStorage(
                    "User ${userData.userId}",
                    selectedImageUri!!,
                    onSuccess = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    },
                    onError = {
                        Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    }
                )

                onNavigateToPesananMasukScreen()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.Black,
        ),
    ) {
        Text(text = "Konfirmasi",
            style = TextStyle(
                fontSize = (20.sp),
                fontWeight = FontWeight.Bold
            )
        )
        
    }

}
//@Preview(showBackground = true)
//@Composable
//fun Konfirmasipreview(){
//    KurrirAppsTheme {
//        Konfirmasi(
//            onNavigateToPesananMasukScreen = {}
//        )
//    }
//}