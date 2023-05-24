package com.example.e_medib.features.home_feature.view

import CustomExpandedCard
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_medib.ui.theme.*

@Composable
fun KondisiKesehatanScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        CustomExpandedCard(
            header = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(6f)) {
                        Text(
                            text = "Tekanan Darah",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            color = mGrayScale
                        )
                        Row(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "1234",
                                style = MaterialTheme.typography.h4,
                                fontWeight = FontWeight.Bold,
                                color = mGrayScale
                            )
                            Text(
                                text = "mmHg",
                                modifier = Modifier.padding(start = 12.dp),
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.SemiBold,
                                color = mGrayScale
                            )
                        }


                    }

                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .weight(3f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = mRedMain,
                            contentColor = mWhite,
                            disabledBackgroundColor = mLightGrayScale,
                            disabledContentColor = mBlack
                        ),
                        shape = RoundedCornerShape(32.dp),
                    ) {
                        Text(
                            text = "Tambah",
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.SemiBold,
                            color = mWhite
                        )
                    }

                }
            },
            body = {
                Column() {
                    Text(
                        text = "Some List",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        color = mGrayScale
                    )
                    Text(
                        text = "Some List",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        color = mGrayScale
                    )
                    Text(
                        text = "Some List",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        color = mGrayScale
                    )
                    Text(
                        text = "Some List",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        color = mGrayScale
                    )
                    Text(
                        text = "Some List",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        color = mGrayScale
                    )
                }

            },
        )

    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun KondisiKesehatanScreenPreview() {
    KondisiKesehatanScreen()

//    CustomExpandedCard(
//        headerLabel = "Tekanan Darah",
//        body = {}, onAddButton = {}, onListTab = {},
//    )
}