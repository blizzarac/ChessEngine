package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewQueen extends PieceView {

	public PieceViewQueen(PieceColor color, int ii, int jj) {
		super(PieceType.QUEEN, color, ii, jj);
        imgname = "queen.png";
        switch (color){
            case WHITE:
                image = new Image("whitequeen.png");
                break;
            case BLACK:
                image = new Image("blackqueen.png");
                break;
        }
	}

	@Override
	public PieceView[][] move(PieceView q, PieceView t, PieceView[][] bs){
        PieceView[][] boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewQueen(q.getColor(), t.icoord(), t.jcoord());
		boardstate[q.icoord()][q.jcoord()] = new Empty(q.icoord(), q.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        // Look up .. (left)
        for(int y=this.jcoord()-1, x=this.icoord()-1; y >= 0 && x >= 0; y--,x--){
            if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor()== this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=-1;y=-1;
            }
            if(x!=-1 && y!=-1 && pieceViews[x][y].getColor() == this.color){
                // Stop Looking
                x=-1;y=-1;
            }
        }

        // Look up .. (right)
        for(int y=this.jcoord()-1, x=this.icoord()+1; y >= 0 && x < 8; y--,x++){
            if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor()== this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=8;y=-1;
            }
            if(x!=8 && y!=-1 && pieceViews[x][y].getColor() == this.color){
                // Stop Looking
                x=8;y=-1;
            }
        }

        // Look down .. (left)
        for(int y=this.jcoord()+1, x=this.icoord()-1; y < 8 && x >= 0; y++,x--){
            if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor()== this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=-1;y=8;
            }
            if(x!=-1 && y!=8 && pieceViews[x][y].getColor() == this.color){
                // Stop Looking
                x=-1;y=8;
            }
        }

        // Look down .. (right)
        for(int y=this.jcoord()+1, x=this.icoord()+1; y < 8 && x < 8; y++,x++){
            if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor()== this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=8;y=8;
            }
            if(x!=8 && y!=8 && pieceViews[x][y].getColor() == this.color){
                // Stop Looking
                x=8;y=8;
            }
        }

        // Look Up ..
        for(int y = this.jcoord()-1; y >= 0; y--){
            if(pieceViews[this.icoord()][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
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
            if(pieceViews[x][this.jcoord()].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
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
            if(pieceViews[x][this.jcoord()].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
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
            if(pieceViews[this.icoord()][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                board[this.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][y].getColor()== this.color.mirror()){
                board[this.icoord()][y].setStroke(Color.AQUAMARINE);
                // Stop looking
                y=8;
            }
            if(y!=8 && pieceViews[this.icoord()][y].getColor() == this.color && y!=8){
                // Stop looking
                y=8;
            }
        }
    }
}
