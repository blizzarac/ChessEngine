package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PieceRook extends Piece {
	private Piece[][] boardstate;

	public PieceRook(PieceColor type, int ii, int jj) {
		super(type);
        name = "Rook";
        imgname = "rook.png";
		this.type = type;
		i = ii;
		j = jj;
	}
	
	@Override
	public Image image(){
	 if(type == PieceColor.WHITE)
	 return new Image("file:resource/whiterookcursor.png");
	 else
	 return new Image("file:resource/blackrookcursor.png");
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
	public Piece[][] move(Piece r, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceRook(r.type(), t.icoord(), t.jcoord());
		boardstate[r.icoord()][r.jcoord()] = new Empty(PieceColor.NONE, r.icoord(), r.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {

    }
}
