package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PieceKing extends Piece {
	private Piece[][] boardstate;

	public PieceKing(PieceColor type, int ii, int jj) {
		super(type);
        name = "King";
        imgname = "king.png";
		this.type=type;
		i = ii;
		j = jj;
	}
	
	@Override
	public Image image(){
	 if(type == PieceColor.WHITE)
	 return new Image("file:resource/whitekingcursor.png");
	 else
	 return new Image("file:resource/blackkingcursor.png");
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
	public Piece[][] move(Piece k, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceKing(k.type(), t.icoord(), t.jcoord());
		boardstate[k.icoord()][k.jcoord()] = new Empty(PieceColor.NONE, k.icoord(), k.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {

    }
}
