package com.stolz.alexander.chessengine.engine.pieces;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public enum PieceColor {
    NONE, WHITE, BLACK;

    public PieceColor mirror() {
        switch (this) {
            case WHITE:
                return BLACK;
            case BLACK:
                return WHITE;
            default:
                return NONE;
        }
    }
}
