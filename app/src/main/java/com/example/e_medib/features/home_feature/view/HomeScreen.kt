package com.example.e_medib.features.home_feature.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_medib.navigations.TabItem
import com.example.e_medib.ui.theme.*
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold() {
        HomeScreenComponent()
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreenComponent() {
    val today = LocalDate.now()
    val datePickerState =
        rememberDatePickerState(initialDate = LocalDate.now())
    val listTabs = listOf(TabItem.Diary, TabItem.KondisiKesehatan)
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        BoxWithConstraints(
            modifier = Modifier
                .height(360.dp)
                .fillMaxWidth()
        ) {
            val maxHeight = this.maxHeight
            val topHeight: Dp = maxHeight * 2 / 3

            Box(
                modifier = Modifier
                    .height(topHeight)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(color = mRedMain)
            )
            Column(modifier = Modifier.padding(top = 24.dp)) {
                // SELAMAT DATANG
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(
                            text = "Selamat Datang, ",
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            color = mWhite
                        )
                        Text(
                            text = "Zulfikri",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = mWhite
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    Text(
                        text = "Monitor kondisi darah dan kesehatanmu hari ini!",
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                            .background(color = mWhite.copy(alpha = 0.2f))
                            .padding(8.dp),

                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Normal,
                        color = mWhite
                    )
                }
                // BOX KONDISI HARI INI
                Card(
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 2.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = mWhite)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            // ROW STATUS
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Kondisimu Hari ini",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.Normal,
                                    color = mGrayScale
                                )
                                Text(
                                    text = "Jumat, 10 Maret 2023",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.SemiBold,
                                    color = mRedMain
                                )
                            }
                            Divider(color = mLightGrayScale, thickness = 1.dp)

                            // ROW BMI BMR KALKULATOR
                            CustomRowInfo(
                                modifier = Modifier,
                                titleRow1 = "BMI",
                                titleRow2 = "BMR",
                                titleRow3 = "Kalkulator",
                                infoRow1 = "20,7",
                                infoRow2 = "1513",
                                infoRow3 = "",
                                unitRow1 = "",
                                unitRow2 = "Cal/day",
                                unitRow3 = "",
                                useRow3Button = true,
                                onRow3Click = {}
                            )
                            Divider(
                                modifier = Modifier.padding(top = 16.dp),
                                color = mLightGrayScale,
                                thickness = 1.dp
                            )

                            // ROW GULA DARAH
                            CustomRowInfo(
                                modifier = Modifier,
                                titleRow1 = "Gula Darah",
                                infoRow1 = "123",
                                unitRow1 = "mg/dl",
                                titleRow2 = "Tekanan Darah",
                                infoRow2 = "-",
                                unitRow2 = "mmHg",
                                titleRow3 = "Kolesterol",
                                infoRow3 = "-",
                                unitRow3 = "mg/dl",
                            )

                        }

                    }
                }

            }
        }


        // ROW TANGGAL
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
                .clickable { }
        ) {
            Text(
                text = "Maret 2023",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                color = mRedMain
            )
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "Calendar",
                tint = mRedMain,
            )
        }
        DatePickerTimeline(
            modifier = Modifier,
            state = datePickerState,
            selectedBackgroundColor = mRedMain,
            selectedTextColor = mWhite,
            dateTextColor = mGrayScale,
            onDateSelected = { selectedDate: LocalDate ->
                //do something with selected date
                Log.d("date", "HomeScreenComponent: $selectedDate")
            },
        )

        // TAB MENU KONDISI KESEHATAN DAN DIARY
        Tabs(tabs = listTabs, pagerState = pagerState)
        TabContent(tabs = listTabs, pagerState = pagerState)


    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = mWhite,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        },
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(index) }
                },
                selectedContentColor = mRedMain,
                unselectedContentColor = mBlack,
                enabled = true,
                text = { Text(text = tabItem.title) }, icon = {})
        }

    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun HomeScreenPreview() {
    HomeScreenComponent()
}