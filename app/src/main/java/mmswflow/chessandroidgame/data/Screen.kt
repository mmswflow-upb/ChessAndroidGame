package mmswflow.chessandroidgame.data

import mmswflow.chessandroidgame.R

sealed class Screen(
    val title: Int,
    val route: String
){

    object Home: Screen(title= R.string.home_screen_title, route= "home_screen")
    object GameMultiplayerSelection: Screen(title= R.string.game_multiplayer_selection_screen_title, route= "multiplayer_selection_screen")

    object GameModeSelection: Screen(title= R.string.game_mode_selection_screen_title, route= "game_mode_selection_screen")
    object Game: Screen(title= R.string.game_screen_title, route= "game_screen")
    object GamesHistoryList: Screen(title= R.string.history_list_screen_title, route= "history_list_screen")

    object GameMovesHistory: Screen(title= R.string.history_screen_title, route= "game_moves_history")

    object GameSettings: Screen(title= R.string.game_settings_screen_title, route= "game_settings_screen")
}