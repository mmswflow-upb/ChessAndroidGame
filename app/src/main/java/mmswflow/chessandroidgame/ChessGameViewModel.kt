package mmswflow.chessandroidgame

import android.content.Context
import android.media.SoundPool
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mmswflow.chessandroidgame.chess_game_classes.PieceColor
import mmswflow.chessandroidgame.chess_game_classes.ChessBoard
import mmswflow.chessandroidgame.chess_game_classes.GameMode
import mmswflow.chessandroidgame.app_ui_data.GameTheme
import mmswflow.chessandroidgame.chess_game_classes.HistoryOfGameMoves
import mmswflow.chessandroidgame.chess_game_classes.Player
import mmswflow.chessandroidgame.chess_game_classes.ChessPiece
import mmswflow.chessandroidgame.chess_game_classes.Move
import mmswflow.chessandroidgame.chess_game_classes.PiecePosition
import mmswflow.chessandroidgame.chess_game_classes.offlineListOfGameModes

class ChessGameViewModel: ViewModel(){

    //Game UI Options
    val uiTheme: MutableState<GameTheme> = mutableStateOf(GameTheme.Normal)
    val showPiecePath : MutableState<Boolean> = mutableStateOf(true)
    val inGameSoundEffects: MutableState<Boolean> = mutableStateOf(true)

    //Game sound effects
    private lateinit var soundPool: SoundPool
    private var pieceSlideSound : Int = R.raw.chess_piece_slide
    private var pieceCaptureSound: Int = R.raw.piece_capture
    private var invalidMoveSound : Int = R.raw.chess_piece_bounce


    //Selectable Game Options
    val currentAvailableGameModes: MutableState<List<GameMode>> = mutableStateOf(offlineListOfGameModes)
    @OptIn(ExperimentalMaterial3Api::class)
    val carouselState = CarouselState(itemCount= {currentAvailableGameModes.value.count()})
    val onlineMode: MutableState<Boolean> = mutableStateOf(false)
    val gameMode: MutableState<GameMode?> = mutableStateOf(null)


    //Current Game Related
    val chessBoard: MutableState<ChessBoard?> = mutableStateOf(null)
    private val testChessBoard: MutableState<ChessBoard?> = mutableStateOf(null) //Use this to simulate moves in cases of checks
    val player1: MutableState<Player?> = mutableStateOf(null)
    val player2: MutableState<Player?> = mutableStateOf(null)
    val playerInTurn: MutableState<Player?> = mutableStateOf(null)
    val selectedChessPiece: MutableState<ChessPiece?> = mutableStateOf(null)
    private var currentTimerJob : MutableState<Job?> = mutableStateOf(null)
    val player1RemainingTime: MutableState<Int> = mutableStateOf(0)
    val player2RemainingTime: MutableState<Int> = mutableStateOf(0)

    //Game ending Stats
    val winnerPlayer : MutableState<Player?> = mutableStateOf(null)
    val gameEnded: MutableState<Boolean> = mutableStateOf(false)
    val displayGameEndedDialog: MutableState<Boolean> = mutableStateOf(false)

    //Game History Related
    val historyOfGameMoves: MutableState<HistoryOfGameMoves?> = mutableStateOf(null)

    //Resources Related
    fun initializeResources(context: Context){
        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        invalidMoveSound = soundPool.load(context, invalidMoveSound, 1)
        pieceCaptureSound = soundPool.load(context, pieceCaptureSound, 1)
        pieceSlideSound = soundPool.load(context, pieceSlideSound, 1)
    }
    private fun playSoundEffect(sound: Int){


        if(::soundPool.isInitialized){
            soundPool.play(sound, 1f, 1f, 1, 0, 1f)
        }
    }

    // Release the resources when the view-model is cleared
    override fun onCleared() {
        super.onCleared()
        if (::soundPool.isInitialized) {
            soundPool.release()
        }
    }

    private fun getPlayerStats(name: String): List<Int>{

        val result = mutableListOf(0,0,0)

        if(onlineMode.value){

            //Fetch from server
        }else{

            //Fetch from storage
        }

        return result
    }

    private fun chooseRandomColorOffline() : Pair<PieceColor,PieceColor>{

        val randomBinary = if(Math.random() < 0.5) 0 else 1

        var firstColor = PieceColor.White
        var secondColor = PieceColor.Black

        if(randomBinary == 0){
            firstColor = PieceColor.Black
            secondColor = PieceColor.White
        }


        return Pair(firstColor, secondColor)
    }

    fun setPlayers(){

        val colorPair : Pair<PieceColor,PieceColor>

        val player1Name  : String
        val player2Name : String


        if(onlineMode.value){

            colorPair = chooseRandomColorOffline()
            player1Name = ""
            player2Name = ""

        }else{
            colorPair = chooseRandomColorOffline()
            player1Name = "Player 1"
            player2Name = "Player 2"
        }


        val player1Stats = getPlayerStats(player1Name)
        val player2Stats = getPlayerStats(player2Name)

        val player1Pieces = if(colorPair.first == PieceColor.White) chessBoard.value!!.whitePieces else chessBoard.value!!.blackPieces
        val player2Pieces = if(colorPair.second == PieceColor.White) chessBoard.value!!.whitePieces else chessBoard.value!!.blackPieces

        player1.value = Player(
            name= player1Name,
            color= colorPair.first,
            remainingTime= gameMode.value!!.timeLimit,
            wins= player1Stats[0],
            losses = player1Stats[1],
            draws= player1Stats[2],
            remainingPieces= player1Pieces,
            online= true,
        )
        player1RemainingTime.value = gameMode.value!!.timeLimit

        player2.value = Player(
            name= player2Name,
            color= colorPair.second,
            wins= player2Stats[0],
            losses = player2Stats[1],
            draws= player2Stats[2],
            remainingTime= gameMode.value!!.timeLimit,
            remainingPieces = player2Pieces,
            online= true
        )
        player2RemainingTime.value = gameMode.value!!.timeLimit


        if(player1.value!!.color == PieceColor.White){

            player1.value!!.active = true
            playerInTurn.value = player1.value!!
        }else{
            player2.value!!.active = true
            playerInTurn.value = player2.value!!
        }


        //Initialize the object that will contain all the moves made throughout the game
        historyOfGameMoves.value = HistoryOfGameMoves(
            startingBoard= chessBoard.value!!,
            moves= mutableListOf<Move>(),
            gameMode= gameMode.value!!,
            player1= player1.value!!,
            player2= player2.value!!
        )

    }

    //Turn on the timer for the current player
    fun startTimer(){

        if(currentTimerJob.value != null){
            return
        }

        currentTimerJob.value = viewModelScope.launch {
            runTimer(playerInTurn.value!!)
        }
    }


    //A function that runs the timer for the current player
    private suspend fun runTimer(player: Player){

        //Each second subtract from the current player's time
        while(player.remainingTime > 0){
            delay(1000)
            player.remainingTime -= 1

            if(player == player1.value){
                player1RemainingTime.value -= 1
            }else{
                player2RemainingTime.value -= 1
            }
        }

        //Game should end if this while loop is terminated, meaning that the current player ran out of time
        if(playerInTurn.value == player1.value){

            //Winner is player 2, since player 1 ran out of time
            endGame(player2.value!!)

        }else{
            //Winner is player 1, since player 2 ran out of time
            endGame(player1.value!!)
        }
    }

    //A function that cancels the current coroutine which is running the timer
    private fun stopTimer(){

        try{
            currentTimerJob.value?.cancel()
        }catch(error: CancellationException){
            Log.e("GAME FLOW TEST", "Timer Error: ${error.message}")
        }
    }


    //It ends the game by setting the states to their appropriate values.
    private fun endGame(winningPlayer: Player){

        if(onlineMode.value){

            //TODO Online mode

        }else{

            //Offline mode
            stopTimer()
            addGameToHistory()
            saveStats()

            winnerPlayer.value = winningPlayer
            gameEnded.value = true
            displayGameEndedDialog.value = true
            player1.value!!.active = false
            player2.value!!.active = false
        }
    }

    //Save the game into the storage of the device or cloud (account-based)
    private fun addGameToHistory(){}

    //Save stats to cloud (account-based) or on device's storage
    private fun saveStats(){}

    // Simulate a move to check whether it's legal or not, and return true if it's valid
    //this method will also look for checks or checkmates
    private fun simulateMove(newPosition: PiecePosition): Boolean{

        var result = false

        return result
    }

    fun movePiece(newPosition : PiecePosition){

        //Check whether move is legal
        if(!simulateMove(newPosition)){

            //Move is invalid, so we play the invalid sound effect
            playSoundEffect(invalidMoveSound)
            return
        }
        //TODO Save the move to the history object after validating it


        //Find out which player has to play next after the move was validated
        playerInTurn.value = if(player1.value!!.active) player2.value else player1.value

        //Stop the current Timer if it exists
        stopTimer()

        //Start a new coroutine to resume the timer for the next player
        currentTimerJob.value = viewModelScope.launch {
            runTimer(playerInTurn.value!!)
        }


    }
}