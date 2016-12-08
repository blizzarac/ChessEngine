package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.NONE;

public class PieceViewRook extends PieceView {

    public PieceViewRook(PieceColor color, int ii, int jj) {
		super(PieceType.ROOK, color, ii, jj);
        imgname = "rook.png";
        switch (color){
            case WHITE:
                image = new Image("whiterook.png");
                break;
            case BLACK:
                image = new Image("blackrook.png");
                break;
        }
	}

	@Override
	public PieceView[][] move(PieceView r, PieceView t, PieceView[][] bs){
		bs[t.icoord()][t.jcoord()] = new PieceViewRook(r.getColor(), t.icoord(), t.jcoord());
		bs[r.icoord()][r.jcoord()] = new Empty(r.icoord(), r.jcoord());
		// Return the new board
		return bs;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        // Look Up ..
        for(int y = this.jcoord()-1; y >= 0; y--){
            if(pieceViews[this.icoord()][y].getColor() == NONE){
                board[this.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][y].getColor()== this.color.mirror()){
                board[this.icoord()][y].setStroke(Color.AQUAMARINE);
                // Stop looking
                y=-1;
            }
            if(y!=-1 && pieceViews[this.icoord()][y].getColor() == this.color){
                // Stop looking
                y=-1;
            }
        }

        // Look Right ..
        for(int x = this.icoord()+1; x < 8; x++){
            if(pieceViews[x][this.jcoord()].getColor() == NONE){
                board[x][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][this.jcoord()].getColor()== this.color.mirror()){
                board[x][this.jcoord()].setStroke(Color.AQUAMARINE);
                // Stop looking
                x=8;
            }
            if(x!=8 && pieceViews[x][this.jcoord()].getColor() == this.color){
                // Stop looking
                x=8;
            }
        }

        // Look Left ..
        for(int x = this.icoord()-1; x >= 0; x--){
            if(pieceViews[x][this.jcoord()].getColor() == NONE){
                board[x][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][this.jcoord()].getColor()== this.color.mirror()){
                board[x][this.jcoord()].setStroke(Color.AQUAMARINE);
                // Stop looking
                x=-1;
            }
            if(x!=-1 && pieceViews[x][this.jcoord()].getColor() == this.color){
                // Stop looking
                x=-1;
            }
        }

        // Look Down ..
        for(int y = this.jcoord()+1; y < 8; y++){
            if(pieceViews[this.icoord()][y].getColor() == NONE){
                board[this.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][y].getColor()== this.color.mirror()){
                board[this.icoord()][y].setStroke(Color.AQUAMARINE);
                // Stop looking
                y=8;
            }
            if(y!=8 && pieceViews[this.icoord()][y].getColor() == this.color){
                // Stop looking
                y=8;
            }
        }
    }
}
