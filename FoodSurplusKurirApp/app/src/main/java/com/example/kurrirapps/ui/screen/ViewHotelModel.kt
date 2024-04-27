
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dummyfirebaseauth.MyApp
import com.example.kurrirapps.data.model.HotelModel
import com.example.kurrirapps.presentation.auth.UserData
import com.example.kurrirapps.presentation.kurir.KurirViewModel
import com.example.kurrirapps.ui.component.DisplayHotel
import com.google.firebase.firestore.QueryDocumentSnapshot

//data class HotelModel(
//    val id : String = "",
//    val name : String = "",
//    val telp : Int ,
//)

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ScreenViewHotelModel(
    userData: UserData,
    kurirViewModel: KurirViewModel,
    onNavigateToScreen: (String) -> Unit
) {
    val contex = LocalContext.current

    val hotelModels = remember { mutableStateListOf<HotelModel>() }

    LaunchedEffect(Unit) { // Launch effect after composition
        MyApp.appModule.hotelRepository.getLiveDataHotel (
            errorCallback = {
                Toast.makeText(contex, "error : $it", Toast.LENGTH_LONG).show()
                Log.d("ScreenViewHotelModel", "error : $it")
            },
            addDataCallback = {
                hotelModels.add(it)
                Log.d("ScreenViewHotelModel", "added to screen : $it")

            },
            updateDataCallback = { updatedData ->

                val index = hotelModels.indexOfFirst { it.id == updatedData.id }
                if (index != -1) hotelModels[index] = updatedData
                Log.d("ScreenViewHotelModel", "updated to screen : $updatedData")
            },
            deleteDataCallback = { documentId: String ->
                hotelModels.removeAll { it.id == documentId }
                Log.d("ScreenViewHotelModel", "deleted from screen : id = $documentId")
            }
        )
    }

    LazyColumn (Modifier.padding(8.dp)) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "Masukkan Hotel Tempat Anda Bekerja",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
        items (hotelModels) {
            DisplayHotel (
                hotelModel = it,
                onNavigateToScreen = onNavigateToScreen,
                kuriirViewModel = kurirViewModel,
                userData = userData
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
        }
    }
}

fun fetchSnapshotToHotel(queryDocumentSnapshot : QueryDocumentSnapshot) : HotelModel {
    return HotelModel(
        id = queryDocumentSnapshot.id,
        name = queryDocumentSnapshot.getString("name") ?: "",
        telp = queryDocumentSnapshot.getString("phoneNumber") ?: "",
        address = queryDocumentSnapshot.getString("alamat") ?: ""
    )
}