package mmswflow.chessandroidgame.data

import mmswflow.chessandroidgame.R

sealed class Screen(
    val title: Int,
    val route: String
){

    object Home: Screen(title= R.string.home_screen_title, route= "home_screen")
    object GameModeSelection: Screen(title= R.string.game_mode_selection_screen_title, route= "game_mode_selection_screen")
    object Game: Screen(title= R.string.game_screen_title, route= "game_screen")
    object History: Screen(title= R.string.history_screen_title, route= "history_screen")
}