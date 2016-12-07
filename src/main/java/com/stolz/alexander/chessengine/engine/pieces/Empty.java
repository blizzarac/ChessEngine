package com.stolz.alexander.chessengine.engine.pieces;

import javafx.scene.shape.Rectangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Empty extends Piece {

	public Empty(PieceColor type, int ii, int jj) {
		super(type);
		i = ii;
		j = jj;
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
	public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
		throw new NotImplementedException();
	}
}
