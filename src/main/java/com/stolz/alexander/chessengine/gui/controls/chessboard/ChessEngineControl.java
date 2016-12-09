package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.CheckValidator;
import com.stolz.alexander.chessengine.engine.logic.ClickState;
import com.stolz.alexander.chessengine.engine.logic.GameLogic;
import com.stolz.alexander.chessengine.gui.pieces.PieceView;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;

import javafx.scene.control.Control;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;


public class ChessEngineControl extends Control {

    private static Logger logger = Logger.getLogger(ChessEngineControl.class.getName());

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
                chessboardPane.setClickLogic(ClickState.NOTHING_CLICKED);
            }
        });

        setOnMouseClicked(event -> {
            // Get has of what we clicked on ..
            int hash = event.getTarget().hashCode();

            // Checks if black timer has hit zero
            if (11 == 0) {
                winner = true;
                logger.log(Level.FINE, "Black time out: White Wins!");
            }

            // Checks if white timer has hit zero
            if (1 == 0) {
                winner = true;
                logger.log(Level.FINE, "White time out: Black Wins!");
            }

            // First click
            if (chessboardPane.getClickState() == ClickState.NOTHING_CLICKED && !stale && !winner) {

                selectedpiece = chessboardPane.selectPiece(hash);
                junkselection = selectedpiece.getColor() == NONE || !chessboardPane.isPieceSelected();

                if (!(selectedpiece.getColor() == NONE) && !junkselection) {
                    getScene().setCursor(new ImageCursor(selectedpiece.getImage()));
                    chessboardPane.setClickLogic(ClickState.PIECE_PICKED_UP);
                    // Highlights valid moves.
                    selectedpiece.drawValidMoves(chessboardPane.chessBoardPiecesView.pieceViews, chessboardPane.chessBoard.getBoard());
                    // Check 4 check ..
                    if (!gamelogic.checkstatus()) {
                        checkValidator.check4check(chessboardPane.otherPlayerColor(), chessboardPane.getState());
                    }
                }

                // If completed move, return to first click ..
                if (chessboardPane.getClickState() == ClickState.NULL) {
                    chessboardPane.setClickLogic(ClickState.NOTHING_CLICKED);
                }
            } else if (chessboardPane.getClickState() == ClickState.PIECE_PICKED_UP) {
                PieceView[][] boardstate = chessboardPane.getState();

                targetpiece = chessboardPane.selectTarget(hash);
                if (selectedpiece.getType() != NOTYPE
                        && selectedpiece != null
                        && targetpiece != null
                        && !selectedpiece.equals(targetpiece)) {

                    if (chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.CORNFLOWERBLUE)
                            || chessboardPane.getStroke(targetpiece.icoord(), targetpiece.jcoord(), Color.AQUAMARINE)) {
                        PieceView[][] oldstate = backupBoardState(boardstate);
                        tryMoveAndReverseOnCheck(boardstate, oldstate);
                    } else {
                        stalemateCheck();
                    }
                }

                chessboardPane.chessBoard.resetColorsOnBoard();
                chessboardPane.setClickLogic(ClickState.NOTHING_CLICKED);

                // Check for checkmate ..
                if (gamelogic.check4checkmate(chessboardPane.otherPlayerColor(), chessboardPane.getState())) {
                    winner = true;
                }

                getScene().setCursor(Cursor.DEFAULT);

                // highlight check..
                if (gamelogic.checkstatus()) {
                    chessboardPane.chessBoard.setFieldHightlightColor(gamelogic.checki(), gamelogic.checkj(), Color.RED);
                    if (!winner) {
                        logger.log(Level.FINE, "CHECK!");
                    }
                    chessboardPane.setClickLogic(ClickState.NOTHING_CLICKED);
                }
            }


        });
    }

    private PieceView[][] backupBoardState(PieceView[][] boardstate) {
        PieceView[][] backup = new PieceView[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(boardstate[x], 0, backup[x], 0, 8);
        }
        return backup;
    }

    private PieceView[][] tryMoveAndReverseOnCheck(PieceView[][] boardstate, PieceView[][] oldstate) {
        // Do move
        boardstate = selectedpiece.move(selectedpiece, targetpiece, boardstate);
        // If move results in no check, do move
        if (!checkValidator.check4check(chessboardPane.otherPlayerColor(), boardstate)) {
            doMoveOnBoard(boardstate);
        } else {
            reverseMove(oldstate);
            stalemateCheck();
        }
        return boardstate;
    }

    public void blacktimeout() {
        winner = true;
        logger.log(Level.FINE, "\nBlack timeout: White wins!");
    }

    public void whitetimeout() {
        winner = true;
        logger.log(Level.FINE, "\nWhite timeout: Black wins!");
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

    private void doMoveOnBoard(PieceView[][] boardstate) {
        chessboardPane.chessBoardPiecesView.replacePieceViews(boardstate);
        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
        chessboardPane.changeplayer();
        chessboardPane.setClickLogic(ClickState.NULL);
        stalecountwhite = 8;
        stalecountblack = 8;
    }

    private void reverseMove(PieceView[][] oldstate) {
        chessboardPane.setClickLogic(ClickState.NULL);
        chessboardPane.chessBoardPiecesView.replacePieceViews(oldstate);
        gamelogic.flipcheck();
    }

    private void stalemateCheck() {
        // Stalemate check
        if (selectedpiece.getColor() == WHITE) {
            stalecountwhite--;
        }
        if (selectedpiece.getColor() == BLACK) {
            stalecountblack--;
        }
        if (stalecountwhite == 0 || stalecountblack == 0) {
            logger.log(Level.FINE, "STALEMATE!");
            stale = true;
        }
    }
}