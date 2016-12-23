package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

public class PieceKing extends Piece {

    public PieceKing(PieceColor color, int ii, int jj) {
		super(PieceType.KING, color, ii, jj);
	}

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();

        // Up
        if(this.jcoord()-1 >= 0){
            if(pieces[this.icoord()][this.jcoord()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()-1));
            }
            if(pieces[this.icoord()][this.jcoord()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()-1));
            }
        }

        // Up - right
        if(this.jcoord()-1 >= 0 && this.icoord()+1 < 8){
            if(pieces[this.icoord()+1][this.jcoord()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-1));
            }
            if(pieces[this.icoord()+1][this.jcoord()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-1));
            }
        }

        // Up - left
        if(this.jcoord()-1 >= 0 && this.icoord()-1 >= 0){
            if(pieces[this.icoord()-1][this.jcoord()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-1));
            }
            if(pieces[this.icoord()-1][this.jcoord()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-1));
            }
        }

        // Left
        if(this.icoord()-1 >= 0){
            if(pieces[this.icoord()-1][this.jcoord()].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()));
            }
            if(pieces[this.icoord()-1][this.jcoord()].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()));
            }
        }

        // Right
        if(this.icoord()+1 < 8){
            if(pieces[this.icoord()+1][this.jcoord()].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()));
            }
            if(pieces[this.icoord()+1][this.jcoord()].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()));
            }
        }

        // Down
        if(this.jcoord()+1 < 8){
            if(pieces[this.icoord()][this.jcoord()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()+1));
            }
            if(pieces[this.icoord()][this.jcoord()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()+1));
            }
        }

        // Down - left
        if(this.jcoord()+1 < 8 && this.icoord()-1 >= 0){
            if(pieces[this.icoord()-1][this.jcoord()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+1));
            }
            if(pieces[this.icoord()-1][this.jcoord()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+1));
            }
        }

        // Down - right
        if(this.jcoord()+1 < 8 && this.icoord()+1 < 8){
            if(pieces[this.icoord()+1][this.jcoord()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+1));
            }
            if(pieces[this.icoord()+1][this.jcoord()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+1));
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
                possiblemoves[move.x][move.y] = new PieceKing(color, move.x, move.y)
        );

        return possiblemoves;
    }
}
