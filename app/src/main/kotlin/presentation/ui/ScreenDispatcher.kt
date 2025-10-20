package ru.work_mate.rick_and_morty.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
//import androidx.hilt.l
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.work_mate.rick_and_morty.presentation.DetailViewModel
import timber.log.Timber

data object RootRoute
data class DetailRoute(val id: Int)

@Composable
fun ScreenDispatcher() {
    val backStack = remember { mutableStateListOf<Any>(RootRoute) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
             entry<RootRoute> {

                 /*
                 LazyColumn {
                     items(10) { i ->
                         Button(onClick = {
                             backStack.add(DetailRoute(i))
                         }) {
                             Text("item $i")
                         }
                     }
                 }*/
                 //val searchState = rememberTextFieldState()
                 MainScreen(hiltViewModel()) { id ->
                     backStack.add(DetailRoute(id))
                     Timber.d("go to: $id")
                 }
             }

            entry<DetailRoute> { route ->
                hiltViewModel<DetailViewModel, DetailViewModel.Factory> { it.create(route) }.also {
                    DetailScreen(it) {
                        backStack.removeLastOrNull()
                    }
                }
            }
        }
    )
}
