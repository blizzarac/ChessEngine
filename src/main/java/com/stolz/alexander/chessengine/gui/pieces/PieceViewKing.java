package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewKing extends PieceView {
	private PieceView[][] boardstate;

	public PieceViewKing(PieceColor color, int ii, int jj) {
		super(PieceType.KING, color, ii, jj);
        imgname = "king.png";
        switch (color){
            case WHITE:
                image = new Image("whiteking.png");
                break;
            case BLACK:
                image = new Image("blackking.png");
                break;
        }
	}

	@Override
	public PieceView[][] move(PieceView k, PieceView t, PieceView[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewKing(k.getColor(), t.icoord(), t.jcoord());
		boardstate[k.icoord()][k.jcoord()] = new Empty(k.icoord(), k.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        // Up
        if(this.jcoord()-1 >= 0){
            if(pieceViews[this.icoord()][this.jcoord()-1].getColor() == PieceColor.NONE){
                board[this.icoord()][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Up - right
        if(this.jcoord()-1 >= 0 && this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()-1].getColor() == PieceColor.NONE){
                board[this.icoord()+1][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()+1][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Up - left
        if(this.jcoord()-1 >= 0 && this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()-1].getColor() == PieceColor.NONE){
                board[this.icoord()-1][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()-1][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Left
        if(this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()].getColor() == PieceColor.NONE){
                board[this.icoord()-1][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()-1][this.jcoord()].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()].setStroke(Color.AQUAMARINE);
            }
        }

        // Right
        if(this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()].getColor() == PieceColor.NONE){
                board[this.icoord()+1][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()+1][this.jcoord()].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()].setStroke(Color.AQUAMARINE);
            }
        }

        // Down
        if(this.jcoord()+1 < 8){
            if(pieceViews[this.icoord()][this.jcoord()+1].getColor() == PieceColor.NONE){
                board[this.icoord()][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }

        // Down - left
        if(this.jcoord()+1 < 8 && this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()+1].getColor() == PieceColor.NONE){
                board[this.icoord()-1][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()-1][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }

        // Down - right
        if(this.jcoord()+1 < 8 && this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()+1].getColor() == PieceColor.NONE){
                board[this.icoord()+1][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()+1][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }
    }

}
