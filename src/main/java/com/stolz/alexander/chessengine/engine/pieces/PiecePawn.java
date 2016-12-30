package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

public class PiecePawn extends Piece {
	private boolean firstmove;

    public PiecePawn(PieceColor color, int ii, int jj, boolean fm) {
		super(PieceType.PAWN, color, ii, jj);
		firstmove = fm;
	}

	@Override
	public boolean firstmove(){
		return firstmove;
	}

    @Override
    public List<PiecePosition> findValidMoves(Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
		if (color == PieceColor.BLACK)
            validMoves.addAll(drawBlackMoves(this, pieces));
		else
            validMoves.addAll(drawWhiteMoves(this, pieces));

        return validMoves;
    }

    private List<PiecePosition>  drawBlackMoves(Piece p, Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.y()+1 < 8){ // Guard for bounds
                if(pieces[p.x()][p.y()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.x(),p.y()+1));
                }}

            if(p.firstmove()){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.x()][p.y()+2].getColor() == PieceColor.NONE
                        && pieces[p.x()][p.y()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.x(),p.y()+2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.x()-1 >= 0 && p.y()+1 < 8){
                if(pieces[p.x()-1][p.y()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(p.x()-1,p.y()+1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.x()+1 < 8 && p.y()+1 < 8){
                if(pieces[p.x()+1][p.y()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(p.x()+1, p.y()+1));
                }}

        return validMoves;
    }

    private List<PiecePosition> drawWhiteMoves(Piece p, Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.y()-1 >= 0){ // Guard for bounds
                if(pieces[p.x()][p.y()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.x(),p.y()-1));
                }}

            if(p.firstmove()){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.x()][p.y()-2].getColor() == PieceColor.NONE
                        && pieces[p.x()][p.y()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.x(), p.y()-2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.x()-1 >= 0 && p.y()-1 >= 0){
                if(pieces[p.x()-1][p.y()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(p.x()-1, p.y()-1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.x()+1 < 8 && p.y()-1 >= 0){
                if(pieces[p.x()+1][p.y()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(p.x()+1, p.y()-1));
                }}

            return validMoves;
        }

    @Override
    public PiecePawn copy() {
        return new PiecePawn(this.getColor(), this.x(), this.y(), false);
    }
}
