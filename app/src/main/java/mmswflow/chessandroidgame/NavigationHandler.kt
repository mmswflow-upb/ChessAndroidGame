package mmswflow.chessandroidgame

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mmswflow.chessandroidgame.data.Screen
import mmswflow.chessandroidgame.screens.GameModeSelectionScreen
import mmswflow.chessandroidgame.screens.GameScreen
import mmswflow.chessandroidgame.screens.HistoryScreen
import mmswflow.chessandroidgame.screens.HomeScreen


@Composable
fun NavigationHandler(){



    val navHost = rememberNavController()
    val chessGameViewModel : ChessGameViewModel = viewModel()
    NavHost(navController= navHost, startDestination= Screen.Home.route){


        composable(route= Screen.Home.route){

            HomeScreen(navHost= navHost)
        }

        composable(route= Screen.GameModeSelection.route){

            GameModeSelectionScreen()
        }



        composable(route= Screen.Game.route){

            GameScreen()
        }

        composable(route= Screen.History.route){
            HistoryScreen()
        }
    }


}
