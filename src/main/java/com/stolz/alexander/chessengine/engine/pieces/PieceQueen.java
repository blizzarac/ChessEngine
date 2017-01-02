package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.KING;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.NOTYPE;

public class PieceQueen extends Piece {

	public PieceQueen(PieceColor color, int ii, int jj) {
		super(PieceType.QUEEN, color, ii, jj);
	}

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
	    List<PiecePosition> validMoves = new ArrayList<>();

        // Look up .. (left)
        for(int y = this.y()-1, x = this.x()-1; y >= 0 && x >= 0; y--,x--){
            if(pieces[x][y].getType() == NOTYPE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=-1;y=-1;
            }
            if(x!=-1 && y!=-1 && pieces[x][y].getColor() == this.color){
                // Stop Looking
                x=-1;y=-1;
            }
        }

        // Look up .. (right)
        for(int y = this.y()-1, x = this.x()+1; y >= 0 && x < 8; y--,x++){
            if(pieces[x][y].getType() == NOTYPE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=8;y=-1;
            }
            if(x!=8 && y!=-1 && pieces[x][y].getColor() == this.color){
                // Stop Looking
                x=8;y=-1;
            }
        }

        // Look down .. (left)
        for(int y = this.y()+1, x = this.x()-1; y < 8 && x >= 0; y++,x--){
            if(pieces[x][y].getType() == NOTYPE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=-1;y=8;
            }
            if(x!=-1 && y!=8 && pieces[x][y].getColor() == this.color){
                // Stop Looking
                x=-1;y=8;
            }
        }

        // Look down .. (right)
        for(int y = this.y()+1, x = this.x()+1; y < 8 && x < 8; y++,x++){
            if(pieces[x][y].getType() == NOTYPE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieces[x][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=8;y=8;
            }
            if(x!=8 && y!=8 && pieces[x][y].getColor() == this.color){
                // Stop Looking
                x=8;y=8;
            }
        }

        // Look Up ..
        for(int y = this.y()-1; y >= 0; y--){
            if(pieces[this.x()][y].getType() == NOTYPE){
                validMoves.add(new PiecePosition(this.x(),y));
            }
            if(pieces[this.x()][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(this.x(),y));
                // Stop looking
                y=-1;
            }
            if(y!=-1 && pieces[this.x()][y].getColor() == this.color){
                // Stop looking
                y=-1;
            }
        }

        // Look Right ..
        for(int x = this.x()+1; x < 8; x++){
            if(pieces[x][this.y()].getType() == NOTYPE){
                validMoves.add(new PiecePosition(x,this.y()));
            }
            if(pieces[x][this.y()].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x,this.y()));
                // Stop looking
                x=8;
            }
            if(x!=8 && pieces[x][this.y()].getColor() == this.color){
                // Stop looking
                x=8;
            }
        }

        // Look Left ..
        for(int x = this.x()-1; x >= 0; x--){
            if(pieces[x][this.y()].getType() == NOTYPE){
                validMoves.add(new PiecePosition(x,this.y()));
            }
            if(pieces[x][this.y()].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x,this.y()));
                // Stop looking
                x=-1;
            }
            if(x!=-1 && pieces[x][this.y()].getColor() == this.color){
                // Stop looking
                x=-1;
            }
        }

        // Look Down ..
        for(int y = this.y()+1; y < 8; y++){
            if(pieces[this.x()][y].getType() == NOTYPE){
                validMoves.add(new PiecePosition(this.x(),y));
            }
            if(pieces[this.x()][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(this.x(),y));
                // Stop looking
                y=8;
            }
            if(y!=8 && pieces[this.x()][y].getColor() == this.color && y!=8){
                // Stop looking
                y=8;
            }
        }

        return validMoves;
    }

    @Override
    public PieceQueen copy() {
        return new PieceQueen(this.getColor(), this.x(), this.y());
    }

    @Override
    public boolean isCheck(Piece[][] boardstate, PieceColor currentPlayer) {
        if (boardstate[this.piecePosition.x][this.piecePosition.y].getColor() == currentPlayer) {
            // Look Up ..
            for (int y = this.piecePosition.y - 1; y >= 0; y--) {
                boolean clearq = true;
                if (!(boardstate[this.piecePosition.x][y].getType() == NOTYPE) && !(boardstate[this.piecePosition.x][y].getType() == KING)) {
                    clearq = false;
                    y = -1;
                }
                // If path is clear, check for king ..
                if (clearq && y != -1) {
                    if (boardstate[this.piecePosition.x][y].getType() == KING && boardstate[this.piecePosition.x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look Right ..
            for (int x = this.piecePosition.x + 1; x < 8; x++) {
                boolean clearq = true;
                if (!(boardstate[x][this.piecePosition.y].getType() == NOTYPE) && !(boardstate[x][this.piecePosition.y].getType() == KING)) {
                    clearq = false;
                    x = 8;
                }
                // If path is clear, check for king ..
                if (clearq && x != 8) {
                    if (boardstate[x][this.piecePosition.y].getType() == KING && boardstate[x][this.piecePosition.y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look Left ..
            for (int x = this.piecePosition.x - 1; x >= 0; x--) {
                boolean clearq = true;
                if (!(boardstate[x][this.piecePosition.y].getType() == NOTYPE) && !(boardstate[x][this.piecePosition.y].getType() == KING)) {
                    clearq = false;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearq && x != -1) {
                    if (boardstate[x][this.piecePosition.y].getType() == KING && boardstate[x][this.piecePosition.y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look Down ..
            for (int y = this.piecePosition.y + 1; y < 8; y++) {
                boolean clearq = true;
                if (!(boardstate[this.piecePosition.x][y].getType() == NOTYPE) && !(boardstate[this.piecePosition.x][y].getType() == KING)) {
                    clearq = false;
                    y = 8;
                }
                // If path is clear, check for king ..
                if (clearq && y != 8) {
                    if (boardstate[this.piecePosition.x][y].getType() == KING && boardstate[this.piecePosition.x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look up .. (left)
            for (int y = this.piecePosition.y - 1, x = this.piecePosition.x - 1; y >= 0 && x >= 0; y--, x--) {
                boolean clearq = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = -1;
                    x = -1;
                }
                // If path is clear, check for king ..
                if (clearq && y != -1 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look up .. (right)
            for (int y = this.piecePosition.y - 1, x = this.piecePosition.x + 1; y >= 0 && x < 8; y--, x++) {
                boolean clearq = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = -1;
                }
                // If path is clear, check for king ..
                if (clearq && y != -1 && x != 8) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look down .. (left)
            for (int y = this.piecePosition.y + 1, x = this.piecePosition.x - 1; y < 8 && x >= 0; y++, x--) {
                boolean clearq = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = 8;
                }
                // If path is clear, check for king ..
                if (clearq && y != 8 && x != -1) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }

            // Look down .. (right)
            for (int y = this.piecePosition.y + 1, x = this.piecePosition.x + 1; y < 8 && x < 8; y++, x++) {
                boolean clearq = true;
                if (!(boardstate[x][y].getType() == NOTYPE) && !(boardstate[x][y].getType() == KING)) {
                    clearq = false;
                    y = 8;
                }
                // If path is clear, check for king ..
                if (clearq && y != 8 && x != 8) {
                    if (boardstate[x][y].getType() == KING && boardstate[x][y].getColor() == currentPlayer.mirror()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
