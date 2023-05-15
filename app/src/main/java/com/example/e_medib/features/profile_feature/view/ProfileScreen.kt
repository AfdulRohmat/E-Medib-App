package com.example.e_medib.features.profile_feature.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_medib.R
import com.example.e_medib.features.home_feature.view.CustomRowInfo
import com.example.e_medib.features.home_feature.view.DiaryScreen
import com.example.e_medib.navigations.EMedibAppScreen
import com.example.e_medib.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profil",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        color = mWhite
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = mRedMain)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // BOX PROFILE
            CustomBoxProfile()

            // BUTTON EDIT
            OutlinedButton(
                onClick = {
                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = mWhite,
                    contentColor = mRedMain,
                    disabledBackgroundColor = mLightGrayScale,
                    disabledContentColor = mBlack
                ),
                border = BorderStroke(1.dp, mRedMain),
                shape = RoundedCornerShape(32.dp),

                ) {
                Text(
                    text = "Edit",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mRedMain
                )
            }

            // DETAIL AKUN
            Text(
                text = "Detail Akun",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomProfileListTile(
                title = "Profil Saya",
                subtitle = "Informasi akunmu",
                leadingIcon = Icons.Outlined.Person,
                onClick = {}
            )
            CustomProfileListTile(
                title = "Kalkulator BMI/BMR",
                subtitle = "Ukur BMI dan BMRmu",
                leadingIcon = Icons.Outlined.Calculate,
                onClick = {}
            )
            CustomProfileListTile(
                title = "Download Catatan Anda",
                subtitle = "Unduh Catatan Kesehatan anda",
                leadingIcon = Icons.Outlined.FileDownload,
                onClick = {}
            )


            // KEAMANAN AKUN
            Text(
                text = "Keamanan Akun",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, top = 16.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            CustomProfileListTile(
                title = "Kebijakan Privasi",
                subtitle = "Kebijakan mengenai keamanan akunmu",
                leadingIcon = Icons.Outlined.Visibility,
                onClick = {}
            )
            CustomProfileListTile(
                title = "Disclaimer",
                subtitle = "Peringatan mengenai kesehatanmu",
                leadingIcon = Icons.Outlined.Info,
                onClick = {}
            )
            CustomProfileListTile(
                title = "Hubungi Kami",
                subtitle = "Layanan Customer Service siap membantu",
                leadingIcon = Icons.Outlined.HelpOutline,
                onClick = {}
            )

        }

    }
}

@Composable
fun CustomProfileListTile(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    leadingIcon: ImageVector,
    onClick: () -> Unit
) {
    Column(modifier = Modifier
        .padding(vertical = 8.dp)
        .clickable { onClick() }) {
        Row(
            modifier = modifier.height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "Person",
                tint = mRedMain
            )
            Column(
                modifier = modifier
                    .weight(4f)
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mBlack
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Normal,
                        color = mGrayScale
                    )
                }

            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "ChevronRight",
                tint = mRedMain
            )
        }
        Divider(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            color = mLightGrayScale,
            thickness = 2.dp
        )
    }

}

@Composable
fun CustomBoxProfile(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = mRedMain),
        elevation = 0.dp
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // PROFILE NAME
            Column(
                modifier = modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = "Zulfikri",
                    modifier = modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = mBlack
                )

                Text(
                    text = "@zulfikri123",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = mGrayScale
                )
            }

            // STATUS KESEHATAN
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
            ) {
                CustomRowInfo(
                    modifier = Modifier,
                    titleRow1 = "BMI",
                    titleRow2 = "BMR",
                    titleRow3 = "",
                    infoRow1 = "20,7",
                    infoRow2 = "1513",
                    infoRow3 = "",
                    unitRow1 = "Kg/M2",
                    unitRow2 = "Cal/day",
                    unitRow3 = "",
                )
                Divider(
                    modifier = Modifier.padding(top = 16.dp),
                    color = mLightGrayScale,
                    thickness = 1.dp
                )

                // ROW GULA DARAH
                CustomRowInfo(
                    modifier = Modifier,
                    titleRow1 = "Tinggi Badan",
                    infoRow1 = "173",
                    unitRow1 = "cm",
                    titleRow2 = "Berat Badan",
                    infoRow2 = "56",
                    unitRow2 = "kg",
                    titleRow3 = "",
                    infoRow3 = "",
                    unitRow3 = "",
                )

            }
        }

    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}