package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.logic.ClickState;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.gui.pieces.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

public class ChessBoardPane extends Pane {
    public final ChessBoard chessBoard;

    public PieceView[][] getPieceViews() {
        return pieceViews;
    }

    public Rectangle[][] getBoard() {
        return chessBoard.getBoard();
    }

    private PieceView[][] pieceViews;
    private Image[][] images;
    private ImageView[][] imageviews;


    private PieceColor currentPlayerColor = WHITE;
    private ClickState clickState = ClickState.FIRSTCLICK;

    private boolean isPieceSelected = false;
    private boolean winner = false;

    private Translate pos;

    public ClickState getClickState() {
        return clickState;
    }

    public void setClickLogoc(ClickState state) {
        this.clickState = state;
    }

    public PieceColor otherPlayerColor() {
        return currentPlayerColor.mirror();
    }

    public boolean isPieceSelected() {
        return isPieceSelected;
    }

    /**
     * Reset imageviews array
     */
    public void resetGame() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                imageviews[x][y].setImage(images[x][y]);
            }
        }
    }

    public ChessBoardPane() {
        chessBoard = new ChessBoard();
        pos = new Translate();

        // Initializes new board
        chessBoard.setBoard(chessBoard.initializeNewBoard());

        pieceViews = initPiecesViews();

        images = new Image[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                images[x][y] = pieceViews[x][y].getImage();
            }
        }

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
                imageviews[x][y].setImage(images[x][y]);
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

    private PieceView[][] initPiecesViews() {
        //initialize the board: background, data structures, inital layout of pieceViews
        PieceView[][] pieceViews = new PieceView[ChessBoard.boardWidth][ChessBoard.boardHeight];

        // White Pieces
        pieceViews[7][7] = new PieceViewRook(WHITE, 7, 7);
        pieceViews[6][7] = new PieceViewKnight(WHITE, 6, 7);
        pieceViews[5][7] = new PieceViewBishop(WHITE, 5, 7);
        pieceViews[4][7] = new PieceViewKing(WHITE, 4, 7);
        pieceViews[3][7] = new PieceViewQueen(WHITE, 3, 7);
        pieceViews[2][7] = new PieceViewBishop(WHITE, 2, 7);
        pieceViews[1][7] = new PieceViewKnight(WHITE, 1, 7);
        pieceViews[0][7] = new PieceViewRook(WHITE, 0, 7);
        // Pawns
        pieceViews[7][6] = new PieceViewPawn(WHITE, 7, 6, true);
        pieceViews[6][6] = new PieceViewPawn(WHITE, 6, 6, true);
        pieceViews[5][6] = new PieceViewPawn(WHITE, 5, 6, true);
        pieceViews[4][6] = new PieceViewPawn(WHITE, 4, 6, true);
        pieceViews[3][6] = new PieceViewPawn(WHITE, 3, 6, true);
        pieceViews[2][6] = new PieceViewPawn(WHITE, 2, 6, true);
        pieceViews[1][6] = new PieceViewPawn(WHITE, 1, 6, true);
        pieceViews[0][6] = new PieceViewPawn(WHITE, 0, 6, true);

        // com.stolz.alexander.chessengine.gui.pieceViews.Empty Pieces
        for (int x = 5; x > 1; x--) {
            for (int j = 0; j < 8; j++) {
                pieceViews[j][x] = new Empty(j, x);
            }
        }

        // Black Pieces
        pieceViews[7][0] = new PieceViewRook(BLACK, 7, 0);
        pieceViews[6][0] = new PieceViewKnight(BLACK, 6, 0);
        pieceViews[5][0] = new PieceViewBishop(BLACK, 5, 0);
        pieceViews[4][0] = new PieceViewKing(BLACK, 4, 0);
        pieceViews[3][0] = new PieceViewQueen(BLACK, 3, 0);
        pieceViews[2][0] = new PieceViewBishop(BLACK, 2, 0);
        pieceViews[1][0] = new PieceViewKnight(BLACK, 1, 0);
        pieceViews[0][0] = new PieceViewRook(BLACK, 0, 0);
        // Pawns
        pieceViews[7][1] = new PieceViewPawn(BLACK, 7, 1, true);
        pieceViews[6][1] = new PieceViewPawn(BLACK, 6, 1, true);
        pieceViews[5][1] = new PieceViewPawn(BLACK, 5, 1, true);
        pieceViews[4][1] = new PieceViewPawn(BLACK, 4, 1, true);
        pieceViews[3][1] = new PieceViewPawn(BLACK, 3, 1, true);
        pieceViews[2][1] = new PieceViewPawn(BLACK, 2, 1, true);
        pieceViews[1][1] = new PieceViewPawn(BLACK, 1, 1, true);
        pieceViews[0][1] = new PieceViewPawn(BLACK, 0, 1, true);

        return pieceViews;
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
                // Black Royalty
                if (j == 0) {
                    imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                    imageviews[i][j].relocate(i * cell_width, j * cell_height);
                    imageviews[i][j].setFitWidth(cell_width / 1.25);
                    imageviews[i][j].setFitHeight(cell_height / 1.25);
                    imageviews[i][j].setTranslateX(chessBoard.getBoard()[i][j].getWidth() / 8);
                }

                //White Royalty
                else if (j == 7) {
                    imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                    imageviews[i][j].relocate(i * cell_width, j * cell_height);
                    imageviews[i][j].setFitWidth(cell_width / 1.25);
                    imageviews[i][j].setFitHeight(cell_height / 1.25);
                    imageviews[i][j].setTranslateX(chessBoard.getBoard()[i][j].getWidth() / 8);
                } else {
                    imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                    imageviews[i][j].relocate(i * cell_width, j * cell_height);
                    imageviews[i][j].setFitWidth(cell_width / 1.25);
                    imageviews[i][j].setFitHeight(cell_height / 1.25);
                    imageviews[i][j].setTranslateX(chessBoard.getBoard()[i][j].getWidth() / 8);
                }
            }
        }
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        pos.setX(x);
        pos.setY(x);
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
            if (pieceViews[i][j].getColor() == NONE || pieceViews[i][j].getColor() == currentPlayerColor.mirror()) {
                return pieceViews[i][j];
            }
        }
        return pieceViews[i][j];
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
            if (pieceViews[i][j].getColor() == WHITE) {
                // If player has already selected the piece, deselect it
                if (chessBoard.getBoard()[i][j].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    if (i % 2 == 0 && j % 2 == 1) {
                        chessBoard.getBoard()[i][j].setFill(Color.YELLOW);
                        isPieceSelected = false;
                    }
                    if (i % 2 == 0 && j % 2 == 0) {
                        chessBoard.getBoard()[i][j].setFill(Color.BROWN);
                        isPieceSelected = false;
                    }
                    if (i % 2 == 1 && j % 2 == 1) {
                        chessBoard.getBoard()[i][j].setFill(Color.BROWN);
                        isPieceSelected = false;
                    }
                    if (i % 2 == 1 && j % 2 == 0) {
                        chessBoard.getBoard()[i][j].setFill(Color.YELLOW);
                        isPieceSelected = false;
                    }
                }
                // Otherwise select it and work out moves
                else {
                    chessBoard.getBoard()[i][j].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return pieceViews[i][j];
                }
            }
        }

        // If current player is black
        else {
            if (pieceViews[i][j].getColor() == BLACK) {
                if (chessBoard.getBoard()[i][j].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
                    // Resets color
                    if (i % 2 == 0 && j % 2 == 1) {
                        chessBoard.getBoard()[i][j].setFill(Color.YELLOW);
                        isPieceSelected = false;
                    }
                    if (i % 2 == 0 && j % 2 == 0) {
                        chessBoard.getBoard()[i][j].setFill(Color.BROWN);
                        isPieceSelected = false;
                    }
                    if (i % 2 == 1 && j % 2 == 1) {
                        chessBoard.getBoard()[i][j].setFill(Color.BROWN);
                        isPieceSelected = false;
                    }
                    if (i % 2 == 1 && j % 2 == 0) {
                        chessBoard.getBoard()[i][j].setFill(Color.YELLOW);
                        isPieceSelected = false;
                    }
                } else {
                    chessBoard.getBoard()[i][j].setStroke(Color.LIGHTCORAL);
                    isPieceSelected = true;
                    return pieceViews[i][j];
                }
            }
        }

        // return something ..
        return new Empty(i, j);
    }

    // Draw the move and remove highlights
    public void drawmove(final int si, int sj, int ti, int tj) {
        Image empty = new Image("empty.png");
        String piece = "???";
        piece = "black";
        if (pieceViews[ti][tj].getColor() == WHITE) {
            piece = "white";
        }
        piece = piece + pieceViews[ti][tj].getImageFilename();
        //System.out.println("The new piece getImage filename: " + piece);
        Image newimage = new Image("" + piece);
        imageviews[ti][tj].setImage(newimage);
        imageviews[si][sj].setImage(empty);
        // Remove highlight
        if (chessBoard.getBoard()[si][sj].getStroke() == Color.LIGHTCORAL && isPieceSelected) {
            if (si % 2 == 0 && sj % 2 == 1) {
                chessBoard.getBoard()[si][sj].setFill(Color.YELLOW);
            } else if (si % 2 == 0 && sj % 2 == 0) {
                chessBoard.getBoard()[si][sj].setFill(Color.BROWN);
            } else if (si % 2 == 1 && sj % 2 == 1) {
                chessBoard.getBoard()[si][sj].setFill(Color.BROWN);
            } else if (si % 2 == 1 && sj % 2 == 0) {
                chessBoard.getBoard()[si][sj].setFill(Color.YELLOW);
            }
        } else if (!isPieceSelected) {
            chessBoard.getBoard()[si][sj].setStroke(Color.LIGHTCORAL);
            isPieceSelected = true;
        }
    }

    // Returns state of the chess board ..
    public PieceView[][] getState() {
        return pieceViews;
    }

    public void setBoard(PieceView[][] newboard) {
        for (int x = 0; x < 8; x++) {
            System.arraycopy(newboard[x], 0, pieceViews[x], 0, 8);
        }
    }

    public void clearhighlights() {
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                chessBoard.getBoard()[x][j].setStroke(Color.TRANSPARENT);
                if (x % 2 == 0 && j % 2 == 1) {
                    chessBoard.getBoard()[x][j].setFill(Color.YELLOW);
                } else if (x % 2 == 0 && j % 2 == 0) {
                    chessBoard.getBoard()[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 1) {
                    chessBoard.getBoard()[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 0) {
                    chessBoard.getBoard()[x][j].setFill(Color.YELLOW);
                }
            }
        }
    }

    public void changeplayer() {
        if (currentPlayerColor == WHITE) {
            currentPlayerColor = BLACK;
        } else {
            currentPlayerColor = WHITE;
        }
    }
}
