package com.example.e_medib.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.e_medib.features.home_feature.view.DiaryScreen
import com.example.e_medib.features.home_feature.view.KondisiKesehatanScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    val title: String, val screen: ComposableFun
) {
    object KondisiKesehatan :
        TabItem(title = "Kondisi Kesehatan", screen = { KondisiKesehatanScreen() })

    object Diary :
        TabItem(title = "Diary", screen = { DiaryScreen() })
}
