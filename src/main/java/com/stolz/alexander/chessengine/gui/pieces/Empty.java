package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Empty extends PieceView {

	public Empty(int ii, int jj) {
		super(PieceType.NOTYPE, PieceColor.NONE, ii, jj);
        image = new Image("empty.png");
	}

	@Override
	public void drawValidMoves(PieceView[][] pieceViews, Rectangle[][] board) {
		throw new NotImplementedException();
	}

    @Override
    public PieceView[][] findPossibleMoves(PieceView[][] pieceViews) {
        throw new NotImplementedException();
    }
}
