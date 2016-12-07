package com.stolz.alexander.chessengine;

import com.stolz.alexander.chessengine.engine.pieces.*;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

public class GameLogic implements Cloneable {

    private String secondclick = "false";
    private boolean check = false;
    private int checki;
    private int checkj;
    private PieceColor currentplayer;
    private PieceColor otherplayer;

    // Keep track of mouse clicks

    public String changeclick() {
        if (secondclick == "true") {
            secondclick = "false";
        } else {
            secondclick = "true";
        }

        return secondclick;
    }

    public int checki() {
        return checki;
    }

    public int checkj() {
        return checkj;
    }

    public boolean checkstatus() {
        return check;
    }

    public void flipcheck() {
        if (check == true) {
            check = false;
        }
    }

    // Take current boardstate and evaluate check for all pieces in boardstate
    public String check4checkmate(PieceColor currentplayer, Piece[][] boardstate) {
        String checkmateflag = "null";
        // For loop to check every piece on current board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece[][] possiblemoves = new Piece[8][8];
                // No need to run check on empty pieces and enemy pieces
                if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && boardstate[x][y].getColor() == otherplayer) {
                    // Create an array of possible moves for this piece
                    possiblemoves = checkMateMoves(boardstate[x][y], boardstate);
                    // If possiblemoves has a move that resolves check == false, flag=false
                    if (check4check(currentplayer, possiblemoves) == false) {
                        checkmateflag = "false";
                    }
                }
            }
        }


        // If possiblemoves has no move that resolves flag to false, checkmate==true
        if (checkmateflag.equals("null")) {
            System.out.println("CHECKMATE!");
            System.out.println("--- Press Spacebar to Reset ---");
            return checkmateflag = "true";
        } else {
            return checkmateflag = "null";
        }
    }

    // Method for checkmate, puts all valid moves for a piece into a boardstate

    public Piece[][] checkMateMoves(Piece p, Piece[][] pieces) {

        Piece[][] possiblemoves = new Piece[8][8];

        // Move board into new array because java
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                possiblemoves[x][y] = pieces[x][y];
            }
        }

        //_____________________________________WHITEPAWNS_____________________________________//
        if (p.toString() == "Pawn" && p.getColor() == WHITE) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if (p.jcoord() - 1 >= 0) { // Guard for bounds
                if (pieces[p.icoord()][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PiecePawn(WHITE, p.icoord(), p.jcoord() - 1, false);
                }
            }

            if (p.firstmove() == true) {
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if (pieces[p.icoord()][p.jcoord() - 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && pieces[p.icoord()][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() - 2] = new PiecePawn(WHITE, p.icoord(), p.jcoord() - 2, false);
                }
            }

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() - 1 >= 0 && p.jcoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PiecePawn(WHITE, p.icoord() - 1, p.jcoord() - 1, false);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() + 1 < 8 && p.jcoord() - 1 >= 0) {
                if (pieces[p.icoord() + 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PiecePawn(WHITE, p.icoord() + 1, p.jcoord() - 1, false);
                }
            }
        }

        //_____________________________________BLACKPAWNS_____________________________________//
        if (p.toString() == "Pawn" && p.getColor() == BLACK) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if (p.jcoord() + 1 < 8) { // Guard for bounds
                if (pieces[p.icoord()][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PiecePawn(BLACK, p.icoord(), p.jcoord() + 1, false);
                }
            }

            if (p.firstmove() == true) {
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if (pieces[p.icoord()][p.jcoord() + 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && pieces[p.icoord()][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() + 2] = new PiecePawn(BLACK, p.icoord(), p.jcoord() + 2, false);
                }
            }

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() - 1 >= 0 && p.jcoord() + 1 < 8) {
                if (pieces[p.icoord() - 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PiecePawn(BLACK, p.icoord() - 1, p.jcoord() + 1, false);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() + 1 < 8 && p.jcoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PiecePawn(BLACK, p.icoord() + 1, p.jcoord() + 1, false);
                }
            }
        }

        //__________________________________________WHITEROOK_____________________________________//
        if (p.toString() == "Rook" && p.getColor() == WHITE) {
            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceRook(WHITE, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceRook(WHITE, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieces[p.icoord()][y].getColor() == WHITE) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(WHITE, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(WHITE, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieces[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(WHITE, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(WHITE, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieces[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceRook(WHITE, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceRook(WHITE, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieces[p.icoord()][y].getColor() == WHITE && y != 8) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //__________________________________________BLACKROOK_____________________________________//

        if (p.toString() == "Rook" && p.getColor() == BLACK) {
            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceRook(BLACK, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceRook(BLACK, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieces[p.icoord()][y].getColor() == BLACK) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(BLACK, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(BLACK, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieces[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(BLACK, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceRook(BLACK, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieces[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceRook(BLACK, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceRook(BLACK, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieces[p.icoord()][y].getColor() == BLACK) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //__________________________________________WHITEBISHOP_____________________________________//
        if (p.toString() == "Bishop" && p.getColor() == WHITE) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceBishop(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }
        }
        //__________________________________________BLACKBISHOP_____________________________________//
        if (p.toString() == "Bishop" && p.getColor() == BLACK) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceBishop(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }
        }

        //_________________________________WHITEKNIGHT_____________________________________//

        // Assuming knights can jump regardless of what pieces are in the way

        if (p.toString() == "Knight" && p.getColor() == WHITE) {
            // Up and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieces[p.icoord() - 1][p.jcoord() - 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceKnight(WHITE, p.icoord() - 1, p.jcoord() - 2);
                }
                if (pieces[p.icoord() - 1][p.jcoord() - 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceKnight(WHITE, p.icoord() - 1, p.jcoord() - 2);
                }
            }

            // Up and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieces[p.icoord() - 2][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceKnight(WHITE, p.icoord() - 2, p.jcoord() - 1);
                }
                if (pieces[p.icoord() - 2][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceKnight(WHITE, p.icoord() - 2, p.jcoord() - 1);
                }
            }

            // Up and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieces[p.icoord() + 1][p.jcoord() - 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceKnight(WHITE, p.icoord() + 1, p.jcoord() - 2);
                }
                if (pieces[p.icoord() + 1][p.jcoord() - 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceKnight(WHITE, p.icoord() + 1, p.jcoord() - 2);
                }
            }

            // Up and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieces[p.icoord() + 2][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceKnight(WHITE, p.icoord() + 2, p.jcoord() - 1);
                }
                if (pieces[p.icoord() + 2][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceKnight(WHITE, p.icoord() + 2, p.jcoord() - 1);
                }
            }

            // Bottom and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieces[p.icoord() + 1][p.jcoord() + 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceKnight(WHITE, p.icoord() + 1, p.jcoord() + 2);
                }
                if (pieces[p.icoord() + 1][p.jcoord() + 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceKnight(WHITE, p.icoord() + 1, p.jcoord() + 2);
                }
            }

            // Bottom and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieces[p.icoord() + 2][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceKnight(WHITE, p.icoord() + 2, p.jcoord() + 1);
                }
                if (pieces[p.icoord() + 2][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceKnight(WHITE, p.icoord() + 2, p.jcoord() + 1);
                }
            }

            // Bottom and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieces[p.icoord() - 1][p.jcoord() + 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceKnight(WHITE, p.icoord() - 1, p.jcoord() + 2);
                }
                if (pieces[p.icoord() - 1][p.jcoord() + 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceKnight(WHITE, p.icoord() - 1, p.jcoord() + 2);
                }
            }

            // Bottom and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieces[p.icoord() - 2][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceKnight(WHITE, p.icoord() - 2, p.jcoord() + 1);
                }
                if (pieces[p.icoord() - 2][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceKnight(WHITE, p.icoord() - 2, p.jcoord() + 1);
                }
            }
        }

        //_________________________________BLACKKNIGHT_____________________________________//
        if (p.toString() == "Knight" && p.getColor() == BLACK) {
            // Up and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieces[p.icoord() - 1][p.jcoord() - 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceKnight(BLACK, p.icoord() - 1, p.jcoord() - 2);
                }
                if (pieces[p.icoord() - 1][p.jcoord() - 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceKnight(BLACK, p.icoord() - 1, p.jcoord() - 2);
                }
            }

            // Up and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieces[p.icoord() - 2][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceKnight(BLACK, p.icoord() - 2, p.jcoord() - 1);
                }
                if (pieces[p.icoord() - 2][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceKnight(BLACK, p.icoord() - 2, p.jcoord() - 1);
                }
            }

            // Up and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieces[p.icoord() + 1][p.jcoord() - 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceKnight(BLACK, p.icoord() + 1, p.jcoord() - 2);
                }
                if (pieces[p.icoord() + 1][p.jcoord() - 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceKnight(BLACK, p.icoord() + 1, p.jcoord() - 2);
                }
            }

            // Up and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieces[p.icoord() + 2][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceKnight(BLACK, p.icoord() + 2, p.jcoord() - 1);
                }
                if (pieces[p.icoord() + 2][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceKnight(BLACK, p.icoord() + 2, p.jcoord() - 1);
                }
            }

            // Bottom and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieces[p.icoord() + 1][p.jcoord() + 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceKnight(BLACK, p.icoord() + 1, p.jcoord() + 2);
                }
                if (pieces[p.icoord() + 1][p.jcoord() + 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceKnight(BLACK, p.icoord() + 1, p.jcoord() + 2);
                }
            }

            // Bottom and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieces[p.icoord() + 2][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceKnight(BLACK, p.icoord() + 2, p.jcoord() + 1);
                }
                if (pieces[p.icoord() + 2][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceKnight(BLACK, p.icoord() + 2, p.jcoord() + 1);
                }
            }

            // Bottom and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieces[p.icoord() - 1][p.jcoord() + 2].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceKnight(BLACK, p.icoord() - 1, p.jcoord() + 2);
                }
                if (pieces[p.icoord() - 1][p.jcoord() + 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceKnight(BLACK, p.icoord() - 1, p.jcoord() + 2);
                }
            }

            // Bottom and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieces[p.icoord() - 2][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceKnight(BLACK, p.icoord() - 2, p.jcoord() + 1);
                }
                if (pieces[p.icoord() - 2][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceKnight(BLACK, p.icoord() - 2, p.jcoord() + 1);
                }
            }

        }

        //______________________________WHITEQUEEN_____________________________________//
        if (p.toString() == "Queen" && p.getColor() == WHITE) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                }
                if (pieces[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceQueen(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieces[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }

            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(WHITE, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(WHITE, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieces[p.icoord()][y].getColor() == WHITE) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(WHITE, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(WHITE, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieces[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(WHITE, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(WHITE, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieces[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(WHITE, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(WHITE, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieces[p.icoord()][y].getColor() == WHITE && y != 8) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //______________________________BLACKQUEEN_____________________________________//
        if (p.toString() == "Queen" && p.getColor() == BLACK) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieces[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                }
                if (pieces[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceQueen(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieces[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }

            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(BLACK, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(BLACK, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieces[p.icoord()][y].getColor() == BLACK) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(BLACK, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(BLACK, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieces[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(BLACK, x, p.jcoord());
                }
                if (pieces[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceQueen(BLACK, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieces[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(BLACK, p.icoord(), y);
                }
                if (pieces[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceQueen(BLACK, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieces[p.icoord()][y].getColor() == BLACK && y != 8) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //_________________________________WHITEKING_____________________________________//
        if (p.toString() == "King" && p.getColor() == WHITE) {
            // Up
            if (p.jcoord() - 1 >= 0) {
                if (pieces[p.icoord()][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceKing(WHITE, p.icoord(), p.jcoord() - 1);
                }
                if (pieces[p.icoord()][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceKing(WHITE, p.icoord(), p.jcoord() - 1);
                }
            }

            // Up - right
            if (p.jcoord() - 1 >= 0 && p.icoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceKing(WHITE, p.icoord() + 1, p.jcoord() - 1);
                }
                if (pieces[p.icoord() + 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceKing(WHITE, p.icoord() + 1, p.jcoord() - 1);
                }
            }

            // Up - left
            if (p.jcoord() - 1 >= 0 && p.icoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceKing(WHITE, p.icoord() - 1, p.jcoord() - 1);
                }
                if (pieces[p.icoord() - 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceKing(WHITE, p.icoord() - 1, p.jcoord() - 1);
                }
            }

            // Left
            if (p.icoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceKing(WHITE, p.icoord() - 1, p.jcoord());
                }
                if (pieces[p.icoord() - 1][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceKing(WHITE, p.icoord() - 1, p.jcoord());
                }
            }

            // Right
            if (p.icoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceKing(WHITE, p.icoord() + 1, p.jcoord());
                }
                if (pieces[p.icoord() + 1][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceKing(WHITE, p.icoord() + 1, p.jcoord());
                }
            }

            // Down
            if (p.jcoord() + 1 < 8) {
                if (pieces[p.icoord()][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceKing(WHITE, p.icoord(), p.jcoord() + 1);
                }
                if (pieces[p.icoord()][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceKing(WHITE, p.icoord(), p.jcoord() + 1);
                }
            }

            // Down - left
            if (p.jcoord() + 1 < 8 && p.icoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceKing(WHITE, p.icoord() - 1, p.jcoord() + 1);
                }
                if (pieces[p.icoord() - 1][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceKing(WHITE, p.icoord() - 1, p.jcoord() + 1);
                }
            }

            // Down - right
            if (p.jcoord() + 1 < 8 && p.icoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceKing(WHITE, p.icoord() + 1, p.jcoord() + 1);
                }
                if (pieces[p.icoord() + 1][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceKing(WHITE, p.icoord() + 1, p.jcoord() + 1);
                }
            }
        }

        //_________________________________BLACKKING_____________________________________//
        if (p.toString() == "King" && p.getColor() == BLACK) {
            // Up
            if (p.jcoord() - 1 >= 0) {
                if (pieces[p.icoord()][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceKing(BLACK, p.icoord(), p.jcoord() - 1);
                }
                if (pieces[p.icoord()][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceKing(BLACK, p.icoord(), p.jcoord() - 1);
                }
            }

            // Up - right
            if (p.jcoord() - 1 >= 0 && p.icoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceKing(BLACK, p.icoord() + 1, p.jcoord() - 1);
                }
                if (pieces[p.icoord() + 1][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceKing(BLACK, p.icoord() + 1, p.jcoord() - 1);
                }
            }

            // Up - left
            if (p.jcoord() - 1 >= 0 && p.icoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord() - 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceKing(BLACK, p.icoord() - 1, p.jcoord() - 1);
                }
                if (pieces[p.icoord() - 1][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceKing(BLACK, p.icoord() - 1, p.jcoord() - 1);
                }
            }

            // Left
            if (p.icoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceKing(BLACK, p.icoord() - 1, p.jcoord());
                }
                if (pieces[p.icoord() - 1][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceKing(BLACK, p.icoord() - 1, p.jcoord());
                }
            }

            // Right
            if (p.icoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceKing(BLACK, p.icoord() + 1, p.jcoord());
                }
                if (pieces[p.icoord() + 1][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceKing(BLACK, p.icoord() + 1, p.jcoord());
                }
            }

            // Down
            if (p.jcoord() + 1 < 8) {
                if (pieces[p.icoord()][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceKing(BLACK, p.icoord(), p.jcoord() + 1);
                }
                if (pieces[p.icoord()][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceKing(BLACK, p.icoord(), p.jcoord() + 1);
                }
            }

            // Down - left
            if (p.jcoord() + 1 < 8 && p.icoord() - 1 >= 0) {
                if (pieces[p.icoord() - 1][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceKing(BLACK, p.icoord() - 1, p.jcoord() + 1);
                }
                if (pieces[p.icoord() - 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceKing(BLACK, p.icoord() - 1, p.jcoord() + 1);
                }
            }

            // Down - right
            if (p.jcoord() + 1 < 8 && p.icoord() + 1 < 8) {
                if (pieces[p.icoord() + 1][p.jcoord() + 1].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty")) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceKing(BLACK, p.icoord() + 1, p.jcoord() + 1);
                }
                if (pieces[p.icoord() + 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceKing(BLACK, p.icoord() + 1, p.jcoord() + 1);
                }
            }
        }

        // Return array of possible moves for the piece ..
        return possiblemoves;
    }

    // Check if player is in check
    public boolean check4check(PieceColor current_player, Piece[][] boardstate) {
        if (current_player == WHITE) {
            otherplayer = BLACK;
            currentplayer = WHITE;
        } else {
            otherplayer = WHITE;
            currentplayer = BLACK;
        }

        // For loop to go through each piece and see if it can target the king
        for (int xi = 0; xi < 8; xi++) {
            for (int yi = 0; yi < 8; yi++) {

                //_____________________________________PAWNS_____________________________________//
                if (boardstate[xi][yi].toString().equals("Pawn") && boardstate[xi][yi].getColor() == currentplayer) {
                    // LOOK ONE SQUARE LEFT DIAGONALLY IF KING PRESENT HIGHLIGHT
                    if (xi - 1 >= 0 && yi - 1 >= 0) {
                        if (boardstate[xi - 1][yi - 1].toString().equals("King") && boardstate[xi - 1][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 1;
                            checkj = yi - 1;
                            return true;
                        }
                    }

                    // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
                    if (xi + 1 < 8 && yi - 1 >= 0) {
                        if (boardstate[xi + 1][yi - 1].toString().equals("King") && boardstate[xi - 1][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 1;
                            checkj = yi - 1;
                            return true;
                        }
                    }
                }

                //__________________________________________ROOKS_____________________________________//
                if (boardstate[xi][yi].toString().equals("Rook") && boardstate[xi][yi].getColor() == currentplayer) {
                    // Look Up ..
                    for (int y = yi - 1; y >= 0; y--) {
                        boolean clearc = true;
                        if (!boardstate[xi][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[xi][y].toString().equals("King")) {
                            clearc = false;
                            y = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearc == true && y != -1) {
                            //System.out.println(clearc);
                            if (boardstate[xi][y].toString().equals("King") && boardstate[xi][y].getColor() == otherplayer) {
                                //System.out.println("gotcha");
                                check = true;
                                checki = xi;
                                checkj = y;
                                y = -1;
                                return true;
                            }
                        }
                    }

                    // Look Right ..
                    for (int x = xi + 1; x < 8; x++) {
                        boolean clearc = true;
                        if (!boardstate[x][yi].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][yi].toString().equals("King")) {
                            clearc = false;
                            x = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearc == true && x != 8) {
                            if (boardstate[x][yi].toString().equals("King") && boardstate[x][yi].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = yi;
                                x = 8;
                                return true;
                            }
                        }
                    }

                    // Look Left ..
                    for (int x = xi - 1; x >= 0; x--) {
                        boolean clearc = true;
                        if (!boardstate[x][yi].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][yi].toString().equals("King")) {
                            clearc = false;
                            x = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearc == true && x != -1) {
                            if (boardstate[x][yi].toString().equals("King") && boardstate[x][yi].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = yi;
                                x = -1;
                                return true;
                            }
                        }
                    }

                    // Look Down ..
                    for (int y = yi + 1; y < 8; y++) {
                        boolean clearc = true;
                        if (!boardstate[xi][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[xi][y].toString().equals("King")) {
                            clearc = false;
                            y = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearc == true && y != 8) {
                            if (boardstate[xi][y].toString().equals("King") && boardstate[xi][y].getColor() == otherplayer) {
                                check = true;
                                checki = xi;
                                checkj = y;
                                y = 8;
                                return true;
                            }
                        }
                    }
                }

                //__________________________________________BISHOPS_____________________________________//
                if (boardstate[xi][yi].toString().equals("Bishop") && boardstate[xi][yi].getColor() == currentplayer) {
                    // Look up .. (left)
                    for (int y = yi - 1, x = xi - 1; y >= 0 && x >= 0; y--, x--) {
                        boolean clearb = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearb = false;
                            y = -1;
                            x = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearb == true && y != -1 && x != -1) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                x = -1;
                                y = -1;
                                return true;
                            }
                        }
                    }

                    // Look up .. (right)
                    for (int y = yi - 1, x = xi + 1; y >= 0 && x < 8; y--, x++) {
                        boolean clearb = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearb = false;
                            y = -1;
                            x = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearb == true && x != 8 && y != -1) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                y = -1;
                                x = 8;
                                return true;
                            }
                        }
                    }

                    // Look down .. (left)
                    for (int y = yi + 1, x = xi - 1; y < 8 && x >= 0; y++, x--) {
                        boolean clearb = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearb = false;
                            y = 8;
                            x = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearb == true && y != 8 && x != -1) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                y = 8;
                                x = -1;
                                return true;
                            }
                        }
                    }

                    // Look down .. (right)
                    for (int y = yi + 1, x = xi + 1; y < 8 && x < 8; y++, x++) {
                        boolean clearb = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearb = false;
                            y = 8;
                            x = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearb == true && x != 8 && y != 8) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                y = 8;
                                x = 8;
                                return true;
                            }
                        }
                    }
                }

                //_________________________________KNIGHTS_____________________________________//

                // Assuming knights can jump regardless of what pieces are in the way

                if (boardstate[xi][yi].toString().equals("Knight") && boardstate[xi][yi].getColor() == currentplayer) {
                    // Up and left (first)
                    if (xi - 1 >= 0 && yi - 2 >= 0) {                // Bound check
                        if (boardstate[xi - 1][yi - 2].toString().equals("King") && boardstate[xi - 1][yi - 2].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 1;
                            checkj = yi - 2;
                            return true;
                        }
                    }

                    // Up and left (second)
                    if (xi - 2 >= 0 && yi - 1 >= 0) {                // Bound check
                        if (boardstate[xi - 2][yi - 1].toString().equals("King") && boardstate[xi - 2][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 2;
                            checkj = yi - 1;
                            return true;
                        }
                    }

                    // Up and right (first)
                    if (xi + 1 < 8 && yi - 2 >= 0) {                // Bound check
                        if (boardstate[xi + 1][yi - 2].toString().equals("King") && boardstate[xi + 1][yi - 2].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 1;
                            checkj = yi - 2;
                            return true;
                        }
                    }

                    // Up and right (second)
                    if (xi + 2 < 8 && yi - 1 >= 0) {                // Bound check
                        if (boardstate[xi + 2][yi - 1].toString().equals("King") && boardstate[xi + 2][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 2;
                            checkj = yi - 1;
                            return true;
                        }
                    }

                    // Bottom and right (first)
                    if (xi + 1 < 8 && yi + 2 < 8) {                // Bound check
                        if (boardstate[xi + 1][yi + 2].toString().equals("King") && boardstate[xi + 1][yi + 2].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 1;
                            checkj = yi + 2;
                            return true;
                        }
                    }

                    // Bottom and right (second)
                    if (xi + 2 < 8 && yi + 1 < 8) {                // Bound check
                        if (boardstate[xi + 2][yi + 1].toString().equals("King") && boardstate[xi + 2][yi + 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 2;
                            checkj = yi + 1;
                            return true;
                        }
                    }

                    // Bottom and left (first)
                    if (xi - 1 >= 0 && yi + 2 < 8) {                // Bound check
                        if (boardstate[xi - 1][yi + 2].toString().equals("King") && boardstate[xi - 1][yi + 2].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 1;
                            checkj = yi + 2;
                            return true;
                        }
                    }

                    // Bottom and left (second)
                    if (xi - 2 >= 0 && yi + 1 < 8) {                // Bound check
                        if (boardstate[xi - 2][yi + 1].toString().equals("King") && boardstate[xi - 2][yi + 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 2;
                            checkj = yi + 1;
                            return true;
                        }
                    }
                }

                //______________________________QUEENS_____________________________________//
                if (boardstate[xi][yi].toString().equals("Queen") && boardstate[xi][yi].getColor() == currentplayer) {
                    // Look Up ..
                    for (int y = yi - 1; y >= 0; y--) {
                        boolean clearq = true;
                        if (!boardstate[xi][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[xi][y].toString().equals("King")) {
                            clearq = false;
                            y = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && y != -1) {
                            if (boardstate[xi][y].toString().equals("King") && boardstate[xi][y].getColor() == otherplayer) {
                                check = true;
                                checki = xi;
                                checkj = y;
                                y = -1;
                                return true;
                            }
                        }
                    }

                    // Look Right ..
                    for (int x = xi + 1; x < 8; x++) {
                        boolean clearq = true;
                        if (!boardstate[x][yi].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][yi].toString().equals("King")) {
                            clearq = false;
                            x = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && x != 8) {
                            if (boardstate[x][yi].toString().equals("King") && boardstate[x][yi].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = yi;
                                x = 8;
                                return true;
                            }
                        }
                    }

                    // Look Left ..
                    for (int x = xi - 1; x >= 0; x--) {
                        boolean clearq = true;
                        if (!boardstate[x][yi].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][yi].toString().equals("King")) {
                            clearq = false;
                            x = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && x != -1) {
                            if (boardstate[x][yi].toString().equals("King") && boardstate[x][yi].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = yi;
                                x = -1;
                                return true;
                            }
                        }
                    }

                    // Look Down ..
                    for (int y = yi + 1; y < 8; y++) {
                        boolean clearq = true;
                        if (!boardstate[xi][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[xi][y].toString().equals("King")) {
                            clearq = false;
                            y = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && y != 8) {
                            if (boardstate[xi][y].toString().equals("King") && boardstate[xi][y].getColor() == otherplayer) {
                                check = true;
                                checki = xi;
                                checkj = y;
                                y = 8;
                                return true;
                            }
                        }
                    }

                    // Look up .. (left)
                    for (int y = yi - 1, x = xi - 1; y >= 0 && x >= 0; y--, x--) {
                        boolean clearq = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearq = false;
                            y = -1;
                            x = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && y != -1 && x != -1) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                x = -1;
                                y = -1;
                                return true;
                            }
                        }
                    }

                    // Look up .. (right)
                    for (int y = yi - 1, x = xi + 1; y >= 0 && x < 8; y--, x++) {
                        boolean clearq = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearq = false;
                            y = -1;
                            x = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && y != -1 && x != 8) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                y = -1;
                                x = 8;
                                return true;
                            }
                        }
                    }

                    // Look down .. (left)
                    for (int y = yi + 1, x = xi - 1; y < 8 && x >= 0; y++, x--) {
                        boolean clearq = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearq = false;
                            y = 8;
                            x = -1;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && y != 8 && x != -1) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                y = 8;
                                x = -1;
                                return true;
                            }
                        }
                    }

                    // Look down .. (right)
                    for (int y = yi + 1, x = xi + 1; y < 8 && x < 8; y++, x++) {
                        boolean clearq = true;
                        if (!boardstate[x][y].toString().equals("com.stolz.alexander.chessengine.engine.pieces.Empty") && !boardstate[x][y].toString().equals("King")) {
                            clearq = false;
                            y = 8;
                            x = 8;
                        }
                        // If path is clear, check for king ..
                        if (clearq == true && y != 8 && x != 8) {
                            if (boardstate[x][y].toString().equals("King") && boardstate[x][y].getColor() == otherplayer) {
                                check = true;
                                checki = x;
                                checkj = y;
                                y = 8;
                                x = 8;
                                return true;
                            }
                        }
                    }
                }

                //______________________________KINGS_____________________________________// (possibly don't need this .. )
                if (boardstate[xi][yi].toString().equals("King") && boardstate[xi][yi].getColor() == currentplayer) {
                    // Up
                    if (yi - 1 >= 0) {
                        if (boardstate[xi][yi - 1].toString().equals("King") && boardstate[xi][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi;
                            checkj = yi - 1;
                        }
                    }

                    // Up - right
                    if (yi - 1 >= 0 && xi + 1 < 8) {
                        if (boardstate[xi + 1][yi - 1].toString().equals("King") && boardstate[xi + 1][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 1;
                            checkj = yi - 1;
                        }
                    }

                    // Up - left
                    if (yi - 1 >= 0 && xi - 1 >= 0) {
                        if (boardstate[xi - 1][yi - 1].toString().equals("King") && boardstate[xi - 1][yi - 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 1;
                            checkj = yi - 1;
                        }
                    }

                    // Left
                    if (xi - 1 >= 0) {
                        if (boardstate[xi - 1][yi].toString().equals("King") && boardstate[xi - 1][yi].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 1;
                            checkj = yi;
                        }
                    }

                    // Right
                    if (xi + 1 < 8) {
                        if (boardstate[xi + 1][yi].toString().equals("King") && boardstate[xi + 1][yi].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 1;
                            checkj = yi;
                        }
                    }

                    // Down
                    if (yi + 1 < 8) {
                        if (boardstate[xi][yi + 1].toString().equals("King") && boardstate[xi][yi + 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi;
                            checkj = yi + 1;
                        }
                    }

                    // Down - left
                    if (yi + 1 < 8 && xi - 1 >= 0) {
                        if (boardstate[xi - 1][yi + 1].toString().equals("King") && boardstate[xi - 1][yi + 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi - 1;
                            checkj = yi + 1;
                        }
                    }

                    // Down - right
                    if (yi + 1 < 8 && xi + 1 < 8) {
                        if (boardstate[xi + 1][yi + 1].toString().equals("King") && boardstate[xi + 1][yi + 1].getColor() == otherplayer) {
                            check = true;
                            checki = xi + 1;
                            checkj = yi + 1;
                        }
                    }
                }
            }
        }
        // else return false ..
        check = false;
        return false;
    }
}
