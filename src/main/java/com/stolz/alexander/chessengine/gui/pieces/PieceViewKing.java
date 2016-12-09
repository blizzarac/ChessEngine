package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceType.NOTYPE;

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
    public List<PiecePosition> drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        List<PiecePosition> validMoves = new ArrayList<>();

        // Up
        if(this.jcoord()-1 >= 0){
            if(pieceViews[this.icoord()][this.jcoord()-1].getColor() == PieceColor.NONE){
                board[this.icoord()][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Up - right
        if(this.jcoord()-1 >= 0 && this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()-1].getColor() == PieceColor.NONE){
                board[this.icoord()+1][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()+1][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Up - left
        if(this.jcoord()-1 >= 0 && this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()-1].getColor() == PieceColor.NONE){
                board[this.icoord()-1][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()-1][this.jcoord()-1].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()-1].setStroke(Color.AQUAMARINE);
            }
        }

        // Left
        if(this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()].getColor() == PieceColor.NONE){
                board[this.icoord()-1][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()-1][this.jcoord()].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()].setStroke(Color.AQUAMARINE);
            }
        }

        // Right
        if(this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()].getColor() == PieceColor.NONE){
                board[this.icoord()+1][this.jcoord()].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()+1][this.jcoord()].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()].setStroke(Color.AQUAMARINE);
            }
        }

        // Down
        if(this.jcoord()+1 < 8){
            if(pieceViews[this.icoord()][this.jcoord()+1].getColor() == PieceColor.NONE){
                board[this.icoord()][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }

        // Down - left
        if(this.jcoord()+1 < 8 && this.icoord()-1 >= 0){
            if(pieceViews[this.icoord()-1][this.jcoord()+1].getColor() == PieceColor.NONE){
                board[this.icoord()-1][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()-1][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()-1][this.jcoord()+1].setStroke(Color.AQUAMARINE);
            }
        }

        // Down - right
        if(this.jcoord()+1 < 8 && this.icoord()+1 < 8){
            if(pieceViews[this.icoord()+1][this.jcoord()+1].getColor() == PieceColor.NONE){
                board[this.icoord()+1][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
            }
            if(pieceViews[this.icoord()+1][this.jcoord()+1].getColor()==this.color.mirror()){
                board[this.icoord()+1][this.jcoord()+1].setStroke(Color.AQUAMARINE);
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

        findPossibleMovesInternal(this, pieceViews, possiblemoves);

        return possiblemoves;
    }

    private void findPossibleMovesInternal(PieceView p, PieceView[][] pieceViews, PieceView[][] possiblemoves) {
            // Up
            if (p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewKing(p.getColor(), p.icoord(), p.jcoord() - 1);
                }
                if (pieceViews[p.icoord()][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewKing(p.getColor(), p.icoord(), p.jcoord() - 1);
                }
            }

            // Up - right
            if (p.jcoord() - 1 >= 0 && p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewKing(p.getColor(), p.icoord() + 1, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewKing(p.getColor(), p.icoord() + 1, p.jcoord() - 1);
                }
            }

            // Up - left
            if (p.jcoord() - 1 >= 0 && p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewKing(p.getColor(), p.icoord() - 1, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewKing(p.getColor(), p.icoord() - 1, p.jcoord() - 1);
                }
            }

            // Left
            if (p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceViewKing(p.getColor(), p.icoord() - 1, p.jcoord());
                }
                if (pieceViews[p.icoord() - 1][p.jcoord()].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord()] = new PieceViewKing(p.getColor(), p.icoord() - 1, p.jcoord());
                }
            }

            // Right
            if (p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord()].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceViewKing(p.getColor(), p.icoord() + 1, p.jcoord());
                }
                if (pieceViews[p.icoord() + 1][p.jcoord()].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord()] = new PieceViewKing(p.getColor(), p.icoord() + 1, p.jcoord());
                }
            }

            // Down
            if (p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewKing(p.getColor(), p.icoord(), p.jcoord() + 1);
                }
                if (pieceViews[p.icoord()][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewKing(p.getColor(), p.icoord(), p.jcoord() + 1);
                }
            }

            // Down - left
            if (p.jcoord() + 1 < 8 && p.icoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewKing(p.getColor(), p.icoord() - 1, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewKing(p.getColor(), p.icoord() - 1, p.jcoord() + 1);
                }
            }

            // Down - right
            if (p.jcoord() + 1 < 8 && p.icoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewKing(p.getColor(), p.icoord() + 1, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewKing(p.getColor(), p.icoord() + 1, p.jcoord() + 1);
                }
        }
    }
}
