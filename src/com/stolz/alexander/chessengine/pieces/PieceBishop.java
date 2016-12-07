package com.stolz.alexander.chessengine.pieces;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PieceBishop extends Piece {
    private Piece[][] boardstate;

    public PieceBishop(PieceColor type, int ii, int jj) {
        super(type);
        name = "Bishop";
        imgname = "bishop.png";
        this.type = type;
        this.i = ii;
        this.j = jj;
    }

    @Override
    public Image image() {
        if (type == PieceColor.WHITE)
            return new Image("file:resource/whitebishopcursor.png");
        else
            return new Image("file:resource/blackbishopcursor.png");
    }

    @Override
    public int icoord() {
        return i;
    }

    @Override
    public int jcoord() {
        return j;
    }

    @Override
    public Piece[][] move(Piece b, Piece t, Piece[][] bs) {
        boardstate = bs;
        // Move pawn
        boardstate[t.icoord()][t.jcoord()] = new PieceBishop(b.type(), t.icoord(), t.jcoord());
        boardstate[b.icoord()][b.jcoord()] = new Empty(PieceColor.NONE, b.icoord(), b.jcoord());
        // Return the new board
        return boardstate;
    }

    @Override
    public void drawValidMoves(Piece[][] pieces, Rectangle[][] board) {

    }

}
