package ru.work_mate.rick_and_morty.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.work_mate.rick_and_morty.domain.model.Character
import ru.work_mate.rick_and_morty.presentation.model.CharacterItemUi

class CharacterPagingSource : PagingSource<Int, CharacterItemUi>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterItemUi>): Int? {
        // Return a key that can be used to load a page of data if the PagingSource is invalidated.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItemUi> {
        return try {
            val pageNumber = params.key ?: 0
            val pageSize = params.loadSize

            // Simulate fetching data from a remote API
            val response = fetchItems(pageNumber, pageSize)
            val items = response.items

            LoadResult.Page(
                data = items,
                prevKey = if (pageNumber == 0) null else pageNumber - 1,
                nextKey = if (items.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun fetchItems(page: Int, pageSize: Int): PagedResponse {
        val start = page * pageSize
        val end = start + pageSize
        val items = (start until end).map { i ->
            Character.Id(i+1)
            CharacterItemUi("Item $it"
        }
        return PagedResponse(items)
    }
}

data class PagedResponse(val items: List<CharacterItemUi>)