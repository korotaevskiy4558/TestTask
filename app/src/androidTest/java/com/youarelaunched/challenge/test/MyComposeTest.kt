package com.youarelaunched.challenge.test

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.VendorsScreen
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyComposeTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun noResultsIsShow() {

        composeTestRule.setContent {
            VendorAppTheme {
                VendorsScreen(VendorsScreenUiState.EmptyFilterResult) {}
            }
        }
        composeTestRule.onNodeWithText("Sorry! No results found...").assertIsDisplayed()
        composeTestRule.onNodeWithText("Please try a different search request\nor browse businesses from the list").assertIsDisplayed()

    }

    @Test
    fun listIsShow() {

        val fakeList = listOf(
            Vendor(
                0,"","","", true, listOf(),""
            )
        )

        composeTestRule.setContent {
            VendorAppTheme {
                VendorsScreen(VendorsScreenUiState.ListVendors(fakeList)) {}
            }
        }

        composeTestRule.onNodeWithTag("ListVendors")
           .onChildren()
           .onFirst()
           .assertExists()
    }
}