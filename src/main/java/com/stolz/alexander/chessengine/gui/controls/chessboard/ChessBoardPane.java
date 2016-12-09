package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.ClickState;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
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
    public final ChessBoard chessBoard;
    public final ChessBoardPiecesView chessBoardPiecesView;

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
        chessBoard = new ChessBoard();
        chessBoardPiecesView = new ChessBoardPiecesView();

        // Initializes new board
        chessBoard.setBoard(chessBoard.initializeNewBoard());


        // Viewers for each getImage
        imageviews = new ImageView[8][8];

        // Initializes imageviewers and windows
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                imageviews[x][y] = new ImageView();
            }
        }

        // Puts images into imageviewers
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                imageviews[x][y].setImage(chessBoardPiecesView.pieceViews[x][y].getImage());
                imageviews[x][y].setFitWidth(50);
                imageviews[x][y].setFitHeight(80);
                imageviews[x][y].setPreserveRatio(true);
                imageviews[x][y].setSmooth(true);
                imageviews[x][y].setCache(true);
                imageviews[x][y].setTranslateX(chessBoard.getBoard()[x][y].getWidth() / 8);
            }
        }

        currentPlayerColor = WHITE;
    }

    public void placeboard(final int i, final int j) {
        getChildren().add(chessBoard.getBoard()[i][j]);
    }

    public void placeimages(final int i, final int j) {
        getChildren().addAll(imageviews[i][j]);
    }

    // Returns stroke of board piece
    public boolean getStroke(final int i, final int j, Paint color) {
        return chessBoard.getBoard()[i][j].getStroke() == color;
    }

    //resize method
    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        double cell_width = width / 8;
        double cell_height = height / 8;

        chessBoard.resize(cell_width, cell_height);

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                imageviews[i][j].relocate(i * cell_width, j * cell_height);
                imageviews[i][j].setFitWidth(cell_width / 1.25);
                imageviews[i][j].setFitHeight(cell_height / 1.25);
                imageviews[i][j].setTranslateX(chessBoard.getBoard()[i][j].getWidth() / 8);
            }
        }
    }

    public PieceView selectTarget(int hash) {

        int i = 0;
        int j = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (imageviews[x][y].hashCode() == hash || chessBoard.getBoard()[x][y].hashCode() == hash) {
                    i = x;
                    j = y;
                }
            }
        }

        if (!winner) {
            if (chessBoardPiecesView.pieceViews[i][j].getColor() == NONE
                    || chessBoardPiecesView.pieceViews[i][j].getColor() == currentPlayerColor.mirror()) {
                return chessBoardPiecesView.pieceViews[i][j];
            }
        }
        return chessBoardPiecesView.pieceViews[i][j];
    }

    //select piece method
    public PieceView selectPiece(int hash) {
        // Determine what piece was selected and if it can be selected
        int i = 0;
        int j = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (imageviews[x][y].hashCode() == hash && imageviews[x][y] != null) {
                    i = x;
                    j = y;
                }
            }
        }

        if (currentPlayerColor == WHITE && !winner) {
            if (chessBoardPiecesView.pieceViews[i][j].getColor() == WHITE) {
                // If player has already selected the piece, deselect it
                if (chessBoard.getBoard()[i][j].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    chessBoard.resetColorsOnField(i, j);
                }
                // Otherwise select it and work out moves
                else {
                    chessBoard.getBoard()[i][j].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoardPiecesView.pieceViews[i][j];
                }
            }
        }

        // If current player is black
        else {
            if (chessBoardPiecesView.pieceViews[i][j].getColor() == BLACK) {
                if (chessBoard.getBoard()[i][j].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    // Resets color
                    chessBoard.resetColorsOnField(i, j);
                } else {
                    chessBoard.getBoard()[i][j].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return chessBoardPiecesView.pieceViews[i][j];
                }
            }
        }

        // return something ..
        return new Empty(i, j);
    }

    // Draw the move and remove highlights
    public void drawmove(final int si, int sj, int ti, int tj) {
        imageviews[ti][tj].setImage(chessBoardPiecesView.pieceViews[ti][tj].getImage());
        imageviews[si][sj].setImage(new Image("empty.png"));

        // Remove highlight
        if (chessBoard.getBoard()[si][sj].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
            chessBoard.resetColorsOnField(si, sj);
        } else if (!isPieceSelected) {
            chessBoard.getBoard()[si][sj].setStroke(Color.LIGHTCORAL);
            isPieceSelected = true;
        }
    }

    // Returns state of the chess board ..
    public PieceView[][] getState() {
        return chessBoardPiecesView.pieceViews;
    }

    public void changeplayer() {
        currentPlayerColor = currentPlayerColor.mirror();
    }
}
