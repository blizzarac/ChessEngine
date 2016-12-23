package com.stolz.alexander.chessengine.engine.pieces;

import java.util.ArrayList;
import java.util.List;

public class PiecePawn extends Piece {
	boolean firstmove;

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
            if(p.jcoord()+1 < 8){ // Guard for bounds
                if(pieces[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(),p.jcoord()+1));
                }}

            if(p.firstmove()){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.icoord()][p.jcoord()+2].getColor() == PieceColor.NONE
                        && pieces[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(),p.jcoord()+2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()-1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(p.icoord()-1,p.jcoord()+1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(p.icoord()+1, p.jcoord()+1));
                }}

        return validMoves;
    }

    private List<PiecePosition> drawWhiteMoves(Piece p, Piece[][] pieces) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()-1 >= 0){ // Guard for bounds
                if(pieces[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(),p.jcoord()-1));
                }}

            if(p.firstmove()){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.icoord()][p.jcoord()-2].getColor() == PieceColor.NONE
                        && pieces[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(), p.jcoord()-2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(p.icoord()-1, p.jcoord()-1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()+1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(p.icoord()+1, p.jcoord()-1));
                }}

            return validMoves;
        }

    @Override
    public Piece[][] findPossibleMoves(Piece[][] pieces) {
        Piece[][] possiblemoves = new Piece[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieces[x], 0, possiblemoves[x], 0, 8);
        }

        findValidMoves(pieces).stream().forEach(move ->
                possiblemoves[move.x][move.y] = new PiecePawn(color, move.x, move.y, false)
        );

        return possiblemoves;
    }
}
