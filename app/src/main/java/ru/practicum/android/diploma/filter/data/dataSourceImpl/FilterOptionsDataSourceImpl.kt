package ru.practicum.android.diploma.filter.data.dataSourceImpl

import ru.practicum.android.diploma.filter.data.dataSource.FilterOptionsDataSource
import ru.practicum.android.diploma.filter.data.db.FilterDb
import ru.practicum.android.diploma.filter.data.db.LocalCache
import ru.practicum.android.diploma.filter.data.model.dto.Filter

class FilterOptionsDataSourceImpl(
    private val filterDb: FilterDb,
    private val localCache: LocalCache
) : FilterOptionsDataSource {
    override fun getFilterOptions(): Filter? {
        return if (localCache.getFilterCache() == null) {
            filterDb.getFilterOptions()
        } else {
            localCache.getFilterCache()
        }

    }

    override fun putFilterOptions(options: Filter) {
        filterDb.putFilterOptions(options)
    }

    override fun clearFilterOptions() {
        filterDb.clearSavedFilter()
        localCache.clear()
    }
}