package com.youarelaunched.challenge.ui.screen.state

import com.youarelaunched.challenge.data.repository.model.Vendor

sealed interface VendorsScreenUiState {
    data class ListVendors(val vendors: List<Vendor> = listOf()) : VendorsScreenUiState
    object EmptyFilterResult : VendorsScreenUiState
}
