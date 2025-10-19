package ru.work_mate.rick_and_morty.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    searchFieldState: TextFieldState,
    result: List<String>,
    onSearch: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                actions = {
                    // Optional: Add other actions if needed
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = searchFieldState.text.toString(),
                            onQueryChange = { searchFieldState.edit { replace(0, length, it) } },
                            onSearch = {
                                searchFieldState.text.toString().apply {
                                    Timber.i("Searching for: $this")
                                    onSearch(this)
                                }
                            },
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            placeholder = { Text("Search...") },
                        )
                    }
                ) {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        result.forEach { item ->
                            ListItem(
                                headlineContent = { Text(item) },
                                modifier = Modifier
                                    .clickable {
                                        searchFieldState.edit { replace(0, length, item) }
                                        expanded = false
                                    }
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        TextFieldState(),
        listOf(
            "a", "b", "c"
        )
    ) {
        Timber.i("query: $it")
    }
}
