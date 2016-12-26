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
        if(this.y()-1 >= 0){
            if(pieces[this.x()][this.y()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x(),this.y()-1));
            }
            if(pieces[this.x()][this.y()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x(),this.y()-1));
            }
        }

        // Up - right
        if(this.y()-1 >= 0 && this.x()+1 < 8){
            if(pieces[this.x()+1][this.y()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x()+1,this.y()-1));
            }
            if(pieces[this.x()+1][this.y()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x()+1,this.y()-1));
            }
        }

        // Up - left
        if(this.y()-1 >= 0 && this.x()-1 >= 0){
            if(pieces[this.x()-1][this.y()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x()-1,this.y()-1));
            }
            if(pieces[this.x()-1][this.y()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x()-1,this.y()-1));
            }
        }

        // Left
        if(this.x()-1 >= 0){
            if(pieces[this.x()-1][this.y()].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x()-1,this.y()));
            }
            if(pieces[this.x()-1][this.y()].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x()-1,this.y()));
            }
        }

        // Right
        if(this.x()+1 < 8){
            if(pieces[this.x()+1][this.y()].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x()+1,this.y()));
            }
            if(pieces[this.x()+1][this.y()].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x()+1,this.y()));
            }
        }

        // Down
        if(this.y()+1 < 8){
            if(pieces[this.x()][this.y()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x(),this.y()+1));
            }
            if(pieces[this.x()][this.y()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x(),this.y()+1));
            }
        }

        // Down - left
        if(this.y()+1 < 8 && this.x()-1 >= 0){
            if(pieces[this.x()-1][this.y()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x()-1,this.y()+1));
            }
            if(pieces[this.x()-1][this.y()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x()-1,this.y()+1));
            }
        }

        // Down - right
        if(this.y()+1 < 8 && this.x()+1 < 8){
            if(pieces[this.x()+1][this.y()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.x()+1,this.y()+1));
            }
            if(pieces[this.x()+1][this.y()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.x()+1,this.y()+1));
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
