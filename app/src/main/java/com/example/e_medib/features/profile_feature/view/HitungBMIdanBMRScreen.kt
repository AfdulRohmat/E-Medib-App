package com.example.e_medib.features.profile_feature.view

import CustomInputField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_medib.features.home_feature.view.HomeScreenTabs
import com.example.e_medib.navigations.HomeScreenTabItem
import com.example.e_medib.navigations.ProfileScreenTabItem
import com.example.e_medib.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HitungBMIdanBMRScreen(navController: NavController) {
    val tinggiBadan = rememberSaveable() { mutableStateOf("") }
    val beratBadan = rememberSaveable() { mutableStateOf("") }
    val listTabs = listOf(ProfileScreenTabItem.BMIScreen, ProfileScreenTabItem.BMRScreen)
    val pagerState = rememberPagerState(initialPage = 0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hitung BMI dan BMR Anda",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        color = mWhite
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                          navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "arrow_back",
                            tint = mWhite
                        )
                    }
                },
                backgroundColor = mRedMain
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // TITLE
            Text(
                text = "Masukan Tinggi dan Berat Badan anda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 8.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )

            // ROW INPUT TINGGI BADAN DAN BERAT BADAN
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TB
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Tinggi Badan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = mBlack
                    )
                    CustomInputField(
                        valueState = tinggiBadan,
                        placeholder = "00",
                        trailingIcon = {
                            Text(
                                text = "cm",
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.Bold,
                                color = mGrayScale
                            )
                        },
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    )

                }
                Spacer(modifier = Modifier.width(16.dp))

                // BB
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Berat Badan",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = mBlack
                    )
                    CustomInputField(
                        valueState = beratBadan,
                        placeholder = "00",
                        trailingIcon = {
                            Text(
                                text = "kg",
                                style = MaterialTheme.typography.caption,
                                fontWeight = FontWeight.Bold,
                                color = mGrayScale
                            )
                        },
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                    )

                }

            }

            // BUTTON HITUNG
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = mRedMain,
                    contentColor = mWhite,
                    disabledBackgroundColor = mLightGrayScale,
                    disabledContentColor = mBlack
                ),
                shape = RoundedCornerShape(32.dp),
                enabled = true
            ) {
                Text(
                    text = "Hitung",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = mWhite
                )
            }


            // HASIL PERHITUNGAN (TAB BMI DAN BMR)
            Text(
                text = "Hasil Perhitungan",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 12.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = mBlack
            )
            Column(
                modifier = Modifier
                    .height(500.dp)
            ) {
                ProfileScreenTabs(tabs = listTabs, pagerState = pagerState)

            }

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreenTabs(
    tabs: List<ProfileScreenTabItem>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = mWhite,
        indicator = { tabPositions ->
            Modifier
                .pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        },
    ) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(index) }
                },
                selectedContentColor = mRedMain,
                unselectedContentColor = mBlack,
                enabled = true,
                text = { Text(text = tabItem.title) },
            )
        }

    }


    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HitungBMIScreenPreview() {
    val navController = rememberNavController()

    HitungBMIdanBMRScreen(navController)

}
