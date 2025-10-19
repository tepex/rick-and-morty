package ru.work_mate.rick_and_morty.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
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
                 LazyColumn {
                     items(10) { i ->
                         Button(onClick = {
                             backStack.add(DetailRoute(i))
                         }) {
                             Text("item $i")
                         }
                     }
                 }
             }

            entry<DetailRoute> { id ->
                Text("Detail id: $id")
                Timber.d("select: $id")
            }
        }
    )
}
