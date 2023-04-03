package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import com.youarelaunched.challenge.ui.theme.VendorAppTypography

@Composable
fun  NoResults(){
    Box( modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
           horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sorry! No results found...",
                color = VendorAppTheme.colors.titleText,
                style = VendorAppTypography.h2
            )
            Text(modifier = Modifier.padding(start = 28.dp, end = 28.dp),
                text = "Please try a different search request\nor browse businesses from the list",
                color = VendorAppTheme.colors.textDark,
                style = VendorAppTypography.subtitle1,
                textAlign = TextAlign.Center
            )
        }
    }
}