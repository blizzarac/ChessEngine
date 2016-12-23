package com.stolz.alexander.chessengine.engine.pieces;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public abstract class Piece {
    protected PieceColor color;
    protected PieceType type;
    protected PiecePosition piecePosition;
    protected boolean firstmove;

    public Piece(PieceType type, PieceColor color, int i, int j) {
        this.type = type;
        this.color = color;
        piecePosition = new PiecePosition(i,j);
    }

    public boolean firstmove() {
        return firstmove;
    }

    /**
     *
     *
     * @param source
     * @param target
     * @param boardstate
     * @return
     */
    public Piece[][] move(Piece source, Piece target, Piece[][] boardstate) {
        final Piece[][] bs = Arrays.copyOf(boardstate, boardstate.length);

        final Piece tmp =  bs[target.icoord()][target.jcoord()]; // Saving target
        bs[target.icoord()][target.jcoord()] = bs[source.icoord()][source.jcoord()];
        bs[source.icoord()][source.jcoord()] = tmp;

        return bs;
    }

    public abstract List<PiecePosition> findValidMoves(Piece[][] pieces);

    public abstract Piece[][] findPossibleMoves(Piece[][] pieces);

    public int icoord() {
       return piecePosition.x;
    }

    public int jcoord() {
       return piecePosition.y;
    }

    public PiecePosition getPiecePosition() { return piecePosition;}

    public PieceColor getColor() { return color; }

    public PieceType getType() {
        return type;
    }
}
