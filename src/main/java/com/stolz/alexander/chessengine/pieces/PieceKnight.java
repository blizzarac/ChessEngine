package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.pieces.PieceColor.NONE;

public class PieceKnight extends Piece{
	private Piece[][] boardstate;

	public PieceKnight(PieceColor type, int ii, int jj) {
		super(type);
        name = "Knight";
        imgname = "knight.png";
		this.color = type;
        this.i = ii;
        this.j = jj;
	}
	
	@Override
	public Image image(){
	 if(color == PieceColor.WHITE)
	 return new Image("whiteknightcursor.png");
	 else
	 return new Image("blackknightcursor.png");
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
		boardstate[t.icoord()][t.jcoord()] = new PieceKnight(kn.getColor(), t.icoord(), t.jcoord());
		boardstate[kn.icoord()][kn.jcoord()] = new Empty(NONE, kn.icoord(), kn.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
            // Up and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()-2 >= 0){				// Bound check
                if(pieces[this.icoord()-1][this.jcoord()-2].getColor() == NONE){
                    board[this.icoord()-1][this.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()-1][this.jcoord()-2].getColor()==this.color.mirror()){
                    board[this.icoord()-1][this.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()-1 >= 0){				// Bound check
                if(pieces[this.icoord()-2][this.jcoord()-1].getColor() == NONE){
                    board[this.icoord()-2][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()-2][this.jcoord()-1].getColor()==this.color.mirror()){
                    board[this.icoord()-2][this.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()-2 >= 0){				// Bound check
                if(pieces[this.icoord()+1][this.jcoord()-2].getColor() == NONE){
                    board[this.icoord()+1][this.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()+1][this.jcoord()-2].getColor()==this.color.mirror()){
                    board[this.icoord()+1][this.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()-1 >= 0){				// Bound check
                if(pieces[this.icoord()+2][this.jcoord()-1].getColor() == NONE){
                    board[this.icoord()+2][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()+2][this.jcoord()-1].getColor()==this.color.mirror()){
                    board[this.icoord()+2][this.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()+2 < 8){				// Bound check
                if(pieces[this.icoord()+1][this.jcoord()+2].getColor() == NONE){
                    board[this.icoord()+1][this.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()+1][this.jcoord()+2].getColor()==this.color.mirror()){
                    board[this.icoord()+1][this.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()+1 < 8){				// Bound check
                if(pieces[this.icoord()+2][this.jcoord()+1].getColor() == NONE){
                    board[this.icoord()+2][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()+2][this.jcoord()+1].getColor()==this.color.mirror()){
                    board[this.icoord()+2][this.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()+2 < 8){				// Bound check
                if(pieces[this.icoord()-1][this.jcoord()+2].getColor() == NONE){
                    board[this.icoord()-1][this.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()-1][this.jcoord()+2].getColor()==this.color.mirror()){
                    board[this.icoord()-1][this.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()+1 < 8){				// Bound check
                if(pieces[this.icoord()-2][this.jcoord()+1].getColor() == NONE){
                    board[this.icoord()-2][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[this.icoord()-2][this.jcoord()+1].getColor()==this.color.mirror()){
                    board[this.icoord()-2][this.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
    }
}
