package com.stolz.alexander.chessengine.engine.pieces;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public class Piece {
    protected PieceColor color;



    protected PieceType type;
    protected int i;
    protected int j;

    public Piece(PieceType type, PieceColor color, int i, int j) {
        this.type = type;
        this.color = color;
        this.i = i;
        this.j = j;
    }

    public int icoord() {
       return i;
    }

    public int jcoord() {
       return j;
    }

    public PieceColor getColor() { return color; }

    public PieceType getType() {
        return type;
    }
}
