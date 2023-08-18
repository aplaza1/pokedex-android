package com.example.pokedex.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.model.TypeItem
import com.example.pokedex.ui.theme.getTypeColor

@Composable
fun Types(types: List<TypeItem>, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        for(type in types){
            Type(type = type.type.name)
        }
    }
}

@Composable
fun Type(type: String){
    Card (
        modifier = Modifier
            .width(65.dp)
            .padding(bottom = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = getTypeColor(type),
        )
    ){
        Box (
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier
                    .padding(5.dp),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 5f,
                        offset = Offset(2f, 2f)
                    )
                ),
                text = type.uppercase()
            )
        }
    }
}