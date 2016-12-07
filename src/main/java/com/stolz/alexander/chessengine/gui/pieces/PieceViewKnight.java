package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewKnight extends PieceView {
	private PieceView[][] boardstate;

	public PieceViewKnight(PieceColor color, int ii, int jj) {
		super(PieceType.KNIGHT, color, ii, jj);
        imgname = "knight.png";
        switch (color){
            case WHITE:
                image = new Image("whiteknight.png");
                break;
            case BLACK:
                image = new Image("blackknight.png");
                break;
        }

	}

	@Override
	public PieceView[][] move(PieceView kn, PieceView t, PieceView[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewKnight(kn.getColor(), t.icoord(), t.jcoord());
		boardstate[kn.icoord()][kn.jcoord()] = new Empty(kn.icoord(), kn.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
            // Up and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()-2 >= 0){				// Bound check
                if(pieceViews[this.icoord()-1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    board[this.icoord()-1][this.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-1][this.jcoord()-2].getColor()==this.color.mirror()){
                    board[this.icoord()-1][this.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()-1 >= 0){				// Bound check
                if(pieceViews[this.icoord()-2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    board[this.icoord()-2][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-2][this.jcoord()-1].getColor()==this.color.mirror()){
                    board[this.icoord()-2][this.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()-2 >= 0){				// Bound check
                if(pieceViews[this.icoord()+1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    board[this.icoord()+1][this.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+1][this.jcoord()-2].getColor()==this.color.mirror()){
                    board[this.icoord()+1][this.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()-1 >= 0){				// Bound check
                if(pieceViews[this.icoord()+2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    board[this.icoord()+2][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+2][this.jcoord()-1].getColor()==this.color.mirror()){
                    board[this.icoord()+2][this.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()+2 < 8){				// Bound check
                if(pieceViews[this.icoord()+1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    board[this.icoord()+1][this.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+1][this.jcoord()+2].getColor()==this.color.mirror()){
                    board[this.icoord()+1][this.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()+1 < 8){				// Bound check
                if(pieceViews[this.icoord()+2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    board[this.icoord()+2][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+2][this.jcoord()+1].getColor()==this.color.mirror()){
                    board[this.icoord()+2][this.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()+2 < 8){				// Bound check
                if(pieceViews[this.icoord()-1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    board[this.icoord()-1][this.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-1][this.jcoord()+2].getColor()==this.color.mirror()){
                    board[this.icoord()-1][this.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()+1 < 8){				// Bound check
                if(pieceViews[this.icoord()-2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    board[this.icoord()-2][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-2][this.jcoord()+1].getColor()==this.color.mirror()){
                    board[this.icoord()-2][this.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
    }
}
