package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.gui.pieces.*;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.*;

public class GameLogic {

    private String secondclick = "false";
    private boolean check = false;

    private CheckValidator checkValidator;
    private MoveCalculator moveCalculator;

    public GameLogic() {
        this.checkValidator = new CheckValidator();
        this.moveCalculator = new MoveCalculator();
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
    public boolean check4checkmate(PieceColor currentplayer, PieceView[][] boardstate) {
        boolean checkmateflag = true;
        // For loop to check every piece on current board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // No need to run check on empty pieces and enemy pieces
                if (!(boardstate[x][y].getType() == NOTYPE) && boardstate[x][y].getColor() == currentplayer.mirror()) {
                    // Create an array of possible moves for this piece
                    PieceView[][] possiblemoves = moveCalculator.findPossibleMoves(boardstate[x][y], boardstate);
                    // If possiblemoves has a move that resolves check == false, flag=false
                    if (!checkValidator.check4check(currentplayer, possiblemoves)) {
                        checkmateflag = false;
                    }
                }
            }
        }

        return checkmateflag;
    }


    public CheckValidator getCheckValidator() {
        return checkValidator;
    }
}
