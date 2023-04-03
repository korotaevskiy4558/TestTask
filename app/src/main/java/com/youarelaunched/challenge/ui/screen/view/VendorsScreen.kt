package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.NoResults
import com.youarelaunched.challenge.ui.screen.view.components.Search
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState(VendorsScreenUiState.ListVendors())
    val onValueChange: (String) -> Unit = { viewModel.updateFilter(it) }
    VendorsScreen(uiState = uiState, onValueChange)
}

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState,
    onValueChange: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) }
    ) { paddings ->
        Column(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Search(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                onValueChange.invoke(it)
            }
            when(uiState){
                VendorsScreenUiState.EmptyFilterResult ->  NoResults()
                is VendorsScreenUiState.ListVendors -> ListVendors(
                    paddingValues = paddings,
                    uiState.vendors
                )
            }

        }

    }
}

@Composable
fun ListVendors(paddingValues: PaddingValues, vendors: List<Vendor>) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .testTag("ListVendors"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            vertical = 24.dp,
            horizontal = 16.dp
        )
    ) {
        items(vendors) { vendor ->
            VendorItem(
                vendor = vendor
            )
        }

    }
}