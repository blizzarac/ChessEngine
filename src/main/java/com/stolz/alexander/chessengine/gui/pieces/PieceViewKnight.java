package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PieceViewKnight extends PieceView {

    public PieceViewKnight(PieceColor color, int ii, int jj) {
		super(PieceType.KNIGHT, color, ii, jj);
        imgname = "knight.png";
        switch (color){
            case WHITE:
                image = new Image("whiteknight.png");
                break;
            case BLACK:
                image = new Image("blackknight.png");
                break;
        }

	}

	@Override
	public PieceView[][] move(PieceView kn, PieceView t, PieceView[][] bs){
        PieceView[][] boardstate = bs;
		// Move pawn
		boardstate[t.icoord()][t.jcoord()] = new PieceViewKnight(kn.getColor(), t.icoord(), t.jcoord());
		boardstate[kn.icoord()][kn.jcoord()] = new Empty(kn.icoord(), kn.jcoord());
		// Return the new board
		return boardstate;
	}

    @Override
    public List<PiecePosition> findValidMoves(PieceView[][] pieceViews) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // Up and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()-2 >= 0){				// Bound check
                if(pieceViews[this.icoord()-1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-2));
                }
                if(pieceViews[this.icoord()-1][this.jcoord()-2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()-2));
                }
            }

            // Up and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()-1 >= 0){				// Bound check
                if(pieceViews[this.icoord()-2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()-1));
                }
                if(pieceViews[this.icoord()-2][this.jcoord()-1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()-1));
                }
            }

            // Up and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()-2 >= 0){				// Bound check
                if(pieceViews[this.icoord()+1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-2));
                }
                if(pieceViews[this.icoord()+1][this.jcoord()-2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()-2));
                }
            }

            // Up and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()-1 >= 0){				// Bound check
                if(pieceViews[this.icoord()+2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()-1));
                }
                if(pieceViews[this.icoord()+2][this.jcoord()-1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()-1));
                }
            }

            // Bottom and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()+2 < 8){				// Bound check
                if(pieceViews[this.icoord()+1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+2));
                }
                if(pieceViews[this.icoord()+1][this.jcoord()+2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+1,this.jcoord()+2));
                }
            }

            // Bottom and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()+1 < 8){				// Bound check
                if(pieceViews[this.icoord()+2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()+1));
                }
                if(pieceViews[this.icoord()+2][this.jcoord()+1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()+2,this.jcoord()+1));
                }
            }

            // Bottom and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()+2 < 8){				// Bound check
                if(pieceViews[this.icoord()-1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+2));
                }
                if(pieceViews[this.icoord()-1][this.jcoord()+2].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-1,this.jcoord()+2));
                }
            }

            // Bottom and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()+1 < 8){				// Bound check
                if(pieceViews[this.icoord()-2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()+1));
                }
                if(pieceViews[this.icoord()-2][this.jcoord()+1].getColor()==this.color.mirror()){
                    validMoves.add(new PiecePosition(this.icoord()-2,this.jcoord()+1));
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
                possiblemoves[move.i][move.i] = new PieceViewKnight(color, move.i, move.j)
        );

        return possiblemoves;
	}
}
