package com.stolz.alexander.chessengine.engine.pieces;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public abstract class Piece {
    protected PieceColor color;
    protected PieceType type;
    public PiecePosition piecePosition;

    public Piece(PieceType type, PieceColor color, int i, int j) {
        this.type = type;
        this.color = color;
        piecePosition = new PiecePosition(i,j);
    }

    /**
     * Returns a list of possible positions for the current board.
     * @param pieces The pieces on the board.
     * @return List of possible positions.
     */
    public abstract List<PiecePosition> findValidMoves(Piece[][] pieces);

    public int x() {
       return piecePosition.x;
    }

    public int y() {
       return piecePosition.y;
    }

    public PiecePosition getPiecePosition() { return piecePosition;}

    public void setPiecePosition(PiecePosition pos) { this.piecePosition = pos;}
    public PieceColor getColor() { return color; }

    public PieceType getType() {
        return type;
    }

    public abstract Piece copy();

    public abstract boolean isCheck(Piece[][] pieces, PieceColor currentPlayer);
}
