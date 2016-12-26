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
import javafx.scene.paint.Paint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

public class ChessBoardPane extends Pane {
    public final ChessBoardView chessBoardView;
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
        chessBoardView = new ChessBoardView();
        chessBoard = new ChessBoard();
        chessBoard.init();

        // Initializes new board
        chessBoardView.setBoard(chessBoardView.initializeNewBoard());

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
                getChildren().add(chessBoardView.getBoard()[i][j]);
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
                imageviews[x][y].setTranslateX(chessBoardView.getBoard()[x][y].getWidth() / 8);
            }
        }
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        double cell_width = width / 8;
        double cell_height = height / 8;

        chessBoardView.resize(cell_width, cell_height);

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                imageviews[i][j].relocate(i * cell_width, j * cell_height);
                imageviews[i][j].setFitWidth(cell_width / 1.25);
                imageviews[i][j].setFitHeight(cell_height / 1.25);
                imageviews[i][j].setTranslateX(chessBoardView.getBoard()[i][j].getWidth() / 8);
            }
        }
    }

    public Piece selectTarget(int hash) {

        int i = 0;
        int j = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (imageviews[x][y].hashCode() == hash || chessBoardView.getBoard()[x][y].hashCode() == hash) {
                    i = x;
                    j = y;
                }
            }
        }

        if (!winner) {
            if (chessBoard.pieces[i][j].getColor() == NONE
                    || chessBoard.pieces[i][j].getColor() == currentPlayerColor.mirror()) {
                return chessBoard.pieces[i][j];
            }
        }
        return chessBoard.pieces[i][j];
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
                if (chessBoardView.getBoard()[selectedPiece.x][selectedPiece.y].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    chessBoardView.resetColorsOnField(selectedPiece.x, selectedPiece.y);
                }
                // Otherwise select it and work out moves
                else {
                    chessBoardView.getBoard()[selectedPiece.x][selectedPiece.y].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoard.pieces[selectedPiece.x][selectedPiece.y];
                }
            }
        }

        // If current player is black
        else {
            if (chessBoard.pieces[selectedPiece.x][selectedPiece.y].getColor() == BLACK) {
                if (chessBoardView.getBoard()[selectedPiece.x][selectedPiece.y].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    // Resets color
                    chessBoardView.resetColorsOnField(selectedPiece.x, selectedPiece.y);
                } else {
                    chessBoardView.getBoard()[selectedPiece.x][selectedPiece.y].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoard.pieces[selectedPiece.x][selectedPiece.y];
                }
            }
        }

        // return something ..
        return new Empty(selectedPiece.x, selectedPiece.y);
    }

    // Returns state of the chess board ..
    public Piece[][] getState() {
        return chessBoard.pieces;
    }

    public void changeplayer() {
        currentPlayerColor = currentPlayerColor.mirror();
    }
}
