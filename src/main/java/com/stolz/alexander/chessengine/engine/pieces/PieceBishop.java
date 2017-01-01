package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.BISHOP;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.KING;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.NOTYPE;

public class PieceBishop extends Piece {

    public PieceBishop(PieceColor color, int ii, int jj) {
        super(PieceType.BISHOP, color, ii, jj);
    }

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
        // Look up .. (left)
        for(int y = this.y()-1, x = this.x()-1; y >= 0 && x >= 0; y--,x--){
            if(pieces[x][y].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=-1; y=-1;
            }
            if(x!=-1 && y!=-1 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=-1; y=-1;
            }
        }

        // Look up .. (right)
        for(int y = this.y()-1, x = this.x()+1; y >= 0 && x < 8; y--,x++){
            if(pieces[x][y].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=8; y=-1;
            }
            if(x!=8 && y!=-1 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=8; y=-1;
            }
        }

        // Look down .. (left)
        for(int y = this.y()+1, x = this.x()-1; y < 8 && x >= 0; y++,x--){
            if(pieces[x][y].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=-1; y=8;
            }
            if(x!=-1 && y!=8 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=-1; y=8;
            }
        }

        // Look down .. (right)
        for(int y = this.y()+1, x = this.x()+1; y < 8 && x < 8; y++,x++){
            if(pieces[x][y].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=8;y=8;
            }
            if(x!=8 && y!=8 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=8;y=8;
            }
        }

        return validMoves;
    }

    @Override
    public PieceBishop copy() {
        return new PieceBishop(this.getColor(), this.x(), this.y());
    }

    @Override
    public boolean isCheck(Piece[][] boardstate, PieceColor currentPlayer) {
        if (boardstate[this.piecePosition.x][this.piecePosition.y].getColor() == currentPlayer) {
            // Look up .. (left)
            for (int y = this.piecePosition.y - 1, x = this.piecePosition.x - 1; y >= 0 && x >= 0; y--, x--) {
                boolean clearb = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = -1;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearb && y != -1 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look up .. (right)
            for (int y = this.piecePosition.y - 1, x = this.piecePosition.x + 1; y >= 0 && x < 8; y--, x++) {
                boolean clearb = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = -1;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearb && x != 8 && y != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look down .. (left)
            for (int y = this.piecePosition.y + 1, x = this.piecePosition.x - 1; y < 8 && x >= 0; y++, x--) {
                boolean clearb = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = 8;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearb && y != 8 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look down .. (right)
            for (int y = this.piecePosition.y + 1, x = this.piecePosition.x + 1; y < 8 && x < 8; y++, x++) {
                boolean clearb = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearb = false;
                    y = 8;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearb && x != 8 && y != 8) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
