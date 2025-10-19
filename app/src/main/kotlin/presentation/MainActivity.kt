package ru.work_mate.rick_and_morty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.work_mate.rick_and_morty.presentation.ui.DetailScreen
import ru.work_mate.rick_and_morty.presentation.ui.ScreenDispatcher
import ru.work_mate.rick_and_morty.presentation.ui.theme.ApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ApplicationTheme {
                ScreenDispatcher()
                //DetailScreen(1)
            }
        }
    }
}
