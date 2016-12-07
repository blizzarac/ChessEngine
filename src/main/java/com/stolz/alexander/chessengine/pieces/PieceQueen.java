package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.pieces.PieceColor.WHITE;

public class PieceQueen extends Piece {
	private Piece[][] boardstate;
	private int te;

	public PieceQueen(PieceColor type, int ii, int jj) {
		super(type);
        name = "Queen";
        imgname = "queen.png";
		this.color = type;
		i = ii;
		j = jj;
	}
	
	@Override
	public Image image(){
	 if(color == PieceColor.WHITE)
	 return new Image("whitequeencursor.png");
	 else
	 return new Image("blackqueencursor.png");
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
	public Piece[][] move(Piece q, Piece t, Piece[][] bs){
		boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceQueen(q.getColor(), t.icoord(), t.jcoord());
		boardstate[q.icoord()][q.jcoord()] = new Empty(PieceColor.NONE, q.icoord(), q.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
        whiteQueen(this, pieces, board);
        blackQueen(this, pieces, board);
    }

    private void blackQueen(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //______________________________BLACKQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.getColor() == BLACK){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].getColor() == BLACK){
                    // Stop Looking
                    x=-1;y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].getColor() == BLACK){
                    // Stop Looking
                    x=8;y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].getColor() == BLACK){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].getColor() == BLACK){
                    // Stop Looking
                    x=8;y=8;
                }
            }

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
                if(y!=8 && pieces[p.icoord()][y].getColor() == BLACK && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void whiteQueen(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //______________________________WHITEQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.getColor() == WHITE){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].getColor()==WHITE){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8; y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].getColor()==WHITE){
                    // Stop Looking
                    x=8; y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].getColor()==WHITE){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].getColor() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].getColor()==WHITE){
                    // Stop Looking
                    x=8;y=8;
                }
            }

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
