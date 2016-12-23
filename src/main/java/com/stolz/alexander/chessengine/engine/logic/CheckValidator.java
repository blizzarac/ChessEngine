package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;

/**
 * Created by alexanderstolz on 12/8/16.
 */
public class CheckValidator {
    private PieceColor otherplayer;
    public int checki;
    public int checkj;

    // Check if player is in check
    public boolean check4check(PieceColor currentplayer, Piece[][] boardstate) {
        if (currentplayer == WHITE) {
            otherplayer = BLACK;
        } else {
            otherplayer = WHITE;
        }

        // For loop to go through each piece and see if it can target the king
        for (int xi = 0; xi < 8; xi++) {
            for (int yi = 0; yi < 8; yi++) {
                if (check4CheckPawn(currentplayer, boardstate, xi, yi)) return true;
                if (check4CheckRook(currentplayer, boardstate, xi, yi)) return true;
                if (check4CheckBishop(currentplayer, boardstate, xi, yi)) return true;
                if (check4CheckKnight(currentplayer, boardstate, xi, yi)) return true;
                if (check4CheckQueen(currentplayer, boardstate, xi, yi)) return true;
            }
        }
        // else return false ..
        return false;
    }

    private boolean check4CheckQueen(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        if (boardstate[xi][yi].getType() == QUEEN && boardstate[xi][yi].getColor() == currentplayer) {
            // Look Up ..
            for (int y = yi - 1; y >= 0; y--) {
                boolean clearq = true;
                if (!(boardstate[xi][y].getType() == NOTYPE) && !(boardstate[xi][y].getType() == KING)) {
                    clearq = false;
                    y = -1;
                }
                // If path is clear, check for king ..
                if (clearq && y != -1) {
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][yi].getType() == NOTYPE) && !(boardstate[x][yi].getType() == KING)) {
                    clearq = false;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearq && x != 8) {
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == otherplayer) {
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
                if (!(boardstate[x][yi].getType() == NOTYPE) && !(boardstate[x][yi].getType() == KING)) {
                    clearq = false;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearq && x != -1) {
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == otherplayer) {
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
                if (!(boardstate[xi][y].getType() == NOTYPE) && !(boardstate[xi][y].getType() == KING)) {
                    clearq = false;
                    y = 8;
                }
                // If path is clear, check for king ..
                if (clearq && y != 8) {
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = -1;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearq && y != -1 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = -1;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearq && y != -1 && x != 8) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = 8;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearq && y != 8 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = 8;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearq && y != 8 && x != 8) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
                        checki = x;
                        checkj = y;
                        y = 8;
                        x = 8;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean check4CheckKnight(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //_________________________________KNIGHTS_____________________________________//

        // Assuming knights can jump regardless of what pieces are in the way

        if (boardstate[xi][yi].toString().equals("Knight") && boardstate[xi][yi].getColor() == currentplayer) {
            // Up and left (first)
            if (xi - 1 >= 0 && yi - 2 >= 0) {                // Bound check
                if (boardstate[xi - 1][yi - 2].getType() == KING && boardstate[xi - 1][yi - 2].getColor() == otherplayer) {
                    checki = xi - 1;
                    checkj = yi - 2;
                    return true;
                }
            }

            // Up and left (second)
            if (xi - 2 >= 0 && yi - 1 >= 0) {                // Bound check
                if (boardstate[xi - 2][yi - 1].getType() == KING && boardstate[xi - 2][yi - 1].getColor() == otherplayer) {
                    checki = xi - 2;
                    checkj = yi - 1;
                    return true;
                }
            }

            // Up and right (first)
            if (xi + 1 < 8 && yi - 2 >= 0) {                // Bound check
                if (boardstate[xi + 1][yi - 2].getType() == KING && boardstate[xi + 1][yi - 2].getColor() == otherplayer) {
                    checki = xi + 1;
                    checkj = yi - 2;
                    return true;
                }
            }

            // Up and right (second)
            if (xi + 2 < 8 && yi - 1 >= 0) {                // Bound check
                if (boardstate[xi + 2][yi - 1].getType() == KING && boardstate[xi + 2][yi - 1].getColor() == otherplayer) {
                    checki = xi + 2;
                    checkj = yi - 1;
                    return true;
                }
            }

            // Bottom and right (first)
            if (xi + 1 < 8 && yi + 2 < 8) {                // Bound check
                if (boardstate[xi + 1][yi + 2].getType() == KING && boardstate[xi + 1][yi + 2].getColor() == otherplayer) {
                    checki = xi + 1;
                    checkj = yi + 2;
                    return true;
                }
            }

            // Bottom and right (second)
            if (xi + 2 < 8 && yi + 1 < 8) {                // Bound check
                if (boardstate[xi + 2][yi + 1].getType() == KING && boardstate[xi + 2][yi + 1].getColor() == otherplayer) {
                    checki = xi + 2;
                    checkj = yi + 1;
                    return true;
                }
            }

            // Bottom and left (first)
            if (xi - 1 >= 0 && yi + 2 < 8) {                // Bound check
                if (boardstate[xi - 1][yi + 2].getType() == KING && boardstate[xi - 1][yi + 2].getColor() == otherplayer) {
                    checki = xi - 1;
                    checkj = yi + 2;
                    return true;
                }
            }

            // Bottom and left (second)
            if (xi - 2 >= 0 && yi + 1 < 8) {                // Bound check
                if (boardstate[xi - 2][yi + 1].getType() == KING && boardstate[xi - 2][yi + 1].getColor() == otherplayer) {
                    checki = xi - 2;
                    checkj = yi + 1;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check4CheckBishop(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //__________________________________________BISHOPS_____________________________________//
        if (boardstate[xi][yi].toString().equals("Bishop") && boardstate[xi][yi].getColor() == currentplayer) {
            // Look up .. (left)
            for (int y = yi - 1, x = xi - 1; y >= 0 && x >= 0; y--, x--) {
                boolean clearb = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = -1;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearb && y != -1 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = -1;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearb && x != 8 && y != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = 8;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearb && y != 8 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
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
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = 8;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearb && x != 8 && y != 8) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == otherplayer) {
                        checki = x;
                        checkj = y;
                        y = 8;
                        x = 8;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean check4CheckRook(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //__________________________________________ROOKS_____________________________________//
        if (boardstate[xi][yi].toString().equals("Rook") && boardstate[xi][yi].getColor() == currentplayer) {
            // Look Up ..
            for (int y = yi - 1; y >= 0; y--) {
                boolean clearc = true;
                if (!(boardstate[xi][y].getType() == NOTYPE) && !(boardstate[xi][y].getType() == KING)) {
                    clearc = false;
                    y = -1;
                }
                // If path is clear, check for king ..
                if (clearc && y != -1) {
                    //System.out.println(clearc);
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == otherplayer) {
                        //System.out.println("gotcha");
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
                if (!(boardstate[x][yi].getType() == NOTYPE) && !(boardstate[x][yi].getType() == KING)) {
                    clearc = false;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearc && x != 8) {
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == otherplayer) {
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
                if (!(boardstate[x][yi].getType() == NOTYPE) && !(boardstate[x][yi].getType() == KING)) {
                    clearc = false;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearc && x != -1) {
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == otherplayer) {
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
                if (!(boardstate[xi][y].getType() == NOTYPE) && !(boardstate[xi][y].getType() == KING)) {
                    clearc = false;
                    y = 8;
                }
                // If path is clear, check for king ..
                if (clearc && y != 8) {
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == otherplayer) {
                        checki = xi;
                        checkj = y;
                        y = 8;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean check4CheckPawn(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //_____________________________________PAWNS_____________________________________//
        if (boardstate[xi][yi].toString().equals("Pawn") && boardstate[xi][yi].getColor() == currentplayer) {
            // LOOK ONE SQUARE LEFT DIAGONALLY IF KING PRESENT HIGHLIGHT
            if (xi - 1 >= 0 && yi - 1 >= 0) {
                if (boardstate[xi - 1][yi - 1].getType() == KING && boardstate[xi - 1][yi - 1].getColor() == otherplayer) {
                    checki = xi - 1;
                    checkj = yi - 1;
                    return true;
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (xi + 1 < 8 && yi - 1 >= 0) {
                if (boardstate[xi + 1][yi - 1].getType() == KING && boardstate[xi - 1][yi - 1].getColor() == otherplayer) {
                    checki = xi + 1;
                    checkj = yi - 1;
                    return true;
                }
            }
        }
        return false;
    }
}
