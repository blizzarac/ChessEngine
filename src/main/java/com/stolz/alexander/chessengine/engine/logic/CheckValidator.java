package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;

public class CheckValidator {
    private static Logger logger = Logger.getLogger(CheckValidator.class.getName());

    private boolean check = false;

    public boolean checkstate() {
        return check;
    }

    public void flipcheck() {
        if (check) {
            check = false;
        } else {
            check = true;
        }
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
                    check = check4check(possibleMoves, currentPlayer);
                    if (!check) {
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
    public boolean check4check(Piece[][] pieces, PieceColor currentPlayer) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece pieceToCheck = pieces[x][y];
                if (pieceToCheck.getType() != NOTYPE && pieceToCheck.isCheck(pieces, currentPlayer)) {
                    return true;
                }
            }
        }

        return false;
    }
}
