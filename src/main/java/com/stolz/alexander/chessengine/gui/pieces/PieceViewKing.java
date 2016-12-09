package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PieceViewKing extends PieceView {

    public PieceViewKing(PieceColor color, int ii, int jj) {
		super(PieceType.KING, color, ii, jj);
        imgname = "king.png";
        switch (color){
            case WHITE:
                image = new Image("whiteking.png");
                break;
            case BLACK:
                image = new Image("blackking.png");
                break;
        }
	}

	@Override
	public PieceView[][] move(PieceView k, PieceView t, PieceView[][] bs){
        PieceView[][] boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewKing(k.getColor(), t.icoord(), t.jcoord());
		boardstate[k.icoord()][k.jcoord()] = new Empty(k.icoord(), k.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public List<PiecePosition> findValidMoves(PieceView[][] pieceViews) {
        List<PiecePosition> validMoves = new ArrayList<>();

        // Up
        if(this.jcoord()-1 >= 0){
            if(pieceViews[this.icoord()][this.jcoord()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()-1));
            }
            if(pieceViews[this.icoord()][this.jcoord()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()-1));
            }
        }

        // Up - right
        if(this.jcoord()-1 >= 0 && this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-1));
            }
            if(pieceViews[this.icoord()+1][this.jcoord()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-1));
            }
        }

        // Up - left
        if(this.jcoord()-1 >= 0 && this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()-1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-1));
            }
            if(pieceViews[this.icoord()-1][this.jcoord()-1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-1));
            }
        }

        // Left
        if(this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()));
            }
            if(pieceViews[this.icoord()-1][this.jcoord()].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()));
            }
        }

        // Right
        if(this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()));
            }
            if(pieceViews[this.icoord()+1][this.jcoord()].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()));
            }
        }

        // Down
        if(this.jcoord()+1 < 8){
            if(pieceViews[this.icoord()][this.jcoord()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()+1));
            }
            if(pieceViews[this.icoord()][this.jcoord()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord(),this.jcoord()+1));
            }
        }

        // Down - left
        if(this.jcoord()+1 < 8 && this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+1));
            }
            if(pieceViews[this.icoord()-1][this.jcoord()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+1));
            }
        }

        // Down - right
        if(this.jcoord()+1 < 8 && this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()+1].getColor() == PieceColor.NONE){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+1));
            }
            if(pieceViews[this.icoord()+1][this.jcoord()+1].getColor()==this.color.mirror()){
                validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+1));
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
                possiblemoves[move.i][move.i] = new PieceViewKing(color, move.i, move.j)
        );

        return possiblemoves;
    }
}
