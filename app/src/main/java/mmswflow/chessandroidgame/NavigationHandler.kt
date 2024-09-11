package mmswflow.chessandroidgame

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mmswflow.chessandroidgame.data.Screen
import mmswflow.chessandroidgame.screens.GameModeSelectionScreen
import mmswflow.chessandroidgame.screens.GameMovesHistoryScreen
import mmswflow.chessandroidgame.screens.GameMultiplayerSelectionScreen
import mmswflow.chessandroidgame.screens.GameScreen
import mmswflow.chessandroidgame.screens.GameSettingsScreen
import mmswflow.chessandroidgame.screens.GamesHistoryListScreen
import mmswflow.chessandroidgame.screens.HomeScreen


@Composable
fun NavigationHandler(){



    val navHost = rememberNavController()
    val chessGameViewModel : ChessGameViewModel = viewModel()
    NavHost(navController= navHost, startDestination= Screen.Home.route){

        composable(route= Screen.Home.route){

            HomeScreen(navHost= navHost)
        }

        composable(route= Screen.GameMultiplayerSelection.route){

            GameMultiplayerSelectionScreen(gameViewModel= chessGameViewModel, navHost= navHost)
        }

        composable(route= Screen.GameModeSelection.route){

            GameModeSelectionScreen(gameViewModel= chessGameViewModel, navHost= navHost)
        }

        composable(route= Screen.Game.route){

            GameScreen()
        }

        composable(route= Screen.GamesHistoryList.route){
            GamesHistoryListScreen()
        }

        composable(route= Screen.GameMovesHistory.route){
            GameMovesHistoryScreen()
        }

        composable(route= Screen.GameSettings.route){
            GameSettingsScreen()
        }

    }


}
