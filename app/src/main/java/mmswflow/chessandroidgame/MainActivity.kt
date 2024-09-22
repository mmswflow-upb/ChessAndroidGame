package mmswflow.chessandroidgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.lifecycle.viewmodel.compose.viewModel
import mmswflow.chessandroidgame.ui.theme.ChessAndroidGameTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ChessAndroidGameTheme {

                val chessGameViewModel : ChessGameViewModel = viewModel()
                chessGameViewModel.initializeResources(this)
                // A surface container using the 'background' color from the theme
                NavigationHandler(chessGameViewModel)
            }
        }
    }
}

