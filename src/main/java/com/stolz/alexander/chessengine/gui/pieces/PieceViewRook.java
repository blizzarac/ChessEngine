package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewRook extends PieceView {
	private PieceView[][] boardstate;

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
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewRook(r.getColor(), t.icoord(), t.jcoord());
		boardstate[r.icoord()][r.jcoord()] = new Empty(r.icoord(), r.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        whiteRooks(this, pieceViews, board);
        blackRook(this, pieceViews, board);
    }







    private void blackRook(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
        //__________________________________________BLACKROOK_____________________________________//

        if(p.toString() == "Rook" && p.getColor() == PieceColor.BLACK){
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
                if(y!=8 && pieceViews[p.icoord()][y].getColor() == PieceColor.BLACK){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void whiteRooks(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
        //__________________________________________WHITEROOK_____________________________________//
        if(p.toString() == "Rook" && p.getColor() == PieceColor.WHITE){
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
