package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {
    private val _vendors = MutableStateFlow(listOf<Vendor>())
    val vendors = _vendors.asStateFlow()
    private val _filter = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val uiState: Flow<VendorsScreenUiState> =
        _filter.debounce(500)
            .map { if(it.length >= 3 ){it} else "" }
            .combine(_vendors) { filter, vendors ->
                filterVendorsByCompanyName(filter, vendors)
            }.map {
                if (it.isEmpty()) {
                    VendorsScreenUiState.EmptyFilterResult
                } else {
                    VendorsScreenUiState.ListVendors(it)
                }
            }

    init {
        getVendors()
    }

    fun getVendors() {
        viewModelScope.launch {
            _vendors.emit(repository.getVendors())
        }
    }

    fun updateFilter(filter: String) {
        _filter.value = filter
    }

    private fun filterVendorsByCompanyName(filter: String, list: List<Vendor>): List<Vendor> =
        if (filter.isEmpty()) {
            list
        } else {
            list.filter {
                it.companyName.toFilterRules().contains(filter.toFilterRules())
            }
        }

    private fun String.toFilterRules() = this.lowercase().replace(" ", "")
}