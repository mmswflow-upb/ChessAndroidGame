package mmswflow.chessandroidgame

import android.content.Context
import android.media.SoundPool
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
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
import mmswflow.chessandroidgame.chess_game_classes.King
import mmswflow.chessandroidgame.chess_game_classes.Move
import mmswflow.chessandroidgame.chess_game_classes.Pawn
import mmswflow.chessandroidgame.chess_game_classes.PiecePosition
import mmswflow.chessandroidgame.chess_game_classes.offlineListOfGameModes

class ChessGameViewModel: ViewModel(){

    //Game UI Options
    val uiTheme: MutableState<GameTheme> = mutableStateOf(GameTheme.Normal)
    val showPiecePath : MutableState<Boolean> = mutableStateOf(true)
    val inGameSoundEffects: MutableState<Boolean> = mutableStateOf(true)

    //Game sound effects
    private lateinit var soundPool: SoundPool
    private var pieceSlideSound : Int = 0
    private var pieceCaptureSound: Int = 0
    private var invalidMoveSound : Int = 0


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
    val enemyPiecesCheckingPlayerInTurn: MutableState<MutableList<ChessPiece>> = mutableStateOf(mutableListOf())
    val piecesAbleToSavePlayerInTurn: MutableState<MutableList<ChessPiece>> = mutableStateOf(mutableListOf())
    val enPassantEdiblePiece : MutableState<Pawn?> = mutableStateOf(null)

    //Game Small Menu Related
    val displayResignConfirmation: MutableState<Boolean> = mutableStateOf(false)
    val displayDrawDialog: MutableState<Boolean> = mutableStateOf(false)

    //Game ending Stats
    val winnerPlayer : MutableState<Player?> = mutableStateOf(null)
    val reasonForWinning: MutableState<Int> = mutableStateOf(0)
    val gameEnded: MutableState<Boolean> = mutableStateOf(false)
    val displayGameEndedDialog: MutableState<Boolean> = mutableStateOf(false)

    //Game History Related
    val historyOfGameMoves: MutableState<HistoryOfGameMoves?> = mutableStateOf(null)
    val currentMove : MutableState<Move?> = mutableStateOf(null)

    //Resources Related
    fun initializeResources(context: Context){

        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        invalidMoveSound = soundPool.load(context, R.raw.chess_piece_bounce, 1)
        pieceCaptureSound = soundPool.load(context, R.raw.piece_capture, 1)
        pieceSlideSound = soundPool.load(context, R.raw.chess_piece_slide, 1)
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

    //Setup players at the start of the game
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
            player2= player2.value!!,

        )

        startTimer()
    }

    //Turn on the timer for the current player
    private fun startTimer(){

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
        reasonForWinning.value = R.string.out_of_time_reason_for_winning

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

        currentTimerJob.value?.cancel()
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
            displayResignConfirmation.value = false
            displayDrawDialog.value = false
            displayGameEndedDialog.value = true
            player1.value!!.active = false
            player2.value!!.active = false
        }
    }

    //Save the game into the storage of the device or cloud (account-based)
    private fun addGameToHistory(){}

    //Save stats to cloud (account-based) or on device's storage
    private fun saveStats(){}


    //this method will look for checks or checkmates, if there's a check it's
    //represented by 0, if there's a checkmate it's represented by 1, if none then it's 2
    private fun findChecksOrCheckMates(): Int{

        return 2
    }



    //Find who's the enemy, and loop through the pieces of the enemy to check whether they're checking our king or not
    //and add the checking pieces to the appropriate list
    private fun findPiecesThatCheck(chessBoard: ChessBoard, enemyColor: PieceColor){


        if(enemyColor == PieceColor.White){

            val blackKing = chessBoard.getKing(PieceColor.Black)

            for(piece in chessBoard.whitePieces){

                if(piece.protectsPosition(chessBoard,blackKing.position)){
                    enemyPiecesCheckingPlayerInTurn.value.add(piece)
                }
            }

        }else{

            val whiteKing = chessBoard.getKing(PieceColor.White)

            for(piece in chessBoard.whitePieces){

                if(piece.protectsPosition(chessBoard,whiteKing.position)){
                    enemyPiecesCheckingPlayerInTurn.value.add(piece)
                }
            }
        }
    }

    //Gives the enemy of the current player in turn
    private fun getEnemyPlayer(): Player{

        if(playerInTurn.value == player1.value){
            return player2.value!!
        }
        return player1.value!!
    }

    // Simulate a move to check whether it causes a check for player 1 or player 2
    //True means it will cause a check, false means it won't
    private fun simulateMoveCausingCheck(movingPiece: ChessPiece,newPosition: PiecePosition, enemyColor: PieceColor): Boolean{


        testChessBoard.value = chessBoard.value!!.deepClone()

        val oldRow = movingPiece.position.row
        val oldColumn = movingPiece.position.column

        val newRow = newPosition.row
        val newColumn = newPosition.column

        //Nullify the occupying piece after saving it temporarily
        val tempPiece = testChessBoard.value!!.boardMatrix[oldRow][oldColumn].occupyingPiece
        testChessBoard.value!!.boardMatrix[oldRow][oldColumn ].occupyingPiece = null

        //Move piece to new position on test board
        testChessBoard.value!!.boardMatrix[newRow][newColumn].occupyingPiece = tempPiece

        findPiecesThatCheck(testChessBoard.value!!, enemyColor)

        if(enemyPiecesCheckingPlayerInTurn.value.isNotEmpty()){
            return true
        }


        return false
    }

    fun movePiece(newPosition : PiecePosition){

        // Step 1: Check whether move is legal
        if(!selectedChessPiece.value!!.isPieceMoveLegal(chessBoard.value!!,newPosition, enPassantEdiblePiece.value)){

            //Move is invalid, so we play the invalid sound effect
            playSoundEffect(invalidMoveSound)
            return
        }

        // Step 2: Check whether the selected piece is part of the list of pieces that can save the king (in case there's a check)
        if(playerInTurn.value!!.underCheck){

            //Piece can't be moved because it won't save the king from the check
            if(!piecesAbleToSavePlayerInTurn.value.any { it == selectedChessPiece.value}){
                playSoundEffect(invalidMoveSound)
                return
            }
        }

        // Step 3: Check whether moving the selected piece will cause a check for the king
        if(simulateMoveCausingCheck(selectedChessPiece.value!!,newPosition,getEnemyPlayer().color)){

            enemyPiecesCheckingPlayerInTurn.value.clear()
            playSoundEffect(invalidMoveSound)
            return
        }

        //Step 4: Check whether moving the selected piece will cause a check for the enemy king
        if(simulateMoveCausingCheck(selectedChessPiece.value!!,newPosition,playerInTurn.value!!.color)){




            //Step 5: Check if the enemy player can escape from the check we're causing (in case there exists one)


        }



        //TODO Save the move to the history object after validating it


        //Find out which player has to play next after the move was validated
        if(player1.value!!.active){

            //Player 1 was in turn, next is player 2
            player1.value!!.active = false
            player2.value!!.active = true

        }else{

            //Player 2 was in turn, next is player 1
            player2.value!!.active = false
            player1.value!!.active = true

        }

        playerInTurn.value = getEnemyPlayer()

        //Stop the current Timer if it exists
        stopTimer()

        //Start a new coroutine to resume the timer for the next player
        currentTimerJob.value = viewModelScope.launch {
            runTimer(playerInTurn.value!!)
        }


    }
}