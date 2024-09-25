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
    val enemyPiecesCheckingPlayer: MutableState<MutableList<ChessPiece>> = mutableStateOf(mutableListOf())
    val piecesAbleToSavePlayer: MutableState<MutableList<ChessPiece>> = mutableStateOf(mutableListOf())

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

        endGame(getEnemyPlayer())
    }

    //A function that cancels the current coroutine which is running the timer
    private fun stopTimer(){

        currentTimerJob.value?.cancel()
    }


    //It ends the game by setting the states to their appropriate values.
    private fun endGame(winningPlayer: Player?){

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

    //Gives the enemy of the current player in turn
    private fun getEnemyPlayer(): Player{

        if(playerInTurn.value == player1.value){
            return player2.value!!
        }
        return player1.value!!
    }

    //Checks whether the enemy of the current player in turn has any moves left or not
    private fun detectStalemate(chessBoard: ChessBoard, enemyColor: PieceColor): Boolean{

        if(enemyColor == PieceColor.White){

            for(blackPiece in chessBoard.blackPieces){
                if(blackPiece.getAllLegalNewPositions(chessBoard, chessBoard.enPassantEdiblePiece).isNotEmpty()){
                    return false
                }
            }

        }else{

            for(whitePiece in chessBoard.whitePieces){
                if(whitePiece.getAllLegalNewPositions(chessBoard, chessBoard.enPassantEdiblePiece).isNotEmpty()){
                    return false
                }
            }
        }

        return true
    }

    //Find the enemy pieces that can save their king from a check, if there exist such pieces
    //then the enemy player will be able to move when their turn comes, otherwise checkmate will be established
    //and the current player in turn will win
    //Returns true for possibility of escaping a check, false for checkmate
    private fun canEscapeCheck(testChessBoard: ChessBoard, enemyColor: PieceColor): Boolean{

        //Loop through all pieces of enemy, and loop through all their possible positions,
        //and check in each case whether the check on their king can be stopped
        //for each move we find, we add it the piece's list of positions that can save the king
        //and we add that piece to the list of pieces that can save the enemy player


        //get a list of the enemy's pieces
        val listOfClonedPieces = if(enemyColor == PieceColor.White){
            testChessBoard.whitePieces
        }else{
            testChessBoard.blackPieces
        }

        //loop through enemy's pieces
        for(enemyPiece in listOfClonedPieces){

            //loop through all possible positions of enemy's pieces (legal ones)
            for(newPos in enemyPiece.getAllLegalNewPositions(testChessBoard, testChessBoard.enPassantEdiblePiece)){

                //Save original position as we're going to revert the board to what it was before
                val originalPos = enemyPiece.position

                val occPieceInNewPos = testChessBoard.boardMatrix[newPos.row][newPos.column].occupyingPiece

                //Apply the changes on test board
                val capture = simulateMove(testChessBoard, originalPos, newPos)

                //Create a temporary list where we'll store the pieces of the player in turn that are still going to check the enemy after
                //we've simulated the current movement of the current piece & Find out if the enemy player is still under check
                if(!findPiecesThatCheck(testChessBoard, playerInTurn.value!!.color, piecesAbleToSavePlayer.value)){
                    enemyPiece.listOfPositionsThatCanSaveKing.add(newPos)
                }

                //Revert Movement (also there might have been a captured piece that we must put back)
                if(capture){

                    simulateMove(testChessBoard, newPos, originalPos, occPieceInNewPos)

                }else{
                    simulateMove(testChessBoard, newPos, originalPos)

                }
            }


        }

        return piecesAbleToSavePlayer.value.isNotEmpty()
    }

    //Find who's the enemy, and loop through the pieces of the enemy to check whether they're checking our king or not
    //and add the checking pieces to the appropriate list, returns true if there are pieces checking the enemy and false otherwise
    private fun findPiecesThatCheck(chessBoard: ChessBoard, enemyColor: PieceColor, listOfPiecesThatCheck: MutableList<ChessPiece>): Boolean{

        if(enemyColor == PieceColor.White){

            val blackKing = chessBoard.getKing(PieceColor.Black)

            for(piece in chessBoard.whitePieces){

                if(piece.protectsPosition(chessBoard,blackKing.position)){
                    listOfPiecesThatCheck.add(piece)
                }
            }

        }else{

            val whiteKing = chessBoard.getKing(PieceColor.White)

            for(piece in chessBoard.whitePieces){

                if(piece.protectsPosition(chessBoard,whiteKing.position)){
                    listOfPiecesThatCheck.add(piece)
                }
            }
        }

        return listOfPiecesThatCheck.isNotEmpty()
    }


    // Simulate a move, returns true if it's a capture or not, revertedPiece is a piece that we want to put back if it was captured previously
    //especially when simulating multiple times for checkmate detection
    private fun simulateMove(testChessBoard: ChessBoard, oldPosition: PiecePosition,newPosition: PiecePosition, revertedPiece: ChessPiece? = null): Boolean{

        var capture = false

        val oldRow = oldPosition.row
        val oldColumn = oldPosition.column

        val newRow = newPosition.row
        val newColumn = newPosition.column

        val occPiece = testChessBoard.boardMatrix[newRow][newColumn].occupyingPiece
        if(occPiece != null){

            if(occPiece.color == PieceColor.White){
                testChessBoard.whitePieces.remove(occPiece)
            }else{
                testChessBoard.blackPieces.remove(occPiece)
            }

            capture = true
        }

        //Nullify the occupying piece after saving it temporarily
        val tempPiece = testChessBoard.boardMatrix[oldRow][oldColumn].occupyingPiece
        testChessBoard.boardMatrix[oldRow][oldColumn ].occupyingPiece = null

        //Move piece to new position on test board
        testChessBoard.boardMatrix[newRow][newColumn].occupyingPiece = tempPiece

        //There is a piece that must be placed back on the board
        if(revertedPiece != null){
            val row = revertedPiece.position.row
            val column = revertedPiece.position.column

            testChessBoard.boardMatrix[row][column].occupyingPiece = revertedPiece

            if(revertedPiece.color == PieceColor.White){
                testChessBoard.whitePieces.add(revertedPiece)
            }else{
                testChessBoard.blackPieces.add(revertedPiece)
            }
        }

        return capture
    }

    private fun replaceChessBoard(){

        //Also change the list of remaining pieces for each player to the newly cloned ones
        if(playerInTurn.value!!.color == PieceColor.White){

            playerInTurn.value!!.remainingPieces = testChessBoard.value!!.whitePieces
            getEnemyPlayer().remainingPieces = testChessBoard.value!!.blackPieces
        }else{

            playerInTurn.value!!.remainingPieces = testChessBoard.value!!.blackPieces
            getEnemyPlayer().remainingPieces = testChessBoard.value!!.whitePieces
        }

        chessBoard.value = testChessBoard.value
        testChessBoard.value = null
    }


    fun movePiece(newPosition : PiecePosition){

        // Step 1: Check whether move is legal
        if(!selectedChessPiece.value!!.isPieceMoveLegal(chessBoard.value!!,newPosition, chessBoard.value!!.enPassantEdiblePiece)){

            //Move is invalid, so we play the invalid sound effect
            playSoundEffect(invalidMoveSound)
            return
        }

        var tempPiecesAbleToSavePlayer :MutableList<ChessPiece> = mutableListOf()
        var tempEnemyPiecesCheckingPlayer: MutableList<ChessPiece> = mutableListOf()

        // Step 2: Check whether the selected piece is part of the list of pieces that can save the king (in case there's a check)
        if(playerInTurn.value!!.underCheck){

            //Piece can't be moved because it won't save the king from the check
            if(!piecesAbleToSavePlayer.value.any { it == selectedChessPiece.value}){
                playSoundEffect(invalidMoveSound)
                return
            }else{
                //Piece could potentially save king from check
                if(selectedChessPiece.value!!.listOfPositionsThatCanSaveKing.contains(newPosition)){

                    //This move can save the king from the check, so now we can clear all lists
                    //containing the pieces that are checking the playerInTurn's king and
                    //so on

                    //Temporarily save these lists in case we can't get past step 3
                    tempEnemyPiecesCheckingPlayer = enemyPiecesCheckingPlayer.value
                    tempPiecesAbleToSavePlayer = piecesAbleToSavePlayer.value

                    piecesAbleToSavePlayer.value = mutableListOf()
                    enemyPiecesCheckingPlayer.value = mutableListOf()



                }else{
                    //Piece could potentially save the king but the user didn't choose the right move
                    playSoundEffect(invalidMoveSound)
                    return
                }
            }
        }

        // Step 3: Check whether moving the selected piece will cause a new check for the king

        //First create a clone of the current chessboard
        testChessBoard.value = chessBoard.value!!.deepClone()

        //Secondly simulate the move
        val capture= simulateMove(testChessBoard.value!!,selectedChessPiece.value!!.position,newPosition)

        //Then look for enemy pieces that might check king of the playerInTurn
        if(findPiecesThatCheck(testChessBoard.value!!, getEnemyPlayer().color, enemyPiecesCheckingPlayer.value)){


            enemyPiecesCheckingPlayer.value = tempEnemyPiecesCheckingPlayer
            piecesAbleToSavePlayer.value = tempPiecesAbleToSavePlayer

            playSoundEffect(invalidMoveSound)
            return
        }


        //Clear all lists of positions that can save the kings of these pieces, as we're going to update them in the next step
        for(piece in testChessBoard.value!!.whitePieces){
            piece.listOfPositionsThatCanSaveKing.clear()
        }


        for(piece in testChessBoard.value!!.blackPieces){
            piece.listOfPositionsThatCanSaveKing.clear()
        }

        //By this point, the move has been validated and saved on the test chessboard, now we have to find out what are the side effects

        //Step 4: Check whether moving the selected piece will cause a check for the enemy king
        if(findPiecesThatCheck(testChessBoard.value!!,playerInTurn.value!!.color, enemyPiecesCheckingPlayer.value)){

            getEnemyPlayer().underCheck = true

            //Step 5: Check if the enemy player can escape from the check we're causing
            if(!canEscapeCheck(testChessBoard.value!!,getEnemyPlayer().color )){

                //Checkmate detected
                reasonForWinning.value = R.string.checkmate_reason_for_winning
                endGame(winningPlayer= playerInTurn.value!!)
            }

        }else if(detectStalemate(testChessBoard.value!!, getEnemyPlayer().color)){

            //Detect stalemate when the enemy player can't move any of their pieces anymore
            reasonForWinning.value = R.string.stalemate_reason_for_winning
            endGame(null)

        }


        //The testChessBoard will become the newly displayed chessboard after all checks are done
        replaceChessBoard()


        //Play sound according to capture var (slide if capture is false)
        if(capture){
            playSoundEffect(pieceCaptureSound)
        }else{
            playSoundEffect(pieceSlideSound)
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