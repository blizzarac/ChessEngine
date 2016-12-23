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
        for(int y = this.jcoord()-1; y >= 0; y--){
            if(pieces[this.icoord()][y].getColor() == NONE){
                validMoves.add(new PiecePosition(this.icoord(),y));
            }
            if(pieces[this.icoord()][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord(),y));
                // Stop looking
                y=-1;
            }
            if(y!=-1 && pieces[this.icoord()][y].getColor() == this.color){
                // Stop looking
                y=-1;
            }
        }

        // Look Right ..
        for(int x = this.icoord()+1; x < 8; x++){
            if(pieces[x][this.jcoord()].getColor() == NONE){
                validMoves.add(new PiecePosition(x, this.jcoord()));
            }
            if(pieces[x][this.jcoord()].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x, this.jcoord()));
                // Stop looking
                x=8;
            }
            if(x!=8 && pieces[x][this.jcoord()].getColor() == this.color){
                // Stop looking
                x=8;
            }
        }

        // Look Left ..
        for(int x = this.icoord()-1; x >= 0; x--){
            if(pieces[x][this.jcoord()].getColor() == NONE){
            }
            if(pieces[x][this.jcoord()].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(x, this.jcoord()));
                // Stop looking
                x=-1;
            }
            if(x!=-1 && pieces[x][this.jcoord()].getColor() == this.color){
                // Stop looking
                x=-1;
            }
        }

        // Look Down ..
        for(int y = this.jcoord()+1; y < 8; y++){
            if(pieces[this.icoord()][y].getColor() == NONE){
                validMoves.add(new PiecePosition(this.icoord(), y));
            }
            if(pieces[this.icoord()][y].getColor()== this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord(), y));
                // Stop looking
                y=8;
            }
            if(y!=8 && pieces[this.icoord()][y].getColor() == this.color){
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
                possiblemoves[move.x][move.y] = new PieceRook(color, move.x, move.y)
        );

        return possiblemoves;
    }
}
