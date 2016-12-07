package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PiecePawn extends Piece {
	boolean firstmove;
	private Piece[][] boardstate;

	public PiecePawn(PieceColor type, int ii, int jj, boolean fm) {
		super(type);
        name = "Pawn";
        imgname = "pawn.png";
		this.type = type;
		i = ii;
		j = jj;
		firstmove = fm;
	}
	
	@Override
	public Image image(){
	 if(type == PieceColor.WHITE)
	 return new Image("file:resource/whitepawncursor.png");
	 else
	 return new Image("file:resource/blackpawncursor.png");
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
		boardstate[t.icoord()][t.jcoord()] = new PiecePawn(p.type(), t.icoord(), t.jcoord(), firstmove);
		boardstate[p.icoord()][p.jcoord()] = new Empty(PieceColor.NONE, p.icoord(), p.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {

    }
}
