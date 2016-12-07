package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

//class declaration - abstract because we will not want to create a com.stolz.alexander.chessengine.pieces.Piece object but we would
//like to specify the private fields that all pieces should have in addition to their behaviours
public abstract class Piece {

    public enum PieceColor {NONE, WHITE, BLACK}

    ;

    //piece can be either white (1) or black (2)
    protected PieceColor type;
    protected String name;
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

    @Override
    public String toString() {
        return name;
    }

    public String imagefilename() {
        return imgname;
    }

    public Piece(PieceColor type) {
        this.type = type;
    }

    public boolean firstmove() {
        return firstmove;
    }

    public PieceColor type() { return type; }

    public Piece[][] move(Piece selectedpiece, Piece targetpiece, Piece[][] boardstate) {
        return newboard;
    }

    public abstract void drawValidMoves(Piece[][] pieces, Rectangle[][] board);
}
