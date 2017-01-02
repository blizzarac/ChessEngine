package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.CheckValidator;
import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.engine.logic.ClickState;
import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.gui.controls.main.Main;
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
    private final ChessBoard chessboard;
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
    private ClickState clickState = ClickState.NOTHING_CLICKED;

    public ChessEngineControl() {
        setSkin(new ChessEngineControlSkin(this));
        chessboard = Main.injector.getInstance(ChessBoard.class);
        chessboardPane = Main.injector.getInstance(ChessBoardPane.class);
        checkValidator = Main.injector.getInstance(CheckValidator.class);

        getChildren().clear();
        getChildren().addAll(chessboardPane);

        chessboardPane.buildBoard();

        // Spacebar to reset game ..
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                chessboardPane = new ChessBoardPane();
                getChildren().clear();
                getChildren().addAll(chessboardPane);

                chessboardPane.buildBoard();

                // Reset game variables
                stale = false;
                winner = false;
                stalecountwhite = 8;
                stalecountblack = 8;
                clickState = ClickState.NOTHING_CLICKED;
            }
        });

        setOnDragDetected(event -> {
            startFullDrag();

            // Get hash of what we clicked on ..
            int hash = event.getTarget().hashCode();

            if (clickState == ClickState.NOTHING_CLICKED && !stale && !winner) {
                selectedpiece = chessboardPane.selectPiece(hash);
                logger.log(Level.SEVERE, "Picking up ("+ selectedpiece.getType() + ":" + selectedpiece.getColor() + ") from: " + selectedpiece.getPiecePosition());
                junkselection = selectedpiece == null || !chessboardPane.isPieceSelected();

                if (!junkselection) {
                    validMoves = selectedpiece.findValidMoves(chessboardPane.chessBoard.pieces);
                    getScene().setCursor(new ImageCursor(PieceImageProvider.INSTANCE.getImageForPiece(selectedpiece)));
                    clickState = ClickState.PIECE_PICKED_UP;
                    // Highlights valid moves.
                    for (PiecePosition p: validMoves) {
                        chessboardPane.chessBoardFields.fields[p.x][p.y].setStroke(Color.RED);
                    }
                }
            }

            event.consume();
        });


        setOnMouseReleased(event -> {
            int hash = event.getPickResult().getIntersectedNode().hashCode();

            if (clickState == ClickState.PIECE_PICKED_UP) {
                final Piece[][] boardstate = chessboard.pieces;

                targetpiece = chessboardPane.selectTargetLocation(hash);
                logger.log(Level.SEVERE, "Dropping to: ("+ targetpiece.getType() + ":" + targetpiece.getColor() + ") " + targetpiece.getPiecePosition());
                if (selectedpiece.getType() != NOTYPE
                        && selectedpiece != null
                        && targetpiece != null
                        && !selectedpiece.equals(targetpiece)) {

                    if (validMoves.stream().anyMatch(pos -> pos.x == targetpiece.x() && pos.y == targetpiece.y())) {
                        final Piece[][] oldState = chessboardPane.chessBoard.backupPieces(boardstate);
                        logger.log(Level.SEVERE, "Old state:\n" + chessboardPane.chessBoard.printBoard());
                        tryMoveAndReverseOnCheck(boardstate, oldState);
                    } else {
                        stalemateCheck();
                    }
                }

                chessboardPane.chessBoardFields.resetColorsOnBoard();
                clickState = ClickState.NOTHING_CLICKED;

                // Check for checkmate ..
                winner = checkValidator.isCheckMate(chessboardPane.chessBoard.currentPlayer.mirror(), chessboard.pieces);

                getScene().setCursor(Cursor.DEFAULT);

                // highlight check..
                if (checkValidator.checkstate()) {
                    // TODO Check highlighting
                    //chessboardPane.chessBoardFields.setFieldHightlightColor(checkValidator.checkstate().x, checkValidator.checkstate().y, Color.RED);
                    if (!winner) {
                        logger.log(Level.FINE, "CHECK!");
                    }

                    clickState = ClickState.NOTHING_CLICKED;
                }
            }
        });
    }



    private Piece[][] tryMoveAndReverseOnCheck(Piece[][] boardstate, Piece[][] oldstate) {
        logger.log(Level.SEVERE, "tryMoveAndReverseOnCheck");
        boardstate = selectedpiece.move(boardstate, targetpiece.getPiecePosition());

        // If move results in no check, do move
        if (!checkValidator.isCheck(boardstate, chessboardPane.chessBoard.currentPlayer)) {
            logger.log(Level.SEVERE, "Not in check after move!");
            chessboardPane.chessBoard.replacePieces(boardstate);
            logger.log(Level.SEVERE, "New Board state:\n" + chessboardPane.chessBoard.printBoard());
            chessboardPane.buildBoard();
            chessboardPane.chessBoard.currentPlayer = chessboardPane.chessBoard.currentPlayer.mirror();
            stalecountwhite = 8;
            stalecountblack = 8;
        } else {
            logger.log(Level.SEVERE, "In check after move!");
            chessboardPane.chessBoard.replacePieces(oldstate);
            checkValidator.flipcheck();
            stalemateCheck();
        }

        clickState = ClickState.NULL;
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
