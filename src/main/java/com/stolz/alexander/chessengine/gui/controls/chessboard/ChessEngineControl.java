package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.CheckValidator;
import com.stolz.alexander.chessengine.engine.logic.ClickState;
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

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.*;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.NOTYPE;


public class ChessEngineControl extends Control {

    private static Logger logger = Logger.getLogger(ChessEngineControl.class.getName());
    private List<PiecePosition> validMoves;

    private ChessBoardPane chessboardPane;

    private Piece selectedpiece;
    private Piece targetpiece;
    private CheckValidator checkValidator;
    private boolean junkselection;
    private boolean winner = false;
    private boolean stale = false;
    private int stalecountblack = 8;
    private int stalecountwhite = 8;

    public ChessEngineControl() {
        setSkin(new ChessEngineControlSkin(this));
        chessboardPane = new ChessBoardPane();
        checkValidator = new CheckValidator();
        getChildren().addAll(chessboardPane);

        chessboardPane.buildBoard();

        // Spacebar to reset game ..
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                chessboardPane = new ChessBoardPane();
                getChildren().addAll(chessboardPane);

                chessboardPane.buildBoard();

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

            // Get hash of what we clicked on ..
            int hash = event.getTarget().hashCode();

            if (chessboardPane.getClickState() == ClickState.NOTHING_CLICKED && !stale && !winner) {
                selectedpiece = chessboardPane.selectPiece(hash);

                junkselection = selectedpiece.getColor() == NONE || !chessboardPane.isPieceSelected();

                if (!(selectedpiece.getColor() == NONE) && !junkselection) {
                    validMoves = selectedpiece.findValidMoves(chessboardPane.chessBoard.pieces);
                    getScene().setCursor(new ImageCursor(PieceImageProvider.INSTANCE.getImageForPiece(selectedpiece)));
                    chessboardPane.setClickLogic(ClickState.PIECE_PICKED_UP);
                    // Highlights valid moves.
                    for (PiecePosition p: validMoves) {
                        chessboardPane.chessBoardFields.getFields()[p.x][p.y].setStroke(Color.RED);
                    }
                }
            }

            event.consume();
        });


        setOnMouseReleased(event -> {
            logger.log(Level.SEVERE, "setOnMouseReleased");
            int hash = event.getPickResult().getIntersectedNode().hashCode();

            if (chessboardPane.getClickState() == ClickState.PIECE_PICKED_UP) {
                final Piece[][] boardstate = chessboardPane.getState();

                targetpiece = chessboardPane.selectTarget(hash);
                if (selectedpiece.getType() != NOTYPE
                        && selectedpiece != null
                        && targetpiece != null
                        && !selectedpiece.equals(targetpiece)) {

                    if (validMoves.stream().anyMatch(pos -> pos.x == targetpiece.x() && pos.y == targetpiece.y())) {
                        final Piece[][] oldState = chessboardPane.chessBoard.backupPieces(boardstate);
                        tryMoveAndReverseOnCheck(boardstate, oldState);
                    } else {
                        stalemateCheck();
                    }
                }

                chessboardPane.chessBoardFields.resetColorsOnBoard();
                chessboardPane.setClickLogic(ClickState.NOTHING_CLICKED);

                // Check for checkmate ..
                winner = checkValidator.check4checkmate(chessboardPane.otherPlayerColor(), chessboardPane.getState());

                getScene().setCursor(Cursor.DEFAULT);

                // highlight check..
                if (checkValidator.checkstate() != null) {
                    chessboardPane.chessBoardFields.setFieldHightlightColor(checkValidator.checkstate().x, checkValidator.checkstate().y, Color.RED);
                    if (!winner) {
                        logger.log(Level.FINE, "CHECK!");
                    }

                    chessboardPane.setClickLogic(ClickState.NOTHING_CLICKED);
                }
            }
        });
    }



    private Piece[][] tryMoveAndReverseOnCheck(Piece[][] boardstate, Piece[][] oldstate) {
        boardstate = selectedpiece.move(boardstate, targetpiece.getPiecePosition());

        // If move results in no check, do move
        if (checkValidator.check4check(chessboardPane.otherPlayerColor(), boardstate) == null) {
            chessboardPane.chessBoard.replacePieces(boardstate);
            chessboardPane.buildBoard();
            chessboardPane.changeplayer();
            chessboardPane.setClickLogic(ClickState.NULL);
            stalecountwhite = 8;
            stalecountblack = 8;
        } else {
            chessboardPane.setClickLogic(ClickState.NULL);
            chessboardPane.chessBoard.replacePieces(oldstate);
            checkValidator.flipcheck();
            stalemateCheck();
        }
        return boardstate;
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        chessboardPane.resize(width, height);
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
