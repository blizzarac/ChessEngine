package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.pieces.PieceColor.NONE;

public class PieceBishop extends Piece {
    private Piece[][] boardstate;

    public PieceBishop(PieceColor type, int ii, int jj) {
        super(type);
        name = "Bishop";
        imgname = "bishop.png";
        this.color = type;
        this.i = ii;
        this.j = jj;
    }

    @Override
    public Image image() {
        if (color == PieceColor.WHITE)
            return new Image("whitebishopcursor.png");
        else
            return new Image("blackbishopcursor.png");
    }

    @Override
    public int icoord() {
        return i;
    }

    @Override
    public int jcoord() {
        return j;
    }

    @Override
    public Piece[][] move(Piece b, Piece t, Piece[][] bs) {
        boardstate = bs;
        // Move pawn
        boardstate[t.icoord()][t.jcoord()] = new PieceBishop(b.getColor(), t.icoord(), t.jcoord());
        boardstate[b.icoord()][b.jcoord()] = new Empty(NONE, b.icoord(), b.jcoord());
        // Return the new board
        return boardstate;
    }

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {
        // Look up .. (left)
        for(int y=this.jcoord()-1, x=this.icoord()-1; y >= 0 && x >= 0; y--,x--){
            if(pieces[x][y].getColor() == NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=-1; y=-1;
            }
            if(x!=-1 && y!=-1 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=-1; y=-1;
            }
        }

        // Look up .. (right)
        for(int y=this.jcoord()-1, x=this.icoord()+1; y >= 0 && x < 8; y--,x++){
            if(pieces[x][y].getColor() == NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=8; y=-1;
            }
            if(x!=8 && y!=-1 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=8; y=-1;
            }
        }

        // Look down .. (left)
        for(int y=this.jcoord()+1, x=this.icoord()-1; y < 8 && x >= 0; y++,x--){
            if(pieces[x][y].getColor() == NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=-1; y=8;
            }
            if(x!=-1 && y!=8 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=-1; y=8;
            }
        }

        // Look down .. (right)
        for(int y=this.jcoord()+1, x=this.icoord()+1; y < 8 && x < 8; y++,x++){
            if(pieces[x][y].getColor() == NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieces[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=8;y=8;
            }
            if(x!=8 && y!=8 && pieces[x][y].getColor()==this.color){
                // Stop Looking
                x=8;y=8;
            }
        }
    }
}
