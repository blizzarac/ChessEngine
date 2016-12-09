package com.stolz.alexander.chessengine.engine.pieces;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public class Piece {
    protected PieceColor color;
    protected PieceType type;
    protected PiecePosition piecePosition;

    public Piece(PieceType type, PieceColor color, int i, int j) {
        this.type = type;
        this.color = color;
        piecePosition = new PiecePosition(i,j);
    }

    public int icoord() {
       return piecePosition.i ;
    }

    public int jcoord() {
       return piecePosition.i ;
    }

    public PieceColor getColor() { return color; }

    public PieceType getType() {
        return type;
    }
}
