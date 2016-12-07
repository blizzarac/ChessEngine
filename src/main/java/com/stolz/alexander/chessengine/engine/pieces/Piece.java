package com.stolz.alexander.chessengine.engine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Piece {
    protected PieceColor color;
    protected String imgname;
    protected int i;
    protected int j;
    protected Piece[][] newboard;
    protected boolean firstmove;

    public Image image() {
        return image();
    }

    public int icoord() {
        // Bounds correction
        if (i > 8) {
            return 7;
        }

        if (i < 0) {
            return 0;
        } else {
            return i;
        }
    }

    public int jcoord() {
        // Bounds correction
        if (j > 8) {
            return 7;
        }

        if (i < 0) {
            return 0;
        } else {
            return j;
        }
    }

    public String getImageFilename() {
        return imgname;
    }

    public Piece(PieceColor color) {
        this.color = color;
    }

    public boolean firstmove() {
        return firstmove;
    }

    public PieceColor getColor() { return color; }

    public Piece[][] move(Piece selectedpiece, Piece targetpiece, Piece[][] boardstate) {
        return newboard;
    }

    public abstract void drawValidMoves(Piece[][] pieces, Rectangle[][] board);
}
