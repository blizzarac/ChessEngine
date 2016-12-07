package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceViewBishop extends PieceView {
    private PieceView[][] boardstate;

    public PieceViewBishop(PieceColor color, int ii, int jj) {
        super(PieceType.BISHOP, color, ii, jj);
        imgname = "bishop.png";
        switch (color){
            case WHITE:
                image = new Image("whitebishop.png");
                break;
            case BLACK:
                image = new Image("blackbishop.png");
                break;
        }
    }

    @Override
    public PieceView[][] move(PieceView b, PieceView t, PieceView[][] bs) {
        boardstate = bs;
        // Move pawn
        boardstate[t.icoord()][t.jcoord()] = new PieceViewBishop(b.getColor(), t.icoord(), t.jcoord());
        boardstate[b.icoord()][b.jcoord()] = new Empty(b.icoord(), b.jcoord());
        // Return the new board
        return boardstate;
    }

    @Override
    public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        // Look up .. (left)
        for(int y=this.jcoord()-1, x=this.icoord()-1; y >= 0 && x >= 0; y--,x--){
            if(pieceViews[x][y].getColor() == PieceColor.NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=-1; y=-1;
            }
            if(x!=-1 && y!=-1 && pieceViews[x][y].getColor()==this.color){
                // Stop Looking
                x=-1; y=-1;
            }
        }

        // Look up .. (right)
        for(int y=this.jcoord()-1, x=this.icoord()+1; y >= 0 && x < 8; y--,x++){
            if(pieceViews[x][y].getColor() == PieceColor.NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=8; y=-1;
            }
            if(x!=8 && y!=-1 && pieceViews[x][y].getColor()==this.color){
                // Stop Looking
                x=8; y=-1;
            }
        }

        // Look down .. (left)
        for(int y=this.jcoord()+1, x=this.icoord()-1; y < 8 && x >= 0; y++,x--){
            if(pieceViews[x][y].getColor() == PieceColor.NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=-1; y=8;
            }
            if(x!=-1 && y!=8 && pieceViews[x][y].getColor()==this.color){
                // Stop Looking
                x=-1; y=8;
            }
        }

        // Look down .. (right)
        for(int y=this.jcoord()+1, x=this.icoord()+1; y < 8 && x < 8; y++,x++){
            if(pieceViews[x][y].getColor() == PieceColor.NONE){
                board[x][y].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                board[x][y].setStroke(Color.AQUAMARINE);
                // Stop Looking
                x=8;y=8;
            }
            if(x!=8 && y!=8 && pieceViews[x][y].getColor()==this.color){
                // Stop Looking
                x=8;y=8;
            }
        }
    }
}
