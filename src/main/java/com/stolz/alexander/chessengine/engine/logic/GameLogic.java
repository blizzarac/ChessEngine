package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.gui.pieces.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;

public class GameLogic {

    private static Logger logger = Logger.getLogger(GameLogic.class.getName());

    private boolean check = false;

    private CheckValidator checkValidator;

    public GameLogic() {
        this.checkValidator = new CheckValidator();
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
    public boolean check4checkmate(PieceColor currentplayer, PieceView[][] boardstate) {
        logger.log(Level.FINE, "check4checkmate");
        boolean checkmateflag = true;
        // For loop to check every piece on current board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // No need to run check on empty pieces and enemy pieces
                if (!(boardstate[x][y].getType() == NOTYPE) && boardstate[x][y].getColor() == currentplayer.mirror()) {
                    // Create an array of possible moves for this piece
                    PieceView[][] possibleMoves = boardstate[x][y].findPossibleMoves(boardstate);
                    // If possiblemoves has a move that resolves check == false, flag=false
                    if (!checkValidator.check4check(currentplayer, possibleMoves)) {
                        checkmateflag = false;
                    }
                }
            }
        }

        logger.log(Level.FINE, "check4checkmate result: " + checkmateflag);
        return checkmateflag;
    }


    public CheckValidator getCheckValidator() {
        return checkValidator;
    }
}
