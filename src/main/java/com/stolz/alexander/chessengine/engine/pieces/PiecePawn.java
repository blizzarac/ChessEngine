package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.KING;

public class PiecePawn extends Piece implements WithFirstmove{
	private boolean firstmove;

    public PiecePawn(PieceColor color, int ii, int jj, boolean fm) {
		super(PieceType.PAWN, color, ii, jj);
		firstmove = fm;
	}

	@Override
	public boolean isFirstmove(){
		return firstmove;
	}

	@Override
    public void setFirstmove(boolean firstmove) {
	    this.firstmove = firstmove;
    }

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
		if (color == PieceColor.BLACK)
            validMoves.addAll(drawBlackMoves(pieces));
		else
            validMoves.addAll(drawWhiteMoves(pieces));

        return validMoves;
    }

    private List<PiecePosition>  drawBlackMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(this.y()+1 < 8){ // Guard for bounds
                if(pieces[this.x()][this.y()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x(),this.y()+1));
                }}

            if(firstmove){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[this.x()][this.y()+2].getColor() == PieceColor.NONE
                        && pieces[this.x()][this.y()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x(),this.y()+2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(this.x()-1 >= 0 && this.y()+1 < 8){
                if(pieces[this.x()-1][this.y()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(this.x()-1,this.y()+1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(this.x()+1 < 8 && this.y()+1 < 8){
                if(pieces[this.x()+1][this.y()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(this.x()+1, this.y()+1));
                }}

        return validMoves;
    }

    private List<PiecePosition> drawWhiteMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(this.y()-1 >= 0){ // Guard for bounds
                if(pieces[this.x()][this.y()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x(),this.y()-1));
                }}

            if(firstmove){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[this.x()][this.y()-2].getColor() == PieceColor.NONE
                        && pieces[this.x()][this.y()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.x(), this.y()-2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(this.x()-1 >= 0 && this.y()-1 >= 0){
                if(pieces[this.x()-1][this.y()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(this.x()-1, this.y()-1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(this.x()+1 < 8 && this.y()-1 >= 0){
                if(pieces[this.x()+1][this.y()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(this.x()+1, this.y()-1));
                }}

            return validMoves;
        }

    @Override
    public PiecePawn copy() {
        return new PiecePawn(this.getColor(), this.x(), this.y(), false);
    }

    @Override
    public boolean isCheck(Piece[][] boardstate, PieceColor currentPlayer) {
	    final int direction = (currentPlayer == PieceColor.WHITE)? 1: -1;

        // LOOK ONE SQUARE LEFT DIAGONALLY IF KING PRESENT HIGHLIGHT
        if (this.piecePosition.x > 0 && boardstate[this.piecePosition.x - 1][this.piecePosition.y - direction].getType() == KING
                && boardstate[this.piecePosition.x - 1][this.piecePosition.y - direction].getColor() == this.color.mirror()) {
            return true;
        }

        // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
        if (this.piecePosition.x < 7 &&boardstate[this.piecePosition.x + 1][this.piecePosition.y - direction].getType() == KING
                && boardstate[this.piecePosition.x + 1][this.piecePosition.y - direction].getColor() == this.color.mirror()) {
            return true;
        }

        return false;
    }
}
