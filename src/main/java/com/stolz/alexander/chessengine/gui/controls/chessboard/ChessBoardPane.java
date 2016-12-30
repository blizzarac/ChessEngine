package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.engine.logic.ClickState;
import com.stolz.alexander.chessengine.engine.pieces.Empty;
import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.gui.pieces.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

public class ChessBoardPane extends Pane {
    public final ChessBoard chessBoard;
    public final ChessBoardFields chessBoardFields;
    public final ChessBoardPieces chessBoardPieces;


    private boolean isPieceSelected = false;

    public boolean isPieceSelected() {
        return isPieceSelected;
    }

    public ChessBoardPane() {
        chessBoardFields = new ChessBoardFields(Color.YELLOW, Color.BROWN);
        chessBoard = new ChessBoard();
        chessBoard.init();
        chessBoardPieces = new ChessBoardPieces(chessBoard, chessBoardFields);
        chessBoardPieces.init();

        buildBoard();
    }

    public void buildBoard() {
        getChildren().clear();
        chessBoardPieces.init();

        // Places background squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getChildren().add(chessBoardFields.fields[i][j]);
            }
        }

        // Places chess piece images
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getChildren().addAll(chessBoardPieces.getImageviews()[i][j]);
            }
        }
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        double cell_width = width / 8;
        double cell_height = height / 8;

        chessBoardFields.resize(cell_width, cell_height);
        chessBoardPieces.resize(cell_width, cell_height);
    }

    public Piece selectTargetLocation(int hash) {
        PiecePosition position = new PiecePosition(0,0);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (chessBoardPieces.getImageviews()[x][y].hashCode() == hash
                        || chessBoardFields.fields[x][y].hashCode() == hash) {
                    position.x = x;
                    position.y = y;
                }
            }
        }

        if (!chessBoard.winner) {
            if (chessBoard.pieces[position.x][position.y].getColor() == NONE
                    || chessBoard.pieces[position.x][position.y].getColor() == chessBoard.currentPlayer.mirror()) {
                return chessBoard.pieces[position.x][position.y];
            }
        }
        return chessBoard.pieces[position.x][position.y];
    }

    /**
     * Select a specific piece from the board
     * @param hash
     * @return
     */
    public Piece selectPiece(int hash) {
        PiecePosition selectedPiece = null;

        // Determine what piece was selected and if it can be selected
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (chessBoardPieces.getImageviews()[x][y].hashCode() == hash) {
                    if (chessBoardPieces.getImageviews()[x][y] != null) {
                        selectedPiece = new PiecePosition(x, y);
                    } else {
                        return null;
                    }

                }
            }
        }

        if (selectedPiece != null && chessBoard.currentPlayer == WHITE && !chessBoard.winner) {
            if (chessBoard.pieces[selectedPiece.x][selectedPiece.y].getColor() == WHITE) {
                // If player has already selected the piece, deselect it
                if (chessBoardFields.fields[selectedPiece.x][selectedPiece.y].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    chessBoardFields.resetColorsOnField(selectedPiece.x, selectedPiece.y);
                }
                // Otherwise select it and work out moves
                else {
                    chessBoardFields.fields[selectedPiece.x][selectedPiece.y].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoard.pieces[selectedPiece.x][selectedPiece.y];
                }
            }
        } else if (selectedPiece != null && chessBoard.pieces[selectedPiece.x][selectedPiece.y].getColor() == BLACK) {
                if (chessBoardFields.fields[selectedPiece.x][selectedPiece.y].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    // Resets color
                    chessBoardFields.resetColorsOnField(selectedPiece.x, selectedPiece.y);
                } else {
                    chessBoardFields.fields[selectedPiece.x][selectedPiece.y].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoard.pieces[selectedPiece.x][selectedPiece.y];
                }
            }


        return null;
    }

    public Piece[][] getLogicalPieces() {
        return chessBoard.pieces;
    }
}
