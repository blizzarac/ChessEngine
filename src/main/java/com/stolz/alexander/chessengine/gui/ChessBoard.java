package com.stolz.alexander.chessengine.gui;

import com.stolz.alexander.chessengine.pieces.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Translate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.stolz.alexander.chessengine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.pieces.PieceColor.WHITE;

public class ChessBoard extends Pane {

    //private fields
    private int boardWidth = 8;
    private int boardHeight = 8;

    public Piece[][] getPieces() {
        return pieces;
    }

    public Rectangle[][] getBoard() {
        return board;
    }

    private Piece[][] pieces;
    private Rectangle[][] board;
    private Image[][] images;
    private ImageView[][] imageviews;


    private double cell_width;
    private double cell_height;
    private PieceColor current_player = WHITE;
    private String clicklogic = "false";

    private boolean pieceselect = false;
    private boolean winner = false;

    private Translate pos;

    public String clicklogic() {
        return clicklogic;
    }

    public void changeclicktrue() {
        clicklogic = "true";
    }

    public void changeclickfalse() {
        clicklogic = "false";
    }

    public void changeclicknull() {
        clicklogic = "null";
    }

    public PieceColor currentplayer() {
        return current_player;
    }

    public PieceColor otherplayer() {
        if (current_player == WHITE) {
            PieceColor otherplayer = BLACK;
            return otherplayer;
        } else {
            PieceColor otherplayer = WHITE;
            return otherplayer;
        }
    }

    public boolean pieceselect() {
        return pieceselect;
    }

    public void resetGame() {
        // Reset imageviews array
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                imageviews[x][y].setImage(images[x][y]);
            }
        }
    }

    public ChessBoard() {

        pos = new Translate();

        // Declares new board
        board = new Rectangle[boardWidth][boardHeight];

        // Initializes new board
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                board[x][j] = new Rectangle();
                board[x][j].setWidth(50);
                board[x][j].setHeight(50);
                board[x][j].setStroke(Color.TRANSPARENT);
                board[x][j].setStrokeType(StrokeType.INSIDE);
                board[x][j].setStrokeWidth(1);
            }
        }

        // Generates colours for the chessboard backgrounds
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                if (x % 2 == 0 && j % 2 == 1) {
                    board[x][j].setFill(Color.YELLOW);
                } else if (x % 2 == 0 && j % 2 == 0) {
                    board[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 1) {
                    board[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 0) {
                    board[x][j].setFill(Color.YELLOW);
                }
            }
        }

        // New image array
        images = new Image[8][8];

        // first row of renders (black)
        images[7][0] = new Image("blackrook.png");
        images[6][0] = new Image("blackknight.png");
        images[5][0] = new Image("blackbishop.png");
        images[4][0] = new Image("blackking.png");
        images[3][0] = new Image("blackqueen.png");
        images[2][0] = new Image("blackbishop.png");
        images[1][0] = new Image("blackknight.png");
        images[0][0] = new Image("blackrook.png");
        // second row (black)
        images[7][1] = new Image("blackpawn.png");
        images[6][1] = new Image("blackpawn.png");
        images[5][1] = new Image("blackpawn.png");
        images[4][1] = new Image("blackpawn.png");
        images[3][1] = new Image("blackpawn.png");
        images[2][1] = new Image("blackpawn.png");
        images[1][1] = new Image("blackpawn.png");
        images[0][1] = new Image("blackpawn.png");

        // empty squares
        for (int x = 0; x < 8; x++) {
            for (int y = 2; y < 6; y++) {
                images[x][y] = new Image("empty.png");
            }
        }

        // third row (white)
        images[7][6] = new Image("whitepawn.png");
        images[6][6] = new Image("whitepawn.png");
        images[5][6] = new Image("whitepawn.png");
        images[4][6] = new Image("whitepawn.png");
        images[3][6] = new Image("whitepawn.png");
        images[2][6] = new Image("whitepawn.png");
        images[1][6] = new Image("whitepawn.png");
        images[0][6] = new Image("whitepawn.png");
        // fourth row of renders (white)
        images[7][7] = new Image("whiterook.png");
        images[6][7] = new Image("whiteknight.png");
        images[5][7] = new Image("whitebishop.png");
        images[4][7] = new Image("whiteking.png");
        images[3][7] = new Image("whitequeen.png");
        images[2][7] = new Image("whitebishop.png");
        images[1][7] = new Image("whiteknight.png");
        images[0][7] = new Image("whiterook.png");

        // Viewers for each image
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
                imageviews[x][y].setTranslateX(board[x][y].getWidth() / 8);
            }
        }

        //initialize the board: background, data structures, inital layout of pieces
        pieces = new Piece[boardWidth][boardHeight];

        // White Pieces
        pieces[7][7] = new PieceRook(WHITE, 7, 7);
        pieces[6][7] = new PieceKnight(WHITE, 6, 7);
        pieces[5][7] = new PieceBishop(WHITE, 5, 7);
        pieces[4][7] = new PieceKing(WHITE, 4, 7);
        pieces[3][7] = new PieceQueen(WHITE, 3, 7);
        pieces[2][7] = new PieceBishop(WHITE, 2, 7);
        pieces[1][7] = new PieceKnight(WHITE, 1, 7);
        pieces[0][7] = new PieceRook(WHITE, 0, 7);
        // Pawns
        pieces[7][6] = new PiecePawn(WHITE, 7, 6, true);
        pieces[6][6] = new PiecePawn(WHITE, 6, 6, true);
        pieces[5][6] = new PiecePawn(WHITE, 5, 6, true);
        pieces[4][6] = new PiecePawn(WHITE, 4, 6, true);
        pieces[3][6] = new PiecePawn(WHITE, 3, 6, true);
        pieces[2][6] = new PiecePawn(WHITE, 2, 6, true);
        pieces[1][6] = new PiecePawn(WHITE, 1, 6, true);
        pieces[0][6] = new PiecePawn(WHITE, 0, 6, true);

        // com.stolz.alexander.chessengine.pieces.Empty Pieces
        for (int x = 5; x > 1; x--) {
            for (int j = 0; j < 8; j++) {
                pieces[j][x] = new Empty(NONE, j, x);
            }
        }

        // Black Pieces
        pieces[7][0] = new PieceRook(BLACK, 7, 0);
        pieces[6][0] = new PieceKnight(BLACK, 6, 0);
        pieces[5][0] = new PieceBishop(BLACK, 5, 0);
        pieces[4][0] = new PieceKing(BLACK, 4, 0);
        pieces[3][0] = new PieceQueen(BLACK, 3, 0);
        pieces[2][0] = new PieceBishop(BLACK, 2, 0);
        pieces[1][0] = new PieceKnight(BLACK, 1, 0);
        pieces[0][0] = new PieceRook(BLACK, 0, 0);
        // Pawns
        pieces[7][1] = new PiecePawn(BLACK, 7, 1, true);
        pieces[6][1] = new PiecePawn(BLACK, 6, 1, true);
        pieces[5][1] = new PiecePawn(BLACK, 5, 1, true);
        pieces[4][1] = new PiecePawn(BLACK, 4, 1, true);
        pieces[3][1] = new PiecePawn(BLACK, 3, 1, true);
        pieces[2][1] = new PiecePawn(BLACK, 2, 1, true);
        pieces[1][1] = new PiecePawn(BLACK, 1, 1, true);
        pieces[0][1] = new PiecePawn(BLACK, 0, 1, true);

        current_player = WHITE;
    }

    public void placeboard(final int i, final int j) {
        getChildren().add(board[i][j]);
    }

    public void placeimages(final int i, final int j) {
        getChildren().addAll(imageviews[i][j]);
    }

    // Returns stroke of board piece
    public boolean getStroke(final int i, final int j, Paint color) {
        if (board[i][j].getStroke() == color) {
            return true;
        } else return false;
    }

    public void checkhighlight(int x, int y) {
        board[x][y].setStroke(Color.RED);
    }

    //resize method
    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        cell_width = width / 8;
        cell_height = height / 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].resize(i * cell_width, j * cell_height);
                board[i][j].relocate(i * cell_width, j * cell_height);
                board[i][j].setStrokeWidth(cell_width / 16);
                board[i][j].setWidth(cell_width);
                board[i][j].setHeight(cell_height);
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                // Black Royalty
                if (j == 0) {
                    imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                    imageviews[i][j].relocate(i * cell_width, j * cell_height);
                    imageviews[i][j].setFitWidth(cell_width / 1.25);
                    imageviews[i][j].setFitHeight(cell_height / 1.25);
                    imageviews[i][j].setTranslateX(board[i][j].getWidth() / 8);
                }

                //White Royalty
                else if (j == 7) {
                    imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                    imageviews[i][j].relocate(i * cell_width, j * cell_height);
                    imageviews[i][j].setFitWidth(cell_width / 1.25);
                    imageviews[i][j].setFitHeight(cell_height / 1.25);
                    imageviews[i][j].setTranslateX(board[i][j].getWidth() / 8);
                } else {
                    imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                    imageviews[i][j].relocate(i * cell_width, j * cell_height);
                    imageviews[i][j].setFitWidth(cell_width / 1.25);
                    imageviews[i][j].setFitHeight(cell_height / 1.25);
                    imageviews[i][j].setTranslateX(board[i][j].getWidth() / 8);
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

    public Piece selectTarget(int hash) {

        int i = 0;
        int j = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (imageviews[x][y].hashCode() == hash || board[x][y].hashCode() == hash) {
                    i = x;
                    j = y;
                }
            }
        }

        PieceColor enemyplayer = NONE;

        if (current_player == WHITE) {
            enemyplayer = BLACK;
        } else {
            enemyplayer = WHITE;
        }

        if (winner == false) {
            if (pieces[i][j].getColor() == NONE || pieces[i][j].getColor() == enemyplayer) {
                return pieces[i][j];
            }
        }
        return pieces[i][j];
    }

    //select piece method
    public Piece selectPiece(int hash) {
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

        if (current_player == WHITE && winner == false) {
            if (pieces[i][j].getColor() == WHITE) {
                // If player has already selected the piece, deselect it
                if (board[i][j].getStroke() == Color.LIGHTCORAL && pieceselect == true) {
                    if (i % 2 == 0 && j % 2 == 1) {
                        board[i][j].setFill(Color.YELLOW);
                        pieceselect = false;
                    }
                    if (i % 2 == 0 && j % 2 == 0) {
                        board[i][j].setFill(Color.BROWN);
                        pieceselect = false;
                    }
                    if (i % 2 == 1 && j % 2 == 1) {
                        board[i][j].setFill(Color.BROWN);
                        pieceselect = false;
                    }
                    if (i % 2 == 1 && j % 2 == 0) {
                        board[i][j].setFill(Color.YELLOW);
                        pieceselect = false;
                    }
                }
                // Otherwise select it and work out moves
                else {
                    board[i][j].setStroke(Color.LIGHTCORAL);
                    pieceselect = true;
                    return pieces[i][j];
                }
            }
        }

        // If current player is black
        else {
            if (pieces[i][j].getColor() == BLACK) {
                if (board[i][j].getStroke() == Color.LIGHTCORAL && pieceselect == true) {
                    // Resets color
                    if (i % 2 == 0 && j % 2 == 1) {
                        board[i][j].setFill(Color.YELLOW);
                        pieceselect = false;
                    }
                    if (i % 2 == 0 && j % 2 == 0) {
                        board[i][j].setFill(Color.BROWN);
                        pieceselect = false;
                    }
                    if (i % 2 == 1 && j % 2 == 1) {
                        board[i][j].setFill(Color.BROWN);
                        pieceselect = false;
                    }
                    if (i % 2 == 1 && j % 2 == 0) {
                        board[i][j].setFill(Color.YELLOW);
                        pieceselect = false;
                    }
                } else {
                    board[i][j].setStroke(Color.LIGHTCORAL);
                    pieceselect = true;
                    return pieces[i][j];
                }
            }
        }

        // return something ..
        return new Empty(NONE, i, j);
    }

    // Draw the move and remove highlights
    public void drawmove(final int si, int sj, int ti, int tj) {
        Image empty = new Image("empty.png");
        String piece = "???";
        piece = "black";
        if (pieces[ti][tj].getColor() == WHITE) {
            piece = "white";
        }
        piece = piece + pieces[ti][tj].getImageFilename();
        //System.out.println("The new piece image filename: " + piece);
        Image newimage = new Image("" + piece);
        imageviews[ti][tj].setImage(newimage);
        imageviews[si][sj].setImage(empty);
        // Remove highlight
        if (board[si][sj].getStroke() == Color.LIGHTCORAL && pieceselect == true) {
            if (si % 2 == 0 && sj % 2 == 1) {
                board[si][sj].setFill(Color.YELLOW);
            } else if (si % 2 == 0 && sj % 2 == 0) {
                board[si][sj].setFill(Color.BROWN);
            } else if (si % 2 == 1 && sj % 2 == 1) {
                board[si][sj].setFill(Color.BROWN);
            } else if (si % 2 == 1 && sj % 2 == 0) {
                board[si][sj].setFill(Color.YELLOW);
            }
        } else if (pieceselect == false) {
            board[si][sj].setStroke(Color.LIGHTCORAL);
            pieceselect = true;
        }
    }

    // Returns state of the chess board ..
    public Piece[][] getState() {
        return pieces;
    }

    public void setBoard(Piece[][] newboard) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                pieces[x][y] = newboard[x][y];
                //System.out.println(pieces[y][x].toString() + " " + pieces[y][x].icoord() + "," + pieces[y][x].jcoord());
            }
        }
    }

    public void hoverhighlight(int x, int y) {
        int i = x;
        int j = y;
        // Set highlight color
        board[i][j].setStroke(Color.CADETBLUE);
    }

    public void clearhighlights() {
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                board[x][j].setStroke(Color.TRANSPARENT);
                if (x % 2 == 0 && j % 2 == 1) {
                    board[x][j].setFill(Color.YELLOW);
                } else if (x % 2 == 0 && j % 2 == 0) {
                    board[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 1) {
                    board[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 0) {
                    board[x][j].setFill(Color.YELLOW);
                }
            }
        }
    }

    public void changeplayer() {
        if (current_player == WHITE) {
            current_player = BLACK;
        } else {
            current_player = WHITE;
        }
    }
}