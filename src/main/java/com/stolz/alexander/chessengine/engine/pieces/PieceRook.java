package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;

public class PieceRook extends Piece {

    public PieceRook(PieceColor color, int ii, int jj) {
		super(PieceType.ROOK, color, ii, jj);
	}

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
        // Look Up ..
        for(int y = this.y()-1; y >= 0; y--){
            if(pieces[this.x()][y].getColor() == NONE){
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
            if(pieces[x][this.y()].getColor() == NONE){
                validMoves.add(new PiecePosition(x, this.y()));
            }
            if(pieces[x][this.y()].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x, this.y()));
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
            if(pieces[x][this.y()].getColor() == NONE){
            }
            if(pieces[x][this.y()].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x, this.y()));
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
            if(pieces[this.x()][y].getColor() == NONE){
                validMoves.add(new PiecePosition(this.x(), y));
            }
            if(pieces[this.x()][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(this.x(), y));
                // Stop looking
                y=8;
            }
            if(y!=8 && pieces[this.x()][y].getColor() == this.color){
                // Stop looking
                y=8;
            }
        }

        return validMoves;
    }

    @Override
    public PieceRook copy() {
        return new PieceRook(this.getColor(), this.x(), this.y());
    }
}
