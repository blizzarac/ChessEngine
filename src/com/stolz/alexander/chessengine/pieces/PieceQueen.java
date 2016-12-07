package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PieceQueen extends Piece {
	private Piece[][] boardstate;
	private int te;

	public PieceQueen(PieceColor type, int ii, int jj) {
		super(type);
        name = "Queen";
        imgname = "queen.png";
		this.type = type;
		i = ii;
		j = jj;
	}
	
	@Override
	public Image image(){
	 if(type == PieceColor.WHITE)
	 return new Image("file:resource/whitequeencursor.png");
	 else
	 return new Image("file:resource/blackqueencursor.png");
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
	public Piece[][] move(Piece q, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceQueen(q.type(), t.icoord(), t.jcoord());
		boardstate[q.icoord()][q.jcoord()] = new Empty(PieceColor.NONE, q.icoord(), q.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {

    }
}
