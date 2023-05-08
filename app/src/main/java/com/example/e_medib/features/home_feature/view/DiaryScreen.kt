package com.example.e_medib.features.home_feature.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.e_medib.R
import com.example.e_medib.navigations.EMedibAppScreen
import com.example.e_medib.ui.theme.*

@Composable
fun DiaryScreen() {
    val catatanLuka = rememberSaveable() { mutableStateOf("") }
    val mExpanded = remember { mutableStateOf(false) }
    val jenisLuka = listOf("Luka Basah", "Luka Kering")
    val selectedLuka = remember { mutableStateOf("") }
    val mTextFieldSize = remember { mutableStateOf(Size.Zero) }
    val icon = if (mExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        // TITLE DIARY
        Text(
            text = "Bagaimana perasaan anda hari ini ?",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = mGrayScale
        )
        DiaryBox(
            header = {
                Text(
                    text = "Anda belum menulis Diary hari ini",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = mGrayScale
                )
            },
            body = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = mLightGrayScale)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pen_tool),
                        contentDescription = "pent_tool",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(24.dp)
                    )
                }
            },
            footer = {
                Text(
                    text = "Tambahkan apa yang kamu lakukan hari ini dan bagaimana perasaan kamu hari ini \uD83D\uDCDD",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom = 24.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = mGrayScale
                )
            },
            buttonLabel = "Tambah Diary", onButtonTap = {},
        )

        // CATATAN LUKA
        Text(
            text = "Catatan Luka",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 16.dp, top = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = mGrayScale
        )
        DiaryBox(
            header = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    OutlinedTextField(
                        value = selectedLuka.value,
                        onValueChange = { selectedLuka.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                mTextFieldSize.value = coordinates.size.toSize()
                            },
                        placeholder = {
                            Text(
                                text = "Jenis Luka",
                                style = MaterialTheme.typography.body1,
//                            modifier = Modifier.padding(6.dp),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                                color = mRedMain
                            )
                        },
                        trailingIcon = {
                            Icon(
                                icon,
                                "contentDescription",
                                tint = mRedMain,
                                modifier = Modifier.clickable {
                                    mExpanded.value = !mExpanded.value
                                },
                            )
                        },
                        readOnly = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = mWhite,
                            textColor = mRedMain,
                            unfocusedBorderColor = mLightGrayScale,
                            focusedBorderColor = mLightGrayScale
                        ),
                    )
                }
                DropdownMenu(
                    expanded = mExpanded.value,
                    onDismissRequest = { mExpanded.value = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() })
                ) {
                    jenisLuka.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedLuka.value = label
                            mExpanded.value = false
                        }) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                                color = mGrayScale
                            )
                        }
                    }
                }

            },
            body = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .background(color = mLightGrayScale)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = "add_image",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(24.dp)
                                .clickable {}
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    OutlinedTextField(
                        value = catatanLuka.value,
                        onValueChange = { catatanLuka.value = it },
                        modifier = Modifier.fillMaxHeight(),
                        placeholder = {
                            Text(
                                text = "Deskirpsikan lukamu disini...",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(6.dp),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Light,
                                color = mGrayScale
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = mLightGrayScale,
                            unfocusedBorderColor = mLightGrayScale,
                            focusedBorderColor = mLightGrayScale
                        ),
                    )
                }

            },
            footer = {},
            buttonLabel = "Tambah Catatan", onButtonTap = {},
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}


@Composable
fun DiaryBox(
    header: @Composable @UiComposable () -> Unit,
    body: @Composable @UiComposable () -> Unit,
    footer: @Composable @UiComposable () -> Unit,
    buttonLabel: String,
    onButtonTap: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = mLightGrayScale),
        elevation = 0.dp
    ) {
        Column(

            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // header
            header()

            // body
            body()

            // footer
            footer()

            // button
            Button(
                onClick = {
                    onButtonTap()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = mRedMain,
                    contentColor = mWhite,
                    disabledBackgroundColor = mLightGrayScale,
                    disabledContentColor = mBlack
                ),
                shape = RoundedCornerShape(32.dp),
            ) {
                Text(
                    text = buttonLabel,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mWhite
                )
            }

        }

    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DiaryScreenPreview() {
    DiaryScreen()
}