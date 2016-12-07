package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewPawn extends PieceView {
	boolean firstmove;
	private PieceView[][] boardstate;

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
		boardstate = bs;
		// Move pawn
		firstmove = false;
		boardstate[t.icoord()][t.jcoord()] = new PieceViewPawn(p.getColor(), t.icoord(), t.jcoord(), firstmove);
		boardstate[p.icoord()][p.jcoord()] = new Empty(p.icoord(), p.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
		if (color == PieceColor.BLACK)
            blackPawns(this, pieceViews, board);
		else
            whitePawns(this, pieceViews, board);
    }

    private void blackPawns(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()+1 < 8){ // Guard for bounds
                if(pieceViews[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieceViews[p.icoord()][p.jcoord()+2].getColor() == PieceColor.NONE && pieceViews[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()+1 < 8){
                if(pieceViews[p.icoord()-1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieceViews[p.icoord()+1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}
    }

    private void whitePawns(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()-1 >= 0){ // Guard for bounds
                if(pieceViews[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieceViews[p.icoord()][p.jcoord()-2].getColor() == PieceColor.NONE && pieceViews[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()-1 >= 0){
                if(pieceViews[p.icoord()-1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieceViews[p.icoord()+1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}
        }
}
