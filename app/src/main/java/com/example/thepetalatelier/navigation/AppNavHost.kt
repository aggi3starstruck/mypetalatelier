package com.example.thepetalatelier.navigation

import androidx.compose.runtime.Composable
//import androidx.navigation.NavDestination
//import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myprecioustraditionalattires.ui.theme.screens.Item.UpdateFlowerScreen
import com.example.thepetalatelier.ui.theme.screens.Buyer.AddBuyerScreen
import com.example.thepetalatelier.ui.theme.screens.Buyer.UpdateBuyerScreen
import com.example.thepetalatelier.ui.theme.screens.Buyer.ViewBuyer
import com.example.thepetalatelier.ui.theme.screens.Flower.AddFlowerScreen
import com.example.thepetalatelier.ui.theme.screens.Flower.ViewFlowerScreen
import com.example.thepetalatelier.ui.theme.screens.dashboard.Dashboard
import com.example.thepetalatelier.ui.theme.screens.login.LoginScreen
import com.example.thepetalatelier.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_LOGIN
               ){
    NavHost(
        navController = navController,
        startDestination = startDestination,){
        composable( ROUTE_SPLASH_SCREEN) {
            navController.navigate(ROUTE_REGISTER){
                popUpTo(ROUTE_SPLASH_SCREEN){inclusive = true}

            }
        }
        composable(ROUTE_REGISTER) { SignupScreen(navController)  }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_HOME) { Dashboard(navController) }
        composable(ROUTE_ADD_BUYER) { AddBuyerScreen(navController)  }
        composable(ROUTE_VIEW_BUYER) { ViewBuyer(navController) }


        composable("$ROUTE_UPDATE_BUYER/{$id}") {
            passedData -> UpdateBuyerScreen(
                navController, passedData.arguments?.getString("id")!!
            )
        }
        composable(ROUTE_VIEW_FLOWER) { ViewFlowerScreen(navController)  }
        composable("$ROUTE_UPDATE_FLOWER/{$id}") { passedData -> UpdateFlowerScreen(
            navController, passedData.arguments?.getString("id")!!
        ) }

        composable(ROUTE_ADD_FLOWER) { AddFlowerScreen(navController)  }




    }


}