package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import com.youarelaunched.challenge.ui.theme.VendorAppTypography

@Composable
fun Search(modifier: Modifier,onValueChange :(String) -> Unit ) {
    var searchText by remember { mutableStateOf("") }

        Card(
            modifier = modifier,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(percent = 50),
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier.padding(start = 14.dp),
                    value = searchText,
                    onValueChange = {
                        onValueChange.invoke(it)
                        searchText = it
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 16.sp
                    ),

                    placeholder = { Text(
                        text = "Search...",
                        color = VendorAppTheme.colors.text,
                        style = VendorAppTypography.subtitle2) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedLabelColor =  Color.Transparent,
                        cursorColor =  VendorAppTheme.colors.text,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )

                )
                Image(
                    modifier = Modifier.padding(end = 12.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_search),
                    contentDescription = "search"
                )
            }

        }


}
