package com.example.kurrirapps.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kurrirapps.data.model.PesananModel

@Composable
fun PesananAnda(
    pesananState: PesananModel,
    onNavigateToDetailPesananScreen : (pesananModel : PesananModel, desiredScreen : String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "Pesanan Anda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }

        items(pesananState.pesananListState) { pesanan ->
            Column(
                Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        onNavigateToDetailPesananScreen(pesanan, Screen.ScreenDetailPesananAnda.route)
                    }
            ) {
                CheckStatusPesanan(
                    statusPhoto = R.drawable.otw,
                    pesananModel = pesanan,
                    pesananListViewModel = pesananListViewModel,
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ScreenPesananAndaPreview() {
//    TryUserAppTheme {
//        Surface {
//            PesananAnda(
//                pesananState = PesananState(),
//                onNavigateToScreen = {},
//                onHomeScreenEvent = HomeScreenEvent
//            )
//        }
//    }
//}
