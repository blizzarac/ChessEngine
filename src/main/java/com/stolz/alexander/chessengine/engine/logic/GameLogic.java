package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.gui.pieces.*;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;

public class GameLogic implements Cloneable {

    private String secondclick = "false";
    private boolean check = false;

    private PieceColor otherplayer;

    private CheckValidator checkValidator;

    public GameLogic() {
        this.checkValidator = new CheckValidator();
    }

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
        return checkValidator.checki;
    }

    public int checkj() {
        return checkValidator.checkj;
    }

    public boolean checkstatus() {
        return check;
    }

    public void flipcheck() {
        check = !check;
    }

    // Take current boardstate and evaluate check for all pieces in boardstate
    public String check4checkmate(PieceColor currentplayer, PieceView[][] boardstate) {
        String checkmateflag = "null";
        // For loop to check every piece on current board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                PieceView[][] possiblemoves = new PieceView[8][8];
                // No need to run check on empty pieces and enemy pieces
                if (!(boardstate[x][y].getType() == NOTYPE) && boardstate[x][y].getColor() == otherplayer) {
                    // Create an array of possible moves for this piece
                    possiblemoves = checkMateMoves(boardstate[x][y], boardstate);
                    // If possiblemoves has a move that resolves check == false, flag=false
                    check = checkValidator.check4check(currentplayer, possiblemoves);
                    if (!check) {
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

    public PieceView[][] checkMateMoves(PieceView p, PieceView[][] pieceViews) {

        PieceView[][] possiblemoves = new PieceView[8][8];

        // Move board into new array because java
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieceViews[x], 0, possiblemoves[x], 0, 8);
        }

        //_____________________________________WHITEPAWNS_____________________________________//
        if (p.toString() == "Pawn" && p.getColor() == WHITE) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if (p.jcoord() - 1 >= 0) { // Guard for bounds
                if (pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewPawn(WHITE, p.icoord(), p.jcoord() - 1, false);
                }
            }

            if (p.firstmove()) {
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if (pieceViews[p.icoord()][p.jcoord() - 2].getType() == NOTYPE && pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 2] = new PieceViewPawn(WHITE, p.icoord(), p.jcoord() - 2, false);
                }
            }

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() - 1 >= 0 && p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewPawn(WHITE, p.icoord() - 1, p.jcoord() - 1, false);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() + 1 < 8 && p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewPawn(WHITE, p.icoord() + 1, p.jcoord() - 1, false);
                }
            }
        }

        //_____________________________________BLACKPAWNS_____________________________________//
        if (p.getType() == PAWN && p.getColor() == BLACK) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if (p.jcoord() + 1 < 8) { // Guard for bounds
                if (pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewPawn(BLACK, p.icoord(), p.jcoord() + 1, false);
                }
            }

            if (p.firstmove()) {
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if (pieceViews[p.icoord()][p.jcoord() + 2].getType() == NOTYPE && pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 2] = new PieceViewPawn(BLACK, p.icoord(), p.jcoord() + 2, false);
                }
            }

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() - 1 >= 0 && p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewPawn(BLACK, p.icoord() - 1, p.jcoord() + 1, false);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() + 1 < 8 && p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewPawn(BLACK, p.icoord() + 1, p.jcoord() + 1, false);
                }
            }
        }

        //__________________________________________WHITEROOK_____________________________________//
        if (p.getType() == ROOK && p.getColor() == WHITE) {
            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(WHITE, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(WHITE, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieceViews[p.icoord()][y].getColor() == WHITE) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(WHITE, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(WHITE, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(WHITE, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(WHITE, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(WHITE, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(WHITE, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieceViews[p.icoord()][y].getColor() == WHITE && y != 8) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //__________________________________________BLACKROOK_____________________________________//

        if (p.getType() == ROOK && p.getColor() == BLACK) {
            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(BLACK, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(BLACK, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieceViews[p.icoord()][y].getColor() == BLACK) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(BLACK, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(BLACK, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(BLACK, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewRook(BLACK, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(BLACK, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceViewRook(BLACK, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieceViews[p.icoord()][y].getColor() == BLACK) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //__________________________________________WHITEBISHOP_____________________________________//
        if (p.getType() == BISHOP && p.getColor() == WHITE) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewBishop(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }
        }
        //__________________________________________BLACKBISHOP_____________________________________//
        if (p.getType() == BISHOP && p.getColor() == BLACK) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewBishop(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }
        }

        //_________________________________WHITEKNIGHT_____________________________________//

        // Assuming knights can jump regardless of what pieceViews are in the way

        if (p.getType() == KNIGHT && p.getColor() == WHITE) {
            // Up and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieceViews[p.icoord() - 1][p.jcoord() - 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceViewKnight(WHITE, p.icoord() - 1, p.jcoord() - 2);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() - 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceViewKnight(WHITE, p.icoord() - 1, p.jcoord() - 2);
                }
            }

            // Up and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieceViews[p.icoord() - 2][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceViewKnight(WHITE, p.icoord() - 2, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() - 2][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceViewKnight(WHITE, p.icoord() - 2, p.jcoord() - 1);
                }
            }

            // Up and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieceViews[p.icoord() + 1][p.jcoord() - 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceViewKnight(WHITE, p.icoord() + 1, p.jcoord() - 2);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() - 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceViewKnight(WHITE, p.icoord() + 1, p.jcoord() - 2);
                }
            }

            // Up and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieceViews[p.icoord() + 2][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceViewKnight(WHITE, p.icoord() + 2, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() + 2][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceViewKnight(WHITE, p.icoord() + 2, p.jcoord() - 1);
                }
            }

            // Bottom and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieceViews[p.icoord() + 1][p.jcoord() + 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceViewKnight(WHITE, p.icoord() + 1, p.jcoord() + 2);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() + 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceViewKnight(WHITE, p.icoord() + 1, p.jcoord() + 2);
                }
            }

            // Bottom and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieceViews[p.icoord() + 2][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceViewKnight(WHITE, p.icoord() + 2, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() + 2][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceViewKnight(WHITE, p.icoord() + 2, p.jcoord() + 1);
                }
            }

            // Bottom and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieceViews[p.icoord() - 1][p.jcoord() + 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceViewKnight(WHITE, p.icoord() - 1, p.jcoord() + 2);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() + 2].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceViewKnight(WHITE, p.icoord() - 1, p.jcoord() + 2);
                }
            }

            // Bottom and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieceViews[p.icoord() - 2][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceViewKnight(WHITE, p.icoord() - 2, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() - 2][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceViewKnight(WHITE, p.icoord() - 2, p.jcoord() + 1);
                }
            }
        }

        //_________________________________BLACKKNIGHT_____________________________________//
        if (p.getType() == KNIGHT && p.getColor() == BLACK) {
            // Up and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieceViews[p.icoord() - 1][p.jcoord() - 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceViewKnight(BLACK, p.icoord() - 1, p.jcoord() - 2);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() - 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceViewKnight(BLACK, p.icoord() - 1, p.jcoord() - 2);
                }
            }

            // Up and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieceViews[p.icoord() - 2][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceViewKnight(BLACK, p.icoord() - 2, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() - 2][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceViewKnight(BLACK, p.icoord() - 2, p.jcoord() - 1);
                }
            }

            // Up and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieceViews[p.icoord() + 1][p.jcoord() - 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceViewKnight(BLACK, p.icoord() + 1, p.jcoord() - 2);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() - 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceViewKnight(BLACK, p.icoord() + 1, p.jcoord() - 2);
                }
            }

            // Up and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieceViews[p.icoord() + 2][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceViewKnight(BLACK, p.icoord() + 2, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() + 2][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceViewKnight(BLACK, p.icoord() + 2, p.jcoord() - 1);
                }
            }

            // Bottom and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieceViews[p.icoord() + 1][p.jcoord() + 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceViewKnight(BLACK, p.icoord() + 1, p.jcoord() + 2);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() + 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceViewKnight(BLACK, p.icoord() + 1, p.jcoord() + 2);
                }
            }

            // Bottom and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieceViews[p.icoord() + 2][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceViewKnight(BLACK, p.icoord() + 2, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() + 2][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceViewKnight(BLACK, p.icoord() + 2, p.jcoord() + 1);
                }
            }

            // Bottom and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieceViews[p.icoord() - 1][p.jcoord() + 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceViewKnight(BLACK, p.icoord() - 1, p.jcoord() + 2);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() + 2].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceViewKnight(BLACK, p.icoord() - 1, p.jcoord() + 2);
                }
            }

            // Bottom and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieceViews[p.icoord() - 2][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceViewKnight(BLACK, p.icoord() - 2, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() - 2][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceViewKnight(BLACK, p.icoord() - 2, p.jcoord() + 1);
                }
            }

        }

        //______________________________WHITEQUEEN_____________________________________//
        if (p.getType() == QUEEN && p.getColor() == WHITE) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                }
                if (pieceViews[x][y].getColor() == BLACK) {
                    possiblemoves[x][y] = new PieceViewQueen(WHITE, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieceViews[x][y].getColor() == WHITE) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }

            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(WHITE, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(WHITE, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieceViews[p.icoord()][y].getColor() == WHITE) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(WHITE, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(WHITE, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(WHITE, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(WHITE, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(WHITE, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == BLACK) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(WHITE, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieceViews[p.icoord()][y].getColor() == WHITE && y != 8) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //______________________________BLACKQUEEN_____________________________________//
        if (p.getType() == QUEEN && p.getColor() == BLACK) {
            // Look up .. (left)
            for (int y = p.jcoord() - 1, x = p.icoord() - 1; y >= 0 && x >= 0; y--, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
                if (x != -1 && y != -1 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = -1;
                }
            }

            // Look up .. (right)
            for (int y = p.jcoord() - 1, x = p.icoord() + 1; y >= 0 && x < 8; y--, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
                if (x != 8 && y != -1 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = -1;
                }
            }

            // Look down .. (left)
            for (int y = p.jcoord() + 1, x = p.icoord() - 1; y < 8 && x >= 0; y++, x--) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
                if (x != -1 && y != 8 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = -1;
                    y = 8;
                }
            }

            // Look down .. (right)
            for (int y = p.jcoord() + 1, x = p.icoord() + 1; y < 8 && x < 8; y++, x++) {
                if (pieceViews[x][y].getType() == NOTYPE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                }
                if (pieceViews[x][y].getColor() == WHITE) {
                    possiblemoves[x][y] = new PieceViewQueen(BLACK, x, y);
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
                if (x != 8 && y != 8 && pieceViews[x][y].getColor() == BLACK) {
                    // Stop Looking
                    x = 8;
                    y = 8;
                }
            }

            // Look Up ..
            for (int y = p.jcoord() - 1; y >= 0; y--) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(BLACK, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(BLACK, p.icoord(), y);
                    // Stop looking
                    y = -1;
                }
                if (y != -1 && pieceViews[p.icoord()][y].getColor() == BLACK) {
                    // Stop looking
                    y = -1;
                }
            }

            // Look Right ..
            for (int x = p.icoord() + 1; x < 8; x++) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(BLACK, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(BLACK, x, p.jcoord());
                    // Stop looking
                    x = 8;
                }
                if (x != 8 && pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = 8;
                }
            }

            // Look Left ..
            for (int x = p.icoord() - 1; x >= 0; x--) {
                if (pieceViews[x][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(BLACK, x, p.jcoord());
                }
                if (pieceViews[x][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[x][p.jcoord()] = new PieceViewQueen(BLACK, x, p.jcoord());
                    // Stop looking
                    x = -1;
                }
                if (x != -1 && pieceViews[x][p.jcoord()].getColor() == BLACK) {
                    // Stop looking
                    x = -1;
                }
            }

            // Look Down ..
            for (int y = p.jcoord() + 1; y < 8; y++) {
                if (pieceViews[p.icoord()][y].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(BLACK, p.icoord(), y);
                }
                if (pieceViews[p.icoord()][y].getColor() == WHITE) {
                    possiblemoves[p.icoord()][y] = new PieceViewQueen(BLACK, p.icoord(), y);
                    // Stop looking
                    y = 8;
                }
                if (y != 8 && pieceViews[p.icoord()][y].getColor() == BLACK && y != 8) {
                    // Stop looking
                    y = 8;
                }
            }
        }

        //_________________________________WHITEKING_____________________________________//
        if (p.getType() == KING && p.getColor() == WHITE) {
            // Up
            if (p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewKing(WHITE, p.icoord(), p.jcoord() - 1);
                }
                if (pieceViews[p.icoord()][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewKing(WHITE, p.icoord(), p.jcoord() - 1);
                }
            }

            // Up - right
            if (p.jcoord() - 1 >= 0 && p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewKing(WHITE, p.icoord() + 1, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewKing(WHITE, p.icoord() + 1, p.jcoord() - 1);
                }
            }

            // Up - left
            if (p.jcoord() - 1 >= 0 && p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewKing(WHITE, p.icoord() - 1, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewKing(WHITE, p.icoord() - 1, p.jcoord() - 1);
                }
            }

            // Left
            if (p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceViewKing(WHITE, p.icoord() - 1, p.jcoord());
                }
                if (pieceViews[p.icoord() - 1][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceViewKing(WHITE, p.icoord() - 1, p.jcoord());
                }
            }

            // Right
            if (p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceViewKing(WHITE, p.icoord() + 1, p.jcoord());
                }
                if (pieceViews[p.icoord() + 1][p.jcoord()].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceViewKing(WHITE, p.icoord() + 1, p.jcoord());
                }
            }

            // Down
            if (p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewKing(WHITE, p.icoord(), p.jcoord() + 1);
                }
                if (pieceViews[p.icoord()][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewKing(WHITE, p.icoord(), p.jcoord() + 1);
                }
            }

            // Down - left
            if (p.jcoord() + 1 < 8 && p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewKing(WHITE, p.icoord() - 1, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewKing(WHITE, p.icoord() - 1, p.jcoord() + 1);
                }
            }

            // Down - right
            if (p.jcoord() + 1 < 8 && p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewKing(WHITE, p.icoord() + 1, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getColor() == BLACK) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewKing(WHITE, p.icoord() + 1, p.jcoord() + 1);
                }
            }
        }

        //_________________________________BLACKKING_____________________________________//
        if (p.getType() == KING && p.getColor() == BLACK) {
            // Up
            if (p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewKing(BLACK, p.icoord(), p.jcoord() - 1);
                }
                if (pieceViews[p.icoord()][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewKing(BLACK, p.icoord(), p.jcoord() - 1);
                }
            }

            // Up - right
            if (p.jcoord() - 1 >= 0 && p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewKing(BLACK, p.icoord() + 1, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewKing(BLACK, p.icoord() + 1, p.jcoord() - 1);
                }
            }

            // Up - left
            if (p.jcoord() - 1 >= 0 && p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewKing(BLACK, p.icoord() - 1, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewKing(BLACK, p.icoord() - 1, p.jcoord() - 1);
                }
            }

            // Left
            if (p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceViewKing(BLACK, p.icoord() - 1, p.jcoord());
                }
                if (pieceViews[p.icoord() - 1][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceViewKing(BLACK, p.icoord() - 1, p.jcoord());
                }
            }

            // Right
            if (p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceViewKing(BLACK, p.icoord() + 1, p.jcoord());
                }
                if (pieceViews[p.icoord() + 1][p.jcoord()].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceViewKing(BLACK, p.icoord() + 1, p.jcoord());
                }
            }

            // Down
            if (p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewKing(BLACK, p.icoord(), p.jcoord() + 1);
                }
                if (pieceViews[p.icoord()][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewKing(BLACK, p.icoord(), p.jcoord() + 1);
                }
            }

            // Down - left
            if (p.jcoord() + 1 < 8 && p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewKing(BLACK, p.icoord() - 1, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewKing(BLACK, p.icoord() - 1, p.jcoord() + 1);
                }
            }

            // Down - right
            if (p.jcoord() + 1 < 8 && p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewKing(BLACK, p.icoord() + 1, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getColor() == WHITE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewKing(BLACK, p.icoord() + 1, p.jcoord() + 1);
                }
            }
        }

        // Return array of possible moves for the piece ..
        return possiblemoves;
    }


    public CheckValidator getCheckValidator() {
        return checkValidator;
    }
}
