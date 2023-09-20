package com.example.navigatonapp.ui.theme.pages.about

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigatonapp.ui.theme.NavigatonAppTheme

@Composable
fun AboutScreen(navController: NavHostController){
    Text(text = "Welcome to about screen")

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun AboutScreenPreview() {
    NavigatonAppTheme() {
        AboutScreen(rememberNavController())

    }

}