package com.stolz.alexander.chessengine.engine.pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;

public class PieceKing extends Piece {
	private Piece[][] boardstate;

	public PieceKing(PieceColor type, int ii, int jj) {
		super(type);
        imgname = "king.png";
		this.color =type;
		i = ii;
		j = jj;
	}
	
	@Override
	public Image image(){
	 if(color == PieceColor.WHITE)
	 return new Image("whitekingcursor.png");
	 else
	 return new Image("blackkingcursor.png");
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
		boardstate[t.icoord()][t.jcoord()] = new PieceKing(k.getColor(), t.icoord(), t.jcoord());
		boardstate[k.icoord()][k.jcoord()] = new Empty(NONE, k.icoord(), k.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
        // Up
        if(this.jcoord()-1 >= 0){
            if(pieces[this.icoord()][this.jcoord()-1].getColor() == NONE){
                board[this.icoord()][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Up - right
        if(this.jcoord()-1 >= 0 && this.icoord()+1 < 8){
            if(pieces[this.icoord()+1][this.jcoord()-1].getColor() == NONE){
                board[this.icoord()+1][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()+1][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Up - left
        if(this.jcoord()-1 >= 0 && this.icoord()-1 >= 0){
            if(pieces[this.icoord()-1][this.jcoord()-1].getColor() == NONE){
                board[this.icoord()-1][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()-1][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Left
        if(this.icoord()-1 >= 0){
            if(pieces[this.icoord()-1][this.jcoord()].getColor() == NONE){
                board[this.icoord()-1][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()-1][this.jcoord()].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()].setStroke(Color.AQUAMARINE);
            }
        }

        // Right
        if(this.icoord()+1 < 8){
            if(pieces[this.icoord()+1][this.jcoord()].getColor() == NONE){
                board[this.icoord()+1][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()+1][this.jcoord()].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()].setStroke(Color.AQUAMARINE);
            }
        }

        // Down
        if(this.jcoord()+1 < 8){
            if(pieces[this.icoord()][this.jcoord()+1].getColor() == NONE){
                board[this.icoord()][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }

        // Down - left
        if(this.jcoord()+1 < 8 && this.icoord()-1 >= 0){
            if(pieces[this.icoord()-1][this.jcoord()+1].getColor() == NONE){
                board[this.icoord()-1][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()-1][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }

        // Down - right
        if(this.jcoord()+1 < 8 && this.icoord()+1 < 8){
            if(pieces[this.icoord()+1][this.jcoord()+1].getColor() == NONE){
                board[this.icoord()+1][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[this.icoord()+1][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }
    }

}
