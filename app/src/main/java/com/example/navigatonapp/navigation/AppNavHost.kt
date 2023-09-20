package com.example.navigatonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigatonapp.ui.theme.pages.SighnupScreen
import com.example.navigatonapp.ui.theme.pages.about.AboutScreen
import com.example.navigatonapp.ui.theme.pages.home.HomeScreen
import com.example.navigatonapp.ui.theme.pages.login.LoginScreen
import com.example.navigatonapp.ui.theme.pages.products.AddProductsScreen

import com.example.navigatonapp.ui.theme.pages.products.UpdateProductsScreen
import com.example.navigatonapp.ui.theme.pages.products.ViewProductsScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_LOGIN){

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination){
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_SIGNUP){
            SighnupScreen(navController)
        }
        composable(ROUTE_ABOUT){
            AboutScreen(navController)
        }
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_ADD_PRODUCTS){
            AddProductsScreen(navController)
        }


//        composable(ROUTE_DROP){
//            DropDownScreen(navController)
//        }


        composable(ROUTE_VIEW_PRODUCTS){
            ViewProductsScreen(navController)
        }
        composable(ROUTE_UPDATE_PRODUCTS+"/{id}"){passedData->
            UpdateProductsScreen(navController,passedData.arguments?.getString("id")!!)
        }
    }

}