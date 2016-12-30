package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

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
}
