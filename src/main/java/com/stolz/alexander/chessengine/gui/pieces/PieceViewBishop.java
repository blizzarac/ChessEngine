package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PieceViewBishop extends PieceView {

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
        PieceView[][] boardstate = bs;
        // Move pawn
        boardstate[t.icoord()][t.jcoord()] = new PieceViewBishop(b.getColor(), t.icoord(), t.jcoord());
        boardstate[b.icoord()][b.jcoord()] = new Empty(b.icoord(), b.jcoord());
        // Return the new board
        return boardstate;
    }

    @Override
    public List<PiecePosition> findValidMoves(PieceView[][] pieceViews) {
        List<PiecePosition> validMoves = new ArrayList<>();
        // Look up .. (left)
        for(int y=this.jcoord()-1, x=this.icoord()-1; y >= 0 && x >= 0; y--,x--){
            if(pieceViews[x][y].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
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
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
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
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
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
                validMoves.add(new PiecePosition(x,y));
            }
            if(pieceViews[x][y].getColor() == this.color.mirror()){
                validMoves.add(new PiecePosition(x,y));
                // Stop Looking
                x=8;y=8;
            }
            if(x!=8 && y!=8 && pieceViews[x][y].getColor()==this.color){
                // Stop Looking
                x=8;y=8;
            }
        }

        return validMoves;
    }

    @Override
    public PieceView[][] findPossibleMoves(PieceView[][] pieceViews) {
        PieceView[][] possiblemoves = new PieceView[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieceViews[x], 0, possiblemoves[x], 0, 8);
        }

        final List<PiecePosition> validMoves = findValidMoves(pieceViews);
        validMoves.stream().forEach(move ->
                possiblemoves[move.i][move.i] = new PieceViewBishop(color, move.i, move.j)
        );

        return possiblemoves;
    }
}
