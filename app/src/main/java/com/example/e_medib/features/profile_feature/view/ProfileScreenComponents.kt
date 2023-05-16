package com.example.e_medib.features.profile_feature.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.e_medib.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomProfileCard(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, color = mLightGrayScale),
        elevation = 0.dp,
    ) {
        Column(
            modifier = modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            header()
            body()
        }

    }


}

@Composable
fun CustomProfileListTile(modifier: Modifier = Modifier, data: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, mLightGrayScale),
                RoundedCornerShape(50)
            )
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = data,

            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            color = mBlack
        )

        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Outlined.FileDownload,
                contentDescription = "Download",
                tint = mRedMain,
            )

        }

    }
}