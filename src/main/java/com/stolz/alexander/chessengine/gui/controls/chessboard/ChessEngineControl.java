package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.CheckValidator;
import com.stolz.alexander.chessengine.engine.logic.GameLogic;
import com.stolz.alexander.chessengine.gui.pieces.PieceView;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;

import javafx.scene.control.Control;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;


public class ChessEngineControl extends Control {


    private ChessBoardPane chessboardPane;

    private Translate pos;
    private PieceView selectedpiece;
    private PieceView targetpiece;
    private GameLogic gamelogic;
    private CheckValidator checkValidator;
    private boolean junkselection;
    private boolean winner = false;
    private boolean stale = false;
    private int stalecountblack = 8;
    private int stalecountwhite = 8;

    public ChessEngineControl() {
        pos = new Translate();
        setSkin(new ChessEngineControlSkin(this));
        chessboardPane = new ChessBoardPane();
        gamelogic = new GameLogic();
        checkValidator = gamelogic.getCheckValidator();
        getChildren().addAll(chessboardPane);


        // Places background squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboardPane.placeboard(i, j);
            }
        }

        // Places chess piece images
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboardPane.placeimages(i, j);
            }
        }

        // Spacebar to reset game ..
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                System.out.println("Game Reset!");

                chessboardPane = new ChessBoardPane();
                getChildren().addAll(chessboardPane);

                // Places background squares
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        chessboardPane.placeboard(i, j);
                    }
                }

                // Places chess piece images
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        chessboardPane.placeimages(i, j);
                    }
                }

                // Reset game variables
                stale = false;
                winner = false;
                stalecountwhite = 8;
                stalecountblack = 8;
                chessboardPane.changeclickfalse();
            }
        });

        setOnMouseClicked(event -> {

            // Get has of what we clicked on ..
            int hash = event.getTarget().hashCode();

            // Checks if black timer has hit zero
            if (11 == 0) {
                winner = true;
                System.out.println("Black time out: White Wins!");
            }

            // Checks if white timer has hit zero
            if (1 == 0) {
                winner = true;
                System.out.println("White time out: Black Wins!");
            }

            // Print current player..
            if (chessboardPane.clicklogic() == "false" && !winner && !stale) {
                if (chessboardPane.currentplayer() == WHITE) {
                    System.out.print("Current player: White");
                } else {
                    System.out.print("Current player: Black");
                }
            }

            System.out.println("\n");

            // Second click
            if (chessboardPane.clicklogic() == "true") {
                PieceView[][] boardstate = chessboardPane.getState();

                //System.out.println("mousesec: " + xcoordmouse + "," + ycoordmouse);
                targetpiece = chessboardPane.selectTarget(hash);
                boardstate = pawnSelection(boardstate);
                boardstate = bishopSelection(boardstate);
                boardstate = queenSelection(boardstate);
                boardstate = rookSelection(boardstate);
                boardstate = kingSelection(boardstate);
                knightSelection(boardstate);


                chessboardPane.clearhighlights();
                chessboardPane.changeclicknull();

                // Check for checkmate ..
                if (gamelogic.check4checkmate(chessboardPane.otherplayer(), chessboardPane.getState()) == "true") {
                    winner = true;
                }

                getScene().setCursor(Cursor.DEFAULT);

                // highlight check..
                if (gamelogic.checkstatus()) {
                    chessboardPane.getChessBoard().checkhighlight(gamelogic.checki(), gamelogic.checkj());
                    if (!winner) {
                        System.out.println("CHECK!");
                    }
                    chessboardPane.changeclicknull();
                }
            }

            // First click
            if (chessboardPane.clicklogic() == "false" && !stale && !winner) {

                selectedpiece = chessboardPane.selectPiece(hash);

                junkselection = selectedpiece.getColor() == NONE || !chessboardPane.pieceselect();

                if (!(selectedpiece.getColor() == NONE) && !junkselection) {
                    getScene().setCursor(new ImageCursor(selectedpiece.getImage()));
                    chessboardPane.changeclicktrue();
                    // Highlights valid moves.
                    selectedpiece.drawValidMoves(chessboardPane.getPieceViews(), chessboardPane.getBoard());
                    // Check 4 check ..
                    if (!gamelogic.checkstatus()) {
                        checkValidator.check4check(chessboardPane.otherplayer(), chessboardPane.getState());
                    }
                }

                if (chessboardPane.clicklogic() == "true") {
                    System.out.print("Whitepieces: " + countWhitePieces(chessboardPane.getState()) + " Blackpieces: " + countBlackPieces(chessboardPane.getState()));
                    System.out.println("\n====================");
                }

                // If completed move, return to first click ..
                if (chessboardPane.clicklogic() == "null") {
                    chessboardPane.changeclickfalse();
                }
            }
        });
    }

    private void knightSelection(PieceView[][] boardstate) {
        // If knight selected ..
        if (selectedpiece.getType() == KNIGHT && selectedpiece != null && targetpiece != null && !selectedpiece.equals(targetpiece)) {
            if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE) || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                // If check is false
                if (!gamelogic.checkstatus()) {
                    PieceView[][] oldstate = new PieceView[8][8];
                    // Transfer pieces to backup variable
                    for (int x = 0; x < 8; x++) {
                        System.arraycopy(boardstate[x], 0, oldstate[x], 0, 8);
                    }
                    // Do move
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // If move results in no check, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                    }
                    // If check, reverse move
                    else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }

                // If in check ..
                if (gamelogic.checkstatus()) {
                    // Do move
                    PieceView[][] oldstate = new PieceView[8][8];
                    for (int x = 0; x < 8; x++) {
                        System.arraycopy(boardstate[x], 0, oldstate[x], 0, 8);
                    }
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // Check if still in check, if not, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                        // If still in check, undo move
                    } else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }
            } else {

                // Stalemate check
                if (selectedpiece.getColor() == WHITE) {
                    stalecountwhite--;
                }
                if (selectedpiece.getColor() == BLACK) {
                    stalecountblack--;
                }
                if (stalecountwhite == 0 || stalecountblack == 0) {
                    System.out.println("STALEMATE!");
                    stale = true;
                }
            }
        }

        // If screw up ..
        else {
            chessboardPane.changeclicknull();
            chessboardPane.clearhighlights();
        }
    }

    private PieceView[][] kingSelection(PieceView[][] boardstate) {
        // If king selected ..
        if (selectedpiece.getType() == KING && selectedpiece != null && targetpiece != null && !selectedpiece.equals(targetpiece)) {
            if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE) || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                // If check is false
                if (!gamelogic.checkstatus()) {
                    PieceView[][] oldstate = new PieceView[8][8];
                    // Transfer pieces to backup variable
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    // Do move
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // If move results in no check, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                    }
                    // If check, reverse move
                    else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }

                // If in check ..
                if (gamelogic.checkstatus()) {
                    // Do move
                    PieceView[][] oldstate = new PieceView[8][8];
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // Check if still in check, if not, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                        // If still in check, undo move
                    } else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }
            } else {

                // Stalemate check
                if (selectedpiece.getColor() == WHITE) {
                    stalecountwhite--;
                }
                if (selectedpiece.getColor() == BLACK) {
                    stalecountblack--;
                }
                if (stalecountwhite == 0 || stalecountblack == 0) {
                    System.out.println("STALEMATE!");
                    stale = true;
                }
            }
        }
        return boardstate;
    }

    private PieceView[][] rookSelection(PieceView[][] boardstate) {
        // If rook selected ..
        if (selectedpiece.getType() == ROOK && selectedpiece != null && targetpiece != null && !selectedpiece.equals(targetpiece)) {
            if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE) || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                // If check is false
                if (!gamelogic.checkstatus()) {
                    PieceView[][] oldstate = new PieceView[8][8];
                    // Transfer pieces to backup variable
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    // Do move
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // If move results in no check, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                    }
                    // If check, reverse move
                    else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }

                // If in check ..
                if (gamelogic.checkstatus()) {
                    // Do move
                    PieceView[][] oldstate = new PieceView[8][8];
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // Check if still in check, if not, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                        // If still in check, undo move
                    } else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }
            } else {

                // Stalemate check
                if (selectedpiece.getColor() == WHITE) {
                    stalecountwhite--;
                }
                if (selectedpiece.getColor() == BLACK) {
                    stalecountblack--;
                }
                if (stalecountwhite == 0 || stalecountblack == 0) {
                    System.out.println("STALEMATE!");
                    stale = true;
                }
            }
        }
        return boardstate;
    }

    private PieceView[][] queenSelection(PieceView[][] boardstate) {
        // If queen selected ..
        if (selectedpiece.getType() == QUEEN && selectedpiece != null && targetpiece != null && !selectedpiece.equals(targetpiece)) {
            if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE) || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                // If check is false
                if (!gamelogic.checkstatus()) {
                    PieceView[][] oldstate = new PieceView[8][8];
                    // Transfer pieces to backup variable
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    // Do move
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // If move results in no check, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                    }
                    // If check, reverse move
                    else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }

                // If in check ..
                if (gamelogic.checkstatus()) {
                    // Do move
                    PieceView[][] oldstate = new PieceView[8][8];
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // Check if still in check, if not, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                        // If still in check, undo move
                    } else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                    }
                    if (stalecountwhite == 0 || stalecountblack == 0) {
                        System.out.println("STALEMATE!");
                        stale = true;
                    }
                }
            } else {

                // Stalemate check
                if (selectedpiece.getColor() == WHITE) {
                    stalecountwhite--;
                }
                if (selectedpiece.getColor() == BLACK) {
                    stalecountblack--;
                }
                if (stalecountwhite == 0 || stalecountblack == 0) {
                    System.out.println("STALEMATE!");
                    stale = true;
                }
            }
        }
        return boardstate;
    }

    private PieceView[][] bishopSelection(PieceView[][] boardstate) {
        // If bishop selected ..
        if (selectedpiece.getType() == BISHOP && selectedpiece != null && targetpiece != null && !selectedpiece.equals(targetpiece)) {
            if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE) || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                // If check is false
                if (!gamelogic.checkstatus()) {
                    PieceView[][] oldstate = new PieceView[8][8];
                    // Transfer pieces to backup variable
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    // Do move
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // If move results in no check, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                    }
                    // If check, reverse move
                    else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }

                // If in check ..
                if (gamelogic.checkstatus()) {
                    // Do move
                    PieceView[][] oldstate = new PieceView[8][8];
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            oldstate[x][y] = boardstate[x][y];
                        }
                    }
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // Check if still in check, if not, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                        // If still in check, undo move
                    } else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }
            } else {

                // Stalemate check
                if (selectedpiece.getColor() == WHITE) {
                    stalecountwhite--;
                }
                if (selectedpiece.getColor() == BLACK) {
                    stalecountblack--;
                }
                if (stalecountwhite == 0 || stalecountblack == 0) {
                    System.out.println("STALEMATE!");
                    stale = true;
                }
            }
        }
        return boardstate;
    }

    private PieceView[][] pawnSelection(PieceView[][] boardstate) {
        // If pawn selected ..
        if (selectedpiece.getType() == PAWN && selectedpiece != null && targetpiece != null && !selectedpiece.equals(targetpiece)) {
            // Only executes if legal move ..
            if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE) || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                // If check is false
                if (!gamelogic.checkstatus()) {
                    PieceView[][] oldstate = new PieceView[8][8];
                    // Transfer pieces to backup variable
                    for (int x = 0; x < 8; x++) {
                        System.arraycopy(boardstate[x], 0, oldstate[x], 0, 8);
                    }

                    // Do move
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // If move results in no check, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        // Successful move
                        stalecountwhite = 8;
                        stalecountblack = 8;
                    }
                    // If check, reverse move
                    else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }

                    }
                }

                // If in check ..
                if (gamelogic.checkstatus()) {
                    // Do move
                    PieceView[][] oldstate = new PieceView[8][8];
                    for (int x = 0; x < 8; x++) {
                        System.arraycopy(boardstate[x], 0, oldstate[x], 0, 8);
                    }
                    boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
                    // Check if still in check, if not, do move
                    if (!checkValidator.check4check(chessboardPane.otherplayer(), boardstate)) {
                        chessboardPane.setBoard(boardstate);
                        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
                        chessboardPane.changeplayer();
                        chessboardPane.changeclicknull();
                        stalecountwhite = 8;
                        stalecountblack = 8;
                        // If still in check, undo move
                    } else {
                        chessboardPane.changeclicknull();
                        chessboardPane.setBoard(oldstate);
                        gamelogic.flipcheck();

                        // Stalemate check
                        if (selectedpiece.getColor() == WHITE) {
                            stalecountwhite--;
                        }
                        if (selectedpiece.getColor() == BLACK) {
                            stalecountblack--;
                        }
                        if (stalecountwhite == 0 || stalecountblack == 0) {
                            System.out.println("STALEMATE!");
                            stale = true;
                        }
                    }
                }

            } else {

                // Stalemate check
                if (selectedpiece.getColor() == WHITE) {
                    stalecountwhite--;
                }
                if (selectedpiece.getColor() == BLACK) {
                    stalecountblack--;
                }
                if (stalecountwhite == 0 || stalecountblack == 0) {
                    System.out.println("STALEMATE!");
                    stale = true;
                }
            }
        }
        return boardstate;
    }

    public int countWhitePieces(PieceView[][] boardstate) {
        int whitepieces = 0;

        // Count white pieces
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (boardstate[x][y].getColor() == WHITE) {
                    whitepieces++;
                }
            }
        }
        // Return int
        return whitepieces;
    }

    public int countBlackPieces(PieceView[][] boardstate) {
        int blackpieces = 0;

        // Count white pieces
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (boardstate[x][y].getColor() == BLACK) {
                    blackpieces++;
                }
            }
        }
        // Return int
        return blackpieces;
    }

    public void blacktimeout() {
        winner = true;
        System.out.println("\nBlack timeout: White wins!");
    }

    public void whitetimeout() {
        winner = true;
        System.out.println("\nWhite timeout: Black wins!");
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        chessboardPane.resize(width, height);
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        pos.setX(x);
        pos.setY(x);
    }
}
