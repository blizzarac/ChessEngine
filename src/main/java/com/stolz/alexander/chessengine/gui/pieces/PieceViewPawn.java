package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PieceViewPawn extends PieceView {
	boolean firstmove;

    public PieceViewPawn(PieceColor color, int ii, int jj, boolean fm) {
		super(PieceType.PAWN, color, ii, jj);
        imgname = "pawn.png";
		firstmove = fm;
        switch (color){
            case WHITE:
                image = new Image("whitepawn.png");
                break;
            case BLACK:
                image = new Image("blackpawn.png");
                break;
        }
	}
	
	@Override
	public boolean firstmove(){
		return firstmove;
	}

	@Override
	public PieceView[][] move(PieceView p, PieceView t, PieceView[][] bs){
		firstmove = false;
		bs[t.icoord()][t.jcoord()] = new PieceViewPawn(p.getColor(), t.icoord(), t.jcoord(), firstmove);
		bs[p.icoord()][p.jcoord()] = new Empty(p.icoord(), p.jcoord());

		return bs;
	}

    @Override
    public List<PiecePosition> findValidMoves(PieceView[][] pieceViews) {
        List<PiecePosition> validMoves = new ArrayList<>();
		if (color == PieceColor.BLACK)
            validMoves.addAll(drawBlackMoves(this, pieceViews));
		else
            validMoves.addAll(drawWhiteMoves(this, pieceViews));

        return validMoves;
    }

    private List<PiecePosition>  drawBlackMoves(PieceView p, PieceView[][] pieceViews) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()+1 < 8){ // Guard for bounds
                if(pieceViews[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(),p.jcoord()+1));
                }}

            if(p.firstmove()){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieceViews[p.icoord()][p.jcoord()+2].getColor() == PieceColor.NONE
                        && pieceViews[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(),p.jcoord()+2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()+1 < 8){
                if(pieceViews[p.icoord()-1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(p.icoord()-1,p.jcoord()+1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieceViews[p.icoord()+1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    validMoves.add(new PiecePosition(p.icoord()+1, p.jcoord()+1));
                }}

        return validMoves;
    }

    private List<PiecePosition> drawWhiteMoves(PieceView p, PieceView[][] pieceViews) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()-1 >= 0){ // Guard for bounds
                if(pieceViews[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(),p.jcoord()-1));
                }}

            if(p.firstmove()){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieceViews[p.icoord()][p.jcoord()-2].getColor() == PieceColor.NONE
                        && pieceViews[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(p.icoord(), p.jcoord()-2));
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()-1 >= 0){
                if(pieceViews[p.icoord()-1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(p.icoord()-1, p.jcoord()-1));
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieceViews[p.icoord()+1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    validMoves.add(new PiecePosition(p.icoord()+1, p.jcoord()-1));
                }}

            return validMoves;
        }

    @Override
    public PieceView[][] findPossibleMoves(PieceView[][] pieceViews) {
        PieceView[][] possiblemoves = new PieceView[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieceViews[x], 0, possiblemoves[x], 0, 8);
        }

        findValidMoves(pieceViews).stream().forEach( move ->
                possiblemoves[move.i][move.j] = new PieceViewPawn(color, move.i, move.j, false)
        );

        return possiblemoves;
    }
}
