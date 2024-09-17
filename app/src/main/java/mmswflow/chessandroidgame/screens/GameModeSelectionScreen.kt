package mmswflow.chessandroidgame.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import mmswflow.chessandroidgame.ChessGameViewModel
import mmswflow.chessandroidgame.R
import mmswflow.chessandroidgame.app_data.Screen
import mmswflow.chessandroidgame.chess_game_classes.ChessBoard
import mmswflow.chessandroidgame.chess_game_classes.GameMode
import mmswflow.chessandroidgame.ui_components.selection_option.SelectionCard
import mmswflow.chessandroidgame.ui_components.selection_option.SelectionIconIdentifier
import mmswflow.chessandroidgame.ui_components.utility.ScreenTopBar
import mmswflow.chessandroidgame.ui_components.UISizingValue.*
import mmswflow.chessandroidgame.ui_components.text.ScreenTitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameModeSelectionScreen(
    navHost: NavHostController,
    gameViewModel: ChessGameViewModel
){

    //Ensure that back stack doesn't get filled for no reason
    BackHandler {
        navHost.popBackStack()
    }

    Surface(
        modifier= Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.background,
    ) {

        Scaffold(
            topBar= {
                ScreenTopBar(
                    screenTitle = R.string.game_mode_selection_screen_title,
                    navHost= navHost,
                    navIcon = Icons.AutoMirrored.Filled.ArrowBack
                )
            }
        ) {
            paddingValues->

            HorizontalMultiBrowseCarousel(
                modifier= Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                state = gameViewModel.carouselState,
                preferredItemWidth = CarouselPreferredItemWidth.value.dp,
                flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(state = gameViewModel.carouselState),
                itemSpacing= CarouselItemSpacing.value.dp,
                maxSmallItemWidth= CarouselItemMaxWidth.value.dp,
                minSmallItemWidth= CarouselItemMinWidth.value.dp,
            ) {
                indx ->

                val currentGameMode = gameViewModel.currentAvailableGameModes.value[indx]
                SelectionCard(
                    identifier = {  
                        SelectionIconIdentifier(icon= currentGameMode.logo, tint= currentGameMode.tint)
                        ScreenTitleText(text = currentGameMode.name, modifier= Modifier.padding(bottom=ScreenTitleTextBottomPadding.value.dp))
                                 },
                    description = currentGameMode.description,
                    actionOnSelection = {
                            gameViewModel.gameMode.value = currentGameMode
                            if(gameViewModel.gameMode.value != GameMode.Edit){
                                gameViewModel.chessBoard.value = ChessBoard()
                            }
                            gameViewModel.viewModelScope.launch {
                                if(!gameViewModel.onlineMode.value){
                                    gameViewModel.setPlayers()
                                    Log.d("GAME_MODE_SELECTION_TEST","Finished Setting Up Players")
                                }
                                navHost.navigate(Screen.Game.route)
                                Log.d("GAME_MODE_SELECTION_TEST","Navigation Requested")
                            } },
                    modifier = Modifier,
                    minHeight = CarouselSelectionCardHeight.value,
                    maxHeight = CarouselSelectionCardHeight.value,
                )
            }
        }

    }
}