package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PieceKnight extends Piece{
	private Piece[][] boardstate;

	public PieceKnight(PieceColor type, int ii, int jj) {
		super(type);
        name = "Knight";
        imgname = "knight.png";
		this.type = type;
        this.i = ii;
        this.j = jj;
	}
	
	@Override
	public Image image(){
	 if(type == PieceColor.WHITE)
	 return new Image("file:resource/whiteknightcursor.png");
	 else
	 return new Image("file:resource/blackknightcursor.png");
	}
	
	@Override
	public int icoord(){
		// Bounds correction
		if(i > 8){
		return 7;}
		
		if(i < 0){
		return 0;}
		
		else{return i;}
	}
	
	@Override
	public int jcoord(){
		// Bounds correction
		if(j > 8){
		return 7;}
		
		if(j < 0){
		return 0;}
		
		else{return j;}
	}

	@Override
	public Piece[][] move(Piece kn, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceKnight(kn.type(), t.icoord(), t.jcoord());
		boardstate[kn.icoord()][kn.jcoord()] = new Empty(PieceColor.NONE, kn.icoord(), kn.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {

    }
}
