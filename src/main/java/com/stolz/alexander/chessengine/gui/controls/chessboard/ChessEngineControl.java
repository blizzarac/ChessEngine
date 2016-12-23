package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.CheckValidator;
import com.stolz.alexander.chessengine.engine.logic.ClickState;
import com.stolz.alexander.chessengine.engine.logic.GameLogic;
import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.gui.pieces.PieceImageProvider;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;

import javafx.scene.control.Control;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;


public class ChessEngineControl extends Control {

    private static Logger logger = Logger.getLogger(ChessEngineControl.class.getName());
    private List<PiecePosition> validMoves;

    private ChessBoardPane chessboardPane;

    private Piece selectedpiece;
    private Piece targetpiece;
    private GameLogic gamelogic;
    private CheckValidator checkValidator;
    private boolean junkselection;
    private boolean winner = false;
    private boolean stale = false;
    private int stalecountblack = 8;
    private int stalecountwhite = 8;

    public ChessEngineControl() {
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

        setOnDragDetected(event -> {
            logger.log(Level.SEVERE, "setOnMouseDragged");
            startFullDrag();
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

            // Get has of what we clicked on ..
            int hash = event.getTarget().hashCode();

            if (chessboardPane.getClickState() == ClickState.NOTHING_CLICKED && !stale && !winner) {
                selectedpiece = chessboardPane.selectPiece(hash);

                junkselection = selectedpiece.getColor() == NONE || !chessboardPane.isPieceSelected();

                if (!(selectedpiece.getColor() == NONE) && !junkselection) {
                    validMoves = selectedpiece.findValidMoves(chessboardPane.chessBoardPiecesView.pieces);
                    getScene().setCursor(new ImageCursor(PieceImageProvider.INSTANCE.getImageForPiece(selectedpiece)));
                    chessboardPane.setClickLogic(ClickState.PIECE_PICKED_UP);
                    // Highlights valid moves.
                    for (PiecePosition p: validMoves) {
                        chessboardPane.chessBoard.getBoard()[p.x][p.y].setStroke(Color.RED);
                    }
                    // Check 4 check ..
                    if (!gamelogic.checkstatus()) {
                        checkValidator.check4check(chessboardPane.otherPlayerColor(), chessboardPane.getState());
                    }
                }
            }

            event.consume();
        });


        setOnMouseReleased(event -> {
            logger.log(Level.SEVERE, "setOnMouseReleased");
            int hash = event.getPickResult().getIntersectedNode().hashCode();

            if (chessboardPane.getClickState() == ClickState.PIECE_PICKED_UP) {
                Piece[][] boardstate = chessboardPane.getState();

                targetpiece = chessboardPane.selectTarget(hash);
                if (selectedpiece.getType() != NOTYPE
                        && selectedpiece != null
                        && targetpiece != null
                        && !selectedpiece.equals(targetpiece)) {

                    if (validMoves.stream().anyMatch(pos -> pos.x == targetpiece.icoord() && pos.y == targetpiece.jcoord())) {
                        Piece[][] oldstate = backupBoardState(boardstate);
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

    private Piece[][] backupBoardState(Piece[][] boardstate) {
        Piece[][] backup = new Piece[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(boardstate[x], 0, backup[x], 0, 8);
        }
        return backup;
    }

    private Piece[][] tryMoveAndReverseOnCheck(Piece[][] boardstate, Piece[][] oldstate) {
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

    private void doMoveOnBoard(Piece[][] boardstate) {
        chessboardPane.chessBoardPiecesView.replacePieceViews(boardstate);
        chessboardPane.drawmove(selectedpiece.icoord(), selectedpiece.jcoord(), targetpiece.icoord(), targetpiece.jcoord());
        chessboardPane.changeplayer();
        chessboardPane.setClickLogic(ClickState.NULL);
        stalecountwhite = 8;
        stalecountblack = 8;
    }

    private void reverseMove(Piece[][] oldstate) {
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
