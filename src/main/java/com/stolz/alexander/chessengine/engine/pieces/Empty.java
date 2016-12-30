package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Empty extends Piece {

	public Empty(int ii, int jj) {
		super(PieceType.NOTYPE, PieceColor.NONE, ii, jj);
	}

    @Override
	public List<PiecePosition> findValidMoves(Piece[][] pieces) {
		throw new NotImplementedException();
	}

    @Override
    public Piece copy() {
        throw new NotImplementedException();
    }
}
