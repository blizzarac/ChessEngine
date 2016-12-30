package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

public class PieceKnight extends Piece {

    public PieceKnight(PieceColor color, int ii, int jj) {
		super(PieceType.KNIGHT, color, ii, jj);
	}

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // Up and left (first)
            if(this.x()-1 >= 0 && this.y()-2 >= 0){				// Bound check
                if(pieces[this.x()-1][this.y()-2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()-1,this.y()-2));
                }
                if(pieces[this.x()-1][this.y()-2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()-1,this.y()-2));
                }
            }

            // Up and left (second)
            if(this.x()-2 >= 0 && this.y()-1 >= 0){				// Bound check
                if(pieces[this.x()-2][this.y()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()-2,this.y()-1));
                }
                if(pieces[this.x()-2][this.y()-1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()-2,this.y()-1));
                }
            }

            // Up and right (first)
            if(this.x()+1 < 8 && this.y()-2 >= 0){				// Bound check
                if(pieces[this.x()+1][this.y()-2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()+1,this.y()-2));
                }
                if(pieces[this.x()+1][this.y()-2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()+1,this.y()-2));
                }
            }

            // Up and right (second)
            if(this.x()+2 < 8 && this.y()-1 >= 0){				// Bound check
                if(pieces[this.x()+2][this.y()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()+2,this.y()-1));
                }
                if(pieces[this.x()+2][this.y()-1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()+2,this.y()-1));
                }
            }

            // Bottom and right (first)
            if(this.x()+1 < 8 && this.y()+2 < 8){				// Bound check
                if(pieces[this.x()+1][this.y()+2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()+1,this.y()+2));
                }
                if(pieces[this.x()+1][this.y()+2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()+1,this.y()+2));
                }
            }

            // Bottom and right (second)
            if(this.x()+2 < 8 && this.y()+1 < 8){				// Bound check
                if(pieces[this.x()+2][this.y()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()+2,this.y()+1));
                }
                if(pieces[this.x()+2][this.y()+1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()+2,this.y()+1));
                }
            }

            // Bottom and left (first)
            if(this.x()-1 >= 0 && this.y()+2 < 8){				// Bound check
                if(pieces[this.x()-1][this.y()+2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()-1,this.y()+2));
                }
                if(pieces[this.x()-1][this.y()+2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()-1,this.y()+2));
                }
            }

            // Bottom and left (second)
            if(this.x()-2 >= 0 && this.y()+1 < 8){				// Bound check
                if(pieces[this.x()-2][this.y()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x()-2,this.y()+1));
                }
                if(pieces[this.x()-2][this.y()+1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.x()-2,this.y()+1));
                }
            }

        return validMoves;
    }

    @Override
    public PieceKnight copy() {
        return new PieceKnight(this.getColor(), this.x(), this.y());
    }
}
