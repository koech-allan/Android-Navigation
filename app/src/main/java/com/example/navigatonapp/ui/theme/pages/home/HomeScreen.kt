package com.example.navigatonapp.ui.theme.pages.home

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigatonapp.navigation.ROUTE_ADD_PRODUCTS
import com.example.navigatonapp.navigation.ROUTE_VIEW_PRODUCTS
import com.example.navigatonapp.navigation.ROUTE_VIEW_PRODUCTS
import com.example.navigatonapp.ui.theme.NavigatonAppTheme

@Composable
fun HomeScreen(navController: NavHostController){
   Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
       Text(text = "Welcome to home page")
       Button(onClick = {
           navController.navigate(ROUTE_ADD_PRODUCTS)
       }) {
           Text(text = "Add products")

       }

       Button(onClick = {
           navController.navigate(ROUTE_VIEW_PRODUCTS)
       }) {
           Text(text = "view products")

       }

   }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    NavigatonAppTheme {
        HomeScreen(rememberNavController())
    }

}