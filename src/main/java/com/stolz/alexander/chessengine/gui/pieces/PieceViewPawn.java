package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;
import static com.stolz.alexander.chessengine.engine.pieces.PieceType.NOTYPE;

public class PieceViewPawn extends PieceView {
	boolean firstmove;

    public PieceViewPawn(PieceColor color, int ii, int jj, boolean fm) {
		super(PieceType.PAWN, color, ii, jj);
        imgname = "pawn.png";
		firstmove = fm;
        switch (color){
            case WHITE:
                image = new Image("whitepawn.png");
                break;
            case BLACK:
                image = new Image("blackpawn.png");
                break;
        }
	}
	
	@Override
	public boolean firstmove(){
		return firstmove;
	}

	@Override
	public PieceView[][] move(PieceView p, PieceView t, PieceView[][] bs){
		firstmove = false;
		bs[t.icoord()][t.jcoord()] = new PieceViewPawn(p.getColor(), t.icoord(), t.jcoord(), firstmove);
		bs[p.icoord()][p.jcoord()] = new Empty(p.icoord(), p.jcoord());

		return bs;
	}

    @Override
    public List<PiecePosition> drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
        List<PiecePosition> validMoves = new ArrayList<>();
		if (color == PieceColor.BLACK)
            drawBlackMoves(this, pieceViews, board);
		else
            drawWhiteMoves(this, pieceViews, board);

        return validMoves;
    }

    private void drawBlackMoves(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()+1 < 8){ // Guard for bounds
                if(pieceViews[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieceViews[p.icoord()][p.jcoord()+2].getColor() == PieceColor.NONE && pieceViews[p.icoord()][p.jcoord()+1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()+1 < 8){
                if(pieceViews[p.icoord()-1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieceViews[p.icoord()+1][p.jcoord()+1].getColor() == PieceColor.WHITE){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}
    }

    private void drawWhiteMoves(PieceView p, PieceView[][] pieceViews, Rectangle[][] board) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()-1 >= 0){ // Guard for bounds
                if(pieceViews[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieceViews[p.icoord()][p.jcoord()-2].getColor() == PieceColor.NONE && pieceViews[p.icoord()][p.jcoord()-1].getColor() == PieceColor.NONE){
                    board[p.icoord()][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()-1 >= 0){
                if(pieceViews[p.icoord()-1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieceViews[p.icoord()+1][p.jcoord()-1].getColor() == PieceColor.BLACK){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}
        }

    @Override
    public PieceView[][] findPossibleMoves(PieceView[][] pieceViews) {
        PieceView[][] possiblemoves = new PieceView[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(pieceViews[x], 0, possiblemoves[x], 0, 8);
        }

        whitePawnsMovement(this, pieceViews, possiblemoves);
        blackPawnsMovement(this, pieceViews, possiblemoves);
        return possiblemoves;
    }

    private void blackPawnsMovement(PieceView p, PieceView[][] pieceViews, PieceView[][] possiblemoves) {
        if (p.getColor() == BLACK) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if (p.jcoord() + 1 < 8) { // Guard for bounds
                if (pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 1] = new PieceViewPawn(p.getColor(), p.icoord(), p.jcoord() + 1, false);
                }
            }

            if (p.firstmove()) {
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if (pieceViews[p.icoord()][p.jcoord() + 2].getType() == NOTYPE && pieceViews[p.icoord()][p.jcoord() + 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() + 2] = new PieceViewPawn(p.getColor(), p.icoord(), p.jcoord() + 2, false);
                }
            }

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() - 1 >= 0 && p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord() - 1][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord() + 1] = new PieceViewPawn(p.getColor(), p.icoord() - 1, p.jcoord() + 1, false);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() + 1 < 8 && p.jcoord() + 1 < 8) {
                if (pieceViews[p.icoord() + 1][p.jcoord() + 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord() + 1] = new PieceViewPawn(p.getColor(), p.icoord() + 1, p.jcoord() + 1, false);
                }
            }
        }
    }

    private void whitePawnsMovement(PieceView p, PieceView[][] pieceViews, PieceView[][] possiblemoves) {
        if (p.getColor() == WHITE) {
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if (p.jcoord() - 1 >= 0) { // Guard for bounds
                if (pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 1] = new PieceViewPawn(p.getColor(), p.icoord(), p.jcoord() - 1, false);
                }
            }

            if (p.firstmove()) {
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if (pieceViews[p.icoord()][p.jcoord() - 2].getType() == NOTYPE && pieceViews[p.icoord()][p.jcoord() - 1].getType() == NOTYPE) {
                    possiblemoves[p.icoord()][p.jcoord() - 2] = new PieceViewPawn(p.getColor(), p.icoord(), p.jcoord() - 2, false);
                }
            }

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() - 1 >= 0 && p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord() - 1][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() - 1][p.jcoord() - 1] = new PieceViewPawn(p.getColor(), p.icoord() - 1, p.jcoord() - 1, false);
                }
            }

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if (p.icoord() + 1 < 8 && p.jcoord() - 1 >= 0) {
                if (pieceViews[p.icoord() + 1][p.jcoord() - 1].getColor() == p.getColor().mirror()) {
                    possiblemoves[p.icoord() + 1][p.jcoord() - 1] = new PieceViewPawn(p.getColor(), p.icoord() + 1, p.jcoord() - 1, false);
                }
            }
        }
    }
}
