package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

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
    public Piece[][] findPossibleMoves(Piece[][] pieces) {
        Piece[][] possiblemoves = new Piece[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieces[x], 0, possiblemoves[x], 0, 8);
        }

        final List<PiecePosition> validMoves = findValidMoves(pieces);
        validMoves.stream().forEach(move ->
                possiblemoves[move.x][move.y] = new PieceQueen(color, move.x, move.y)
        );

        return possiblemoves;
    }
}
