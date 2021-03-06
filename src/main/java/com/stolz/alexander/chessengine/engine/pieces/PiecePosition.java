package com.stolz.alexander.chessengine.engine.pieces;

/**
 * Created by alexanderstolz on 12/9/16.
 */
public class PiecePosition {
    public int x;
    public int y;

    public PiecePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        final PiecePosition other = (PiecePosition) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y+")";
    }
}
