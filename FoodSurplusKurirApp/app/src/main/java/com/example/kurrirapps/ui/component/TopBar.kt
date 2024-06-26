package com.example.kurrirapps.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.kurrirapps.ui.theme.Brown

@Composable
fun TopBar(){
    Box (modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Brown)
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ){
            Text(
                text = "Nama",
                style = TextStyle(
                    color = Color.White
                )
            )
            Button(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                shape = RoundedCornerShape(16.dp) ,
                onClick = { }
            ) {

            }
        }

    }
}