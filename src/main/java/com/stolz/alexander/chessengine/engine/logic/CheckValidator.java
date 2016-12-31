package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;

public class CheckValidator {
    private static Logger logger = Logger.getLogger(CheckValidator.class.getName());

    private PiecePosition check = null;
    public PiecePosition checkstate() {
        return check;
    }
    public void flipcheck() {
        check = null;
    }


    // Take current boardstate and evaluate check for all pieces in boardstate
    public boolean check4checkmate(PieceColor currentPlayer, Piece[][] boardstate) {
        logger.log(Level.FINE, "check4checkmate");
        boolean checkmateflag = true;
        // For loop to check every piece on current fields
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // No need to run check on empty pieces and enemy pieces
                if (!(boardstate[x][y].getType() == NOTYPE) && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                    // Create an array of possible moves for this piece
                    Piece[][] possibleMoves = findBoardWithPossiblePiecePositions(boardstate[x][y], boardstate);
                    // If possiblemoves has a move that resolves check == false, flag=false
                    check = check4check(currentPlayer, possibleMoves);
                    if (check == null) {
                        checkmateflag = false;
                    }
                }
            }
        }

        logger.log(Level.FINE, "check4checkmate result: " + checkmateflag);
        return checkmateflag;
    }

    private Piece[][] findBoardWithPossiblePiecePositions(Piece currentPiece, Piece[][] pieces) {
        Piece[][] possiblemoves = new Piece[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieces[x], 0, possiblemoves[x], 0, 8);
        }

        currentPiece.findValidMoves(pieces).stream().forEach(move -> {
                    Piece copy = currentPiece.copy();
                    copy.setPiecePosition(new PiecePosition(move.x, move.y));
                    possiblemoves[move.x][move.y] = copy;
                }
        );

        return possiblemoves;
    }


    /**
     * Check if player is in check
     */
    public PiecePosition check4check(PieceColor currentplayer, Piece[][] boardstate) {
        // For loop to go through each piece and see if it can target the king
        for (int xi = 0; xi < 8; xi++) {
            for (int yi = 0; yi < 8; yi++) {
                PiecePosition check = check4CheckPawn(currentplayer, boardstate, xi, yi);
                if (check != null) return check;

                check = check4CheckRook(currentplayer, boardstate, xi, yi);
                if (check != null) return check;

                check = check4CheckBishop(currentplayer, boardstate, xi, yi);
                if (check != null) return check;

                check = check4CheckKnight(currentplayer, boardstate, xi, yi);
                if (check != null) return check;

                check = check4CheckQueen(currentplayer, boardstate, xi, yi);
                if (check != null) return check;
            }
        }
        // else return false ..
        return null;
    }

    private PiecePosition check4CheckQueen(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
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
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(xi, y);
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
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, yi);
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
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, yi);
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
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(xi, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
                    }
                }
            }
        }
        return null;
    }

    private PiecePosition check4CheckKnight(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //_________________________________KNIGHTS_____________________________________//

        // Assuming knights can jump regardless of what pieces are in the way

        if (boardstate[xi][yi].toString().equals("Knight") && boardstate[xi][yi].getColor() == currentplayer) {
            // Up and left (first)
            if (xi - 1 >= 0 && yi - 2 >= 0) {                // Bound check
                if (boardstate[xi - 1][yi - 2].getType() == KING && boardstate[xi - 1][yi - 2].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi - 1, yi - 2);
                }
            }

            // Up and left (second)
            if (xi - 2 >= 0 && yi - 1 >= 0) {                // Bound check
                if (boardstate[xi - 2][yi - 1].getType() == KING && boardstate[xi - 2][yi - 1].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi - 2, yi - 1);
                }
            }

            // Up and right (first)
            if (xi + 1 < 8 && yi - 2 >= 0) {                // Bound check
                if (boardstate[xi + 1][yi - 2].getType() == KING && boardstate[xi + 1][yi - 2].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi + 1, yi - 2);
                }
            }

            // Up and right (second)
            if (xi + 2 < 8 && yi - 1 >= 0) {                // Bound check
                if (boardstate[xi + 2][yi - 1].getType() == KING && boardstate[xi + 2][yi - 1].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi + 2, yi - 1);
                }
            }

            // Bottom and right (first)
            if (xi + 1 < 8 && yi + 2 < 8) {                // Bound check
                if (boardstate[xi + 1][yi + 2].getType() == KING && boardstate[xi + 1][yi + 2].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi + 1, yi + 2);
                }
            }

            // Bottom and right (second)
            if (xi + 2 < 8 && yi + 1 < 8) {                // Bound check
                if (boardstate[xi + 2][yi + 1].getType() == KING && boardstate[xi + 2][yi + 1].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi + 2, yi + 1);
                }
            }

            // Bottom and left (first)
            if (xi - 1 >= 0 && yi + 2 < 8) {                // Bound check
                if (boardstate[xi - 1][yi + 2].getType() == KING && boardstate[xi - 1][yi + 2].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi - 1, yi + 2);
                }
            }

            // Bottom and left (second)
            if (xi - 2 >= 0 && yi + 1 < 8) {                // Bound check
                if (boardstate[xi - 2][yi + 1].getType() == KING && boardstate[xi - 2][yi + 1].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi - 2, yi + 1);
                }
            }
        }
        return null;
    }

    private PiecePosition check4CheckBishop(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //__________________________________________BISHOPS_____________________________________//
        if (boardstate[xi][yi].getType() == BISHOP && boardstate[xi][yi].getColor() == currentplayer) {
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
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
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, y);
                    }
                }
            }
        }
        return null;
    }

    private PiecePosition check4CheckRook(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
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
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(xi, y);
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
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, yi);
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
                    if (boardstate[x][yi].getType() == KING && boardstate[x][yi].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(x, yi);
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
                    if (boardstate[xi][y].getType() == KING && boardstate[xi][y].getColor() == currentplayer.mirror()) {
                        return new PiecePosition(xi, y);
                    }
                }
            }
        }
        return null;
    }

    private PiecePosition check4CheckPawn(PieceColor currentplayer, Piece[][] boardstate, int xi, int yi) {
        //_____________________________________PAWNS_____________________________________//
        if (boardstate[xi][yi].toString().equals("Pawn") && boardstate[xi][yi].getColor() == currentplayer) {
            // LOOK ONE SQUARE LEFT DIAGONALLY IF KING PRESENT HIGHLIGHT
            if (xi - 1 >= 0 && yi - 1 >= 0) {
                if (boardstate[xi - 1][yi - 1].getType() == KING && boardstate[xi - 1][yi - 1].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi - 1, yi -1);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (xi + 1 < 8 && yi - 1 >= 0) {
                if (boardstate[xi + 1][yi - 1].getType() == KING && boardstate[xi - 1][yi - 1].getColor() == currentplayer.mirror()) {
                    return new PiecePosition(xi + 1, yi -1);
                }
            }
        }
        return null;
    }
}
