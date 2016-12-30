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
     * Move piece on the board. NOT checking correct positioning.
     *
     * @param target Target position
     * @param boardstate
     * @return
     */
    public Piece[][] move(Piece[][] boardstate, PiecePosition target) {
        final Piece[][] bs = Arrays.copyOf(boardstate, boardstate.length);

        int currX = this.x();
        int currY = this.y();
        int targetX = target.x;
        int targetY = target.y;

        final Piece tmp =  bs[targetX][targetY]; // Saving target
        // Update target piece
        bs[targetX][targetY] = bs[currX][currY];
        bs[targetX][targetY].piecePosition.x = targetX;
        bs[targetX][targetY].piecePosition.y = targetY;

        // Update this
        bs[currX][currY] = tmp;
        bs[currX][currY].piecePosition.x = currX;
        bs[currX][currY].piecePosition.y = currY;

        return bs;
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
}
