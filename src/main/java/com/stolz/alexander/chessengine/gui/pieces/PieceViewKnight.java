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
    public List<PiecePosition> drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        List<PiecePosition> validMoves = new ArrayList<>();
            // Up and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()-2 >= 0){				// Bound check
                if(pieceViews[this.icoord()-1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    board[this.icoord()-1][this.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-1][this.jcoord()-2].getColor()==this.color.mirror()){
                    board[this.icoord()-1][this.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()-1 >= 0){				// Bound check
                if(pieceViews[this.icoord()-2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    board[this.icoord()-2][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-2][this.jcoord()-1].getColor()==this.color.mirror()){
                    board[this.icoord()-2][this.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()-2 >= 0){				// Bound check
                if(pieceViews[this.icoord()+1][this.jcoord()-2].getColor() == PieceColor.NONE){
                    board[this.icoord()+1][this.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+1][this.jcoord()-2].getColor()==this.color.mirror()){
                    board[this.icoord()+1][this.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()-1 >= 0){				// Bound check
                if(pieceViews[this.icoord()+2][this.jcoord()-1].getColor() == PieceColor.NONE){
                    board[this.icoord()+2][this.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+2][this.jcoord()-1].getColor()==this.color.mirror()){
                    board[this.icoord()+2][this.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(this.icoord()+1 < 8 && this.jcoord()+2 < 8){				// Bound check
                if(pieceViews[this.icoord()+1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    board[this.icoord()+1][this.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+1][this.jcoord()+2].getColor()==this.color.mirror()){
                    board[this.icoord()+1][this.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(this.icoord()+2 < 8 && this.jcoord()+1 < 8){				// Bound check
                if(pieceViews[this.icoord()+2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    board[this.icoord()+2][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()+2][this.jcoord()+1].getColor()==this.color.mirror()){
                    board[this.icoord()+2][this.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(this.icoord()-1 >= 0 && this.jcoord()+2 < 8){				// Bound check
                if(pieceViews[this.icoord()-1][this.jcoord()+2].getColor() == PieceColor.NONE){
                    board[this.icoord()-1][this.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-1][this.jcoord()+2].getColor()==this.color.mirror()){
                    board[this.icoord()-1][this.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(this.icoord()-2 >= 0 && this.jcoord()+1 < 8){				// Bound check
                if(pieceViews[this.icoord()-2][this.jcoord()+1].getColor() == PieceColor.NONE){
                    board[this.icoord()-2][this.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieceViews[this.icoord()-2][this.jcoord()+1].getColor()==this.color.mirror()){
                    board[this.icoord()-2][this.jcoord()+1].setStroke(Color.AQUAMARINE);
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
            // Up and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieceViews[p.icoord() - 1][p.jcoord() - 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceViewKnight(p.getColor(), p.icoord() - 1, p.jcoord() - 2);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() - 2].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 2] = new PieceViewKnight(p.getColor(), p.icoord() - 1, p.jcoord() - 2);
                }
            }

            // Up and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieceViews[p.icoord() - 2][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceViewKnight(p.getColor(), p.icoord() - 2, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() - 2][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 2][p.jcoord() - 1] = new PieceViewKnight(p.getColor(), p.icoord() - 2, p.jcoord() - 1);
                }
            }

            // Up and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() - 2 >= 0) {                // Bound check
                if (pieceViews[p.icoord() + 1][p.jcoord() - 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceViewKnight(p.getColor(), p.icoord() + 1, p.jcoord() - 2);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() - 2].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 2] = new PieceViewKnight(p.getColor(), p.icoord() + 1, p.jcoord() - 2);
                }
            }

            // Up and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() - 1 >= 0) {                // Bound check
                if (pieceViews[p.icoord() + 2][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceViewKnight(p.getColor(), p.icoord() + 2, p.jcoord() - 1);
                }
                if (pieceViews[p.icoord() + 2][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 2][p.jcoord() - 1] = new PieceViewKnight(p.getColor(), p.icoord() + 2, p.jcoord() - 1);
                }
            }

            // Bottom and right (first)
            if (p.icoord() + 1 < 8 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieceViews[p.icoord() + 1][p.jcoord() + 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceViewKnight(p.getColor(), p.icoord() + 1, p.jcoord() + 2);
                }
                if (pieceViews[p.icoord() + 1][p.jcoord() + 2].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 2] = new PieceViewKnight(p.getColor(), p.icoord() + 1, p.jcoord() + 2);
                }
            }

            // Bottom and right (second)
            if (p.icoord() + 2 < 8 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieceViews[p.icoord() + 2][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceViewKnight(p.getColor(), p.icoord() + 2, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() + 2][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 2][p.jcoord() + 1] = new PieceViewKnight(p.getColor(), p.icoord() + 2, p.jcoord() + 1);
                }
            }

            // Bottom and left (first)
            if (p.icoord() - 1 >= 0 && p.jcoord() + 2 < 8) {                // Bound check
                if (pieceViews[p.icoord() - 1][p.jcoord() + 2].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceViewKnight(p.getColor(), p.icoord() - 1, p.jcoord() + 2);
                }
                if (pieceViews[p.icoord() - 1][p.jcoord() + 2].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 2] = new PieceViewKnight(p.getColor(), p.icoord() - 1, p.jcoord() + 2);
                }
            }

            // Bottom and left (second)
            if (p.icoord() - 2 >= 0 && p.jcoord() + 1 < 8) {                // Bound check
                if (pieceViews[p.icoord() - 2][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceViewKnight(p.getColor(), p.icoord() - 2, p.jcoord() + 1);
                }
                if (pieceViews[p.icoord() - 2][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 2][p.jcoord() + 1] = new PieceViewKnight(p.getColor(), p.icoord() - 2, p.jcoord() + 1);
                }
            }

    }
}
