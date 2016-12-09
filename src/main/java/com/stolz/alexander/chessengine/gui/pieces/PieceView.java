package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.List;

public abstract class PieceView extends Piece {

    protected String imgname;
    protected Image image;
    protected PieceView[][] newboard;
    protected boolean firstmove;

    public PieceView(PieceType type, PieceColor color, int i, int j) {
        super(type, color, i, j);
    }

    public Image getImage() {
        return image;
    }

    public String getImageFilename() {
        return imgname;
    }

    public boolean firstmove() {
        return firstmove;
    }

    public PieceView[][] move(PieceView selectedpiece, PieceView targetpiece, PieceView[][] boardstate) {
        return newboard;
    }

    public abstract List<PiecePosition> findValidMoves(PieceView[][] pieceViews);

    public abstract PieceView[][] findPossibleMoves(PieceView[][] pieceViews);
}
