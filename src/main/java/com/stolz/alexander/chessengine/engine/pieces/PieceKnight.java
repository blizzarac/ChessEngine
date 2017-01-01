package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.KING;

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

    @Override
    public boolean isCheck(Piece[][] boardstate, PieceColor currentPlayer) {
        if (boardstate[this.piecePosition.x][this.piecePosition.y].getColor() == currentPlayer) {
            // Up and left (first)
            if (this.piecePosition.x - 1 >= 0 && this.piecePosition.y - 2 >= 0) {                // Bound check
                if (boardstate[this.piecePosition.x - 1][this.piecePosition.y - 2].getType() == KING && boardstate[this.piecePosition.x - 1][this.piecePosition.y - 2].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Up and left (second)
            if (this.piecePosition.x - 2 >= 0 && this.piecePosition.y - 1 >= 0) {                // Bound check
                if (boardstate[this.piecePosition.x - 2][this.piecePosition.y - 1].getType() == KING && boardstate[this.piecePosition.x - 2][this.piecePosition.y - 1].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Up and right (first)
            if (this.piecePosition.x + 1 < 8 && this.piecePosition.y - 2 >= 0) {                // Bound check
                if (boardstate[this.piecePosition.x + 1][this.piecePosition.y - 2].getType() == KING && boardstate[this.piecePosition.x + 1][this.piecePosition.y - 2].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Up and right (second)
            if (this.piecePosition.x + 2 < 8 && this.piecePosition.y - 1 >= 0) {                // Bound check
                if (boardstate[this.piecePosition.x + 2][this.piecePosition.y - 1].getType() == KING && boardstate[this.piecePosition.x + 2][this.piecePosition.y - 1].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Bottom and right (first)
            if (this.piecePosition.x + 1 < 8 && this.piecePosition.y + 2 < 8) {                // Bound check
                if (boardstate[this.piecePosition.x + 1][this.piecePosition.y + 2].getType() == KING && boardstate[this.piecePosition.x + 1][this.piecePosition.y + 2].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Bottom and right (second)
            if (this.piecePosition.x + 2 < 8 && this.piecePosition.y + 1 < 8) {                // Bound check
                if (boardstate[this.piecePosition.x + 2][this.piecePosition.y + 1].getType() == KING && boardstate[this.piecePosition.x + 2][this.piecePosition.y + 1].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Bottom and left (first)
            if (this.piecePosition.x - 1 >= 0 && this.piecePosition.y + 2 < 8) {                // Bound check
                if (boardstate[this.piecePosition.x - 1][this.piecePosition.y + 2].getType() == KING && boardstate[this.piecePosition.x - 1][this.piecePosition.y + 2].getColor() == currentPlayer.mirror()) {
                    return true;
                }
            }

            // Bottom and left (second)
            if (this.piecePosition.x - 2 >= 0 && this.piecePosition.y + 1 < 8) {                // Bound check
                if (boardstate[this.piecePosition.x - 2][this.piecePosition.y + 1].getType() == KING && boardstate[this.piecePosition.x - 2][this.piecePosition.y + 1].getColor() == currentPlayer.mirror()) {
                   return true;
                }
            }
        }
        return false;
    }
}
