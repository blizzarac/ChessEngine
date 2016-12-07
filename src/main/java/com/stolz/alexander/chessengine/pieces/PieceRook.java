package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.pieces.PieceColor.WHITE;

public class PieceRook extends Piece {
	private Piece[][] boardstate;

	public PieceRook(PieceColor type, int ii, int jj) {
		super(type);
        name = "Rook";
        imgname = "rook.png";
		this.color = type;
		i = ii;
		j = jj;
	}
	
	@Override
	public Image image(){
	 if(color == PieceColor.WHITE)
	 return new Image("whiterookcursor.png");
	 else
	 return new Image("blackrookcursor.png");
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
	public Piece[][] move(Piece r, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceRook(r.getColor(), t.icoord(), t.jcoord());
		boardstate[r.icoord()][r.jcoord()] = new Empty(PieceColor.NONE, r.icoord(), r.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
        whiteRooks(this, pieces, board);
        blackRook(this, pieces, board);
    }







    private void blackRook(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //__________________________________________BLACKROOK_____________________________________//

        if(p.toString() == "Rook" && p.getColor() == BLACK){
            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].getColor()==WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].getColor() == BLACK){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].getColor()==WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].getColor() == BLACK){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].getColor()==WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].getColor() == BLACK){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].getColor()==WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].getColor() == BLACK){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void whiteRooks(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //__________________________________________WHITEROOK_____________________________________//
        if(p.toString() == "Rook" && p.getColor() == WHITE){
            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].getColor() == BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].getColor()==WHITE){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].getColor() == BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].getColor()==WHITE){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].getColor() == BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].getColor()==WHITE){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].getColor() == BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].getColor()==WHITE && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }
}
