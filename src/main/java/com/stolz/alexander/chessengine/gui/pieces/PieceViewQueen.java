package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewQueen extends PieceView {
	private PieceView[][] boardstate;
	private int te;

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
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewQueen(q.getColor(), t.icoord(), t.jcoord());
		boardstate[q.icoord()][q.jcoord()] = new Empty(q.icoord(), q.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        whiteQueen(this, pieceViews, board);
        blackQueen(this, pieceViews, board);
    }

    private void blackQueen(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
        //______________________________BLACKQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.getColor() == PieceColor.BLACK){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor()== PieceColor.WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=-1;
                }
                if(x!=-1 && y!=-1 && pieceViews[x][y].getColor() == PieceColor.BLACK){
                    // Stop Looking
                    x=-1;y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor()== PieceColor.WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=-1;
                }
                if(x!=8 && y!=-1 && pieceViews[x][y].getColor() == PieceColor.BLACK){
                    // Stop Looking
                    x=8;y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor()== PieceColor.WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieceViews[x][y].getColor() == PieceColor.BLACK){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor()== PieceColor.WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieceViews[x][y].getColor() == PieceColor.BLACK){
                    // Stop Looking
                    x=8;y=8;
                }
            }

            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieceViews[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[p.icoord()][y].getColor()== PieceColor.WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieceViews[p.icoord()][y].getColor() == PieceColor.BLACK){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieceViews[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][p.jcoord()].getColor()== PieceColor.WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieceViews[x][p.jcoord()].getColor() == PieceColor.BLACK){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieceViews[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][p.jcoord()].getColor()== PieceColor.WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieceViews[x][p.jcoord()].getColor() == PieceColor.BLACK){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieceViews[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[p.icoord()][y].getColor()== PieceColor.WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieceViews[p.icoord()][y].getColor() == PieceColor.BLACK && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void whiteQueen(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
        //______________________________WHITEQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.getColor() == PieceColor.WHITE){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor() == PieceColor.BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieceViews[x][y].getColor()== PieceColor.WHITE){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor() == PieceColor.BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8; y=-1;
                }
                if(x!=8 && y!=-1 && pieceViews[x][y].getColor()== PieceColor.WHITE){
                    // Stop Looking
                    x=8; y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor() == PieceColor.BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieceViews[x][y].getColor()== PieceColor.WHITE){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieceViews[x][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][y].getColor() == PieceColor.BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieceViews[x][y].getColor()== PieceColor.WHITE){
                    // Stop Looking
                    x=8;y=8;
                }
            }

            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieceViews[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[p.icoord()][y].getColor() == PieceColor.BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieceViews[p.icoord()][y].getColor()== PieceColor.WHITE){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieceViews[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][p.jcoord()].getColor() == PieceColor.BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieceViews[x][p.jcoord()].getColor()== PieceColor.WHITE){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieceViews[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[x][p.jcoord()].getColor() == PieceColor.BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieceViews[x][p.jcoord()].getColor()== PieceColor.WHITE){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieceViews[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.gui.pieceViews.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[p.icoord()][y].getColor() == PieceColor.BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieceViews[p.icoord()][y].getColor()== PieceColor.WHITE && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }
}
