package com.stolz.alexander.chessengine.parser;

import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.engine.pieces.Piece;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;

/**
 * Created by alexanderstolz on 1/4/17.
 */
public class DebugParser {
    @FunctionalInterface
    interface Function2 <A, B, R> {
        //R is like Return, but doesn't have to be last in the list nor named R.
        R apply (A a, B b);
    }

    public String printBoard(ChessBoard board) {
        StringBuilder builder = new StringBuilder();

        Function2<Piece, String, String> blackWhite =
                (Piece piece, String sym) -> piece.getColor()==BLACK?sym.toLowerCase():sym;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board.pieces[y][x].getType() == PieceType.NOTYPE) {
                    builder.append("_");
                } else if (board.pieces[y][x].getType() == PieceType.PAWN) {
                    builder.append(blackWhite.apply(board.pieces[y][x], "P"));
                } else if (board.pieces[y][x].getType() == PieceType.KING) {
                    builder.append(blackWhite.apply(board.pieces[y][x], "K"));
                } else if (board.pieces[y][x].getType() == PieceType.QUEEN) {
                    builder.append(blackWhite.apply(board.pieces[y][x], "Q"));
                } else if (board.pieces[y][x].getType() == PieceType.BISHOP) {
                    builder.append(blackWhite.apply(board.pieces[y][x], "B"));
                } else if (board.pieces[y][x].getType() == PieceType.KNIGHT) {
                    builder.append(blackWhite.apply(board.pieces[y][x], "N"));
                } else if (board.pieces[y][x].getType() == PieceType.ROOK) {
                    builder.append(blackWhite.apply(board.pieces[y][x], "R"));
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
