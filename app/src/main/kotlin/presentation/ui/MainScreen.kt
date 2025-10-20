package ru.work_mate.rick_and_morty.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import ru.work_mate.rick_and_morty.presentation.MainViewModel
import ru.work_mate.rick_and_morty.presentation.model.CharacterListItem
import timber.log.Timber
import java.nio.file.WatchEvent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onDetail: (Int) -> Unit
) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TitleSearchBar(state.value.search, viewModel::search)
                },
                actions = { }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) { 
                        Filter() 
                    }
                    Row {
                        SearchContent(state.value.result) { onDetail(it) }
                    }
                    Row {
                        PagingBar()
                    }
                }
                if (state.value.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .zIndex(1f)
                            .pointerInput(Unit) {},
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CircularProgressIndicator(color = Color.White)
                            Text(text = "Loading...", color = Color.White)
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleSearchBar(
    searchState: TextFieldState,
    onSearch: (String) -> Unit
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        expanded = false,
        onExpandedChange = { },
        inputField = {
            SearchBarDefaults.InputField(
                query = searchState.text.toString(),
                onQueryChange = { searchState.edit { replace(0, length, it) } },
                onSearch = onSearch,
                expanded = false,
                onExpandedChange = {  },
                placeholder = { Text("Search...") },
            )
        }
    ) {
        /*
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
        }*/
    }
}

@Composable
private fun Filter() {
    Text("filter")
}

@Composable
private fun SearchContent(
    characters: List<CharacterListItem>,
    onClick: (Int) -> Unit
) {
    Box {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(characters) { item ->
                Text(
                    modifier = Modifier
                        .clickable { onClick(item.id) }
                        .fillMaxWidth(),
                    text = item.content
                )
            }
        }
    }
}

@Composable
private fun PagingBar() {
    Text("paging")
}
