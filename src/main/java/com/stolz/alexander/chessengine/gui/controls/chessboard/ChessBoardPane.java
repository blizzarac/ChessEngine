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
    public final ChessBoardFields chessBoardFields;
    public final ChessBoard chessBoard;

    private ImageView[][] imageviews;


    private PieceColor currentPlayerColor = WHITE;
    private ClickState clickState = ClickState.NOTHING_CLICKED;

    private boolean isPieceSelected = false;
    private boolean winner = false;

    public ClickState getClickState() {
        return clickState;
    }

    public void setClickLogic(ClickState state) {
        this.clickState = state;
    }

    public PieceColor otherPlayerColor() {
        return currentPlayerColor.mirror();
    }

    public boolean isPieceSelected() {
        return isPieceSelected;
    }

    public ChessBoardPane() {
        chessBoardFields = new ChessBoardFields(Color.YELLOW, Color.BROWN);
        chessBoard = new ChessBoard();
        chessBoard.init();

        buildBoard();

        currentPlayerColor = WHITE;
    }

    public void buildBoard() {
        getChildren().clear();

        // Viewers for each getImage
        imageviews = new ImageView[8][8];

        // Initializes imageviewers and windows
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                imageviews[x][y] = new ImageView();
            }
        }

        // Places background squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getChildren().add(chessBoardFields.fields[i][j]);
            }
        }

        // Places chess piece images
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getChildren().addAll(imageviews[i][j]);
            }
        }

        // Puts images into imageviewers
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Image pieceImage = PieceImageProvider.INSTANCE.getImageForPiece(chessBoard.pieces[x][y]);
                imageviews[x][y].setImage(pieceImage);
                imageviews[x][y].setFitWidth(50);
                imageviews[x][y].setFitHeight(80);
                imageviews[x][y].setPreserveRatio(true);
                imageviews[x][y].setSmooth(true);
                imageviews[x][y].setCache(true);
                imageviews[x][y].setTranslateX(chessBoardFields.fields[x][y].getWidth() / 8);
            }
        }
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        double cell_width = width / 8;
        double cell_height = height / 8;

        chessBoardFields.resize(cell_width, cell_height);

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                imageviews[i][j].relocate(i * cell_width, j * cell_height);
                imageviews[i][j].setFitWidth(cell_width / 1.25);
                imageviews[i][j].setFitHeight(cell_height / 1.25);
                imageviews[i][j].setTranslateX(chessBoardFields.fields[i][j].getWidth() / 8);
            }
        }
    }

    public Piece selectTarget(int hash) {
        PiecePosition position = new PiecePosition(0,0);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (imageviews[x][y].hashCode() == hash || chessBoardFields.fields[x][y].hashCode() == hash) {
                    position.x = x;
                    position.y = y;
                }
            }
        }

        if (!winner) {
            if (chessBoard.pieces[position.x][position.y].getColor() == NONE
                    || chessBoard.pieces[position.x][position.y].getColor() == currentPlayerColor.mirror()) {
                return chessBoard.pieces[position.x][position.y];
            }
        }
        return chessBoard.pieces[position.x][position.y];
    }

    //select piece method
    public Piece selectPiece(int hash) {
        PiecePosition selectedPiece = new PiecePosition(0,0);
        // Determine what piece was selected and if it can be selected

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (imageviews[x][y].hashCode() == hash && imageviews[x][y] != null) {
                    selectedPiece.x = x;
                    selectedPiece.y = y;
                }
            }
        }

        if (currentPlayerColor == WHITE && !winner) {
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
        }

        // If current player is black
        else {
            if (chessBoard.pieces[selectedPiece.x][selectedPiece.y].getColor() == BLACK) {
                if (chessBoardFields.fields[selectedPiece.x][selectedPiece.y].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    // Resets color
                    chessBoardFields.resetColorsOnField(selectedPiece.x, selectedPiece.y);
                } else {
                    chessBoardFields.fields[selectedPiece.x][selectedPiece.y].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoard.pieces[selectedPiece.x][selectedPiece.y];
                }
            }
        }

        // return something ..
        return new Empty(selectedPiece.x, selectedPiece.y);
    }

    // Returns state of the chess fields ..
    public Piece[][] getState() {
        return chessBoard.pieces;
    }

    public void changeplayer() {
        currentPlayerColor = currentPlayerColor.mirror();
    }
}
