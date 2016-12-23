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
            if(this.icoord()-1 >= 0 && this.jcoord()-2 >= 0){				// Bound check
                if(pieces[this.icoord()-1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-2));
                }
                if(pieces[this.icoord()-1][this.jcoord()-2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-2));
                }
            }

            // Up and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()-1 >= 0){				// Bound check
                if(pieces[this.icoord()-2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()-1));
                }
                if(pieces[this.icoord()-2][this.jcoord()-1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()-1));
                }
            }

            // Up and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()-2 >= 0){				// Bound check
                if(pieces[this.icoord()+1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-2));
                }
                if(pieces[this.icoord()+1][this.jcoord()-2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-2));
                }
            }

            // Up and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()-1 >= 0){				// Bound check
                if(pieces[this.icoord()+2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()-1));
                }
                if(pieces[this.icoord()+2][this.jcoord()-1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()-1));
                }
            }

            // Bottom and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()+2 < 8){				// Bound check
                if(pieces[this.icoord()+1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+2));
                }
                if(pieces[this.icoord()+1][this.jcoord()+2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+2));
                }
            }

            // Bottom and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()+1 < 8){				// Bound check
                if(pieces[this.icoord()+2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()+1));
                }
                if(pieces[this.icoord()+2][this.jcoord()+1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()+1));
                }
            }

            // Bottom and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()+2 < 8){				// Bound check
                if(pieces[this.icoord()-1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+2));
                }
                if(pieces[this.icoord()-1][this.jcoord()+2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+2));
                }
            }

            // Bottom and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()+1 < 8){				// Bound check
                if(pieces[this.icoord()-2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()+1));
                }
                if(pieces[this.icoord()-2][this.jcoord()+1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()+1));
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
                possiblemoves[move.x][move.y] = new PieceKnight(color, move.x, move.y)
        );

        return possiblemoves;
	}
}