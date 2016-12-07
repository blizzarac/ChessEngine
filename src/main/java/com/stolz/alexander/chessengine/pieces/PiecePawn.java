package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.pieces.PieceColor.NONE;
import static com.stolz.alexander.chessengine.pieces.PieceColor.WHITE;

public class PiecePawn extends Piece {
	boolean firstmove;
	private Piece[][] boardstate;

	public PiecePawn(PieceColor type, int ii, int jj, boolean fm) {
		super(type);
        name = "Pawn";
        imgname = "pawn.png";
		this.color = type;
		i = ii;
		j = jj;
		firstmove = fm;
	}
	
	@Override
	public Image image(){
	 if(color == PieceColor.WHITE)
	 return new Image("whitepawncursor.png");
	 else
	 return new Image("blackpawncursor.png");
	}
	
	@Override
	public int icoord(){
		if(i > 8){
		return 7;}
		
		if(i < 0){
		return 0;}
		
		else{return i;}
	}
	
	@Override
	public int jcoord(){
		if(j > 8){
		return 7;}
		
		if(j < 0){
		return 0;}
		
		else{return j;}
	}
	
	@Override
	public boolean firstmove(){
		return firstmove;
	}

	@Override
	public Piece[][] move(Piece p, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		firstmove = false;
		boardstate[t.icoord()][t.jcoord()] = new PiecePawn(p.getColor(), t.icoord(), t.jcoord(), firstmove);
		boardstate[p.icoord()][p.jcoord()] = new Empty(NONE, p.icoord(), p.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
        blackPawns(this, pieces, board);
        whitePawns(this, pieces, board);
    }

    private void blackPawns(Piece p, Piece[][] pieces, Rectangle[][] board) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()+1 < 8){ // Guard for bounds
                if(pieces[p.icoord()][p.jcoord()+1].getColor() == NONE){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.icoord()][p.jcoord()+2].getColor() == NONE && pieces[p.icoord()][p.jcoord()+1].getColor() == NONE){
                    board[p.icoord()][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()-1][p.jcoord()+1].getColor() == WHITE){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].getColor() == WHITE){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}
    }

    private void whitePawns(Piece p, Piece[][] pieces, Rectangle[][] board) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()-1 >= 0){ // Guard for bounds
                if(pieces[p.icoord()][p.jcoord()-1].getColor() == NONE){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.icoord()][p.jcoord()-2].getColor() == NONE && pieces[p.icoord()][p.jcoord()-1].getColor() == NONE){
                    board[p.icoord()][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].getColor() == BLACK){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()+1][p.jcoord()-1].getColor() == BLACK){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}
        }
}
