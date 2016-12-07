package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;

//class declaration - abstract because we will not want to create a com.stolz.alexander.chessengine.pieces.Piece object but we would
//like to specify the private fields that all pieces should have in addition to their behaviours
public abstract class Piece {

    //piece can be either white (1) or black (2)
    private int type;
    private String name;
    private String imgname;
    private int i;
    private int j;
    private Piece[][] newboard;
    private boolean firstmove;

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

    public Piece(int type) {
        this.type = type;
    }

    public boolean firstmove() {
        return firstmove;
    }

    public int type() {
        if (type == 1) {
            return 1;
        }
        if (type == 2) {
            return 2;
        } else return 0;
    }

    public Piece[][] move(Piece selectedpiece, Piece targetpiece, Piece[][] boardstate) {
        return newboard;
    }
}
