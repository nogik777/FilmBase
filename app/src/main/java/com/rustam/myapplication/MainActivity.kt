package com.rustam.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rustam.myapplication.presentation.navigation.AppNavigation
import com.rustam.myapplication.ui.theme.FilmBaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmBaseTheme {
                AppNavigation()
            }
        }
    }
}

