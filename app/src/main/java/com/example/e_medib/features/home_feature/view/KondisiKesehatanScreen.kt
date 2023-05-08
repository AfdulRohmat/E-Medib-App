package com.example.e_medib.features.home_feature.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_medib.ui.theme.mBlack
import com.example.e_medib.ui.theme.mRedMain
import com.example.e_medib.ui.theme.mWhite

@Composable
fun KondisiKesehatanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Kondisi Kesehatan",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Normal,
            color = mBlack
        )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun KondisiKesehatanScreenPreview() {
    KondisiKesehatanScreen()
}