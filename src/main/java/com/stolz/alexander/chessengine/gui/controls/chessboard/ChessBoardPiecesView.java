package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.engine.pieces.*;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

/**
 * Created by alexanderstolz on 12/9/16.
 */
public class ChessBoardPiecesView {
    public final Piece[][] pieces;


    public ChessBoardPiecesView() {
        pieces = initPiecesViews();
    }



    public Piece[][] initPiecesViews() {
        //initialize the board: background, data structures, inital layout of pieces
        Piece[][] pieces = new Piece[ChessBoard.boardWidth][ChessBoard.boardHeight];

        // White Pieces
        pieces[7][7] = new PieceRook(WHITE, 7, 7);
        pieces[6][7] = new PieceKnight(WHITE, 6, 7);
        pieces[5][7] = new PieceBishop(WHITE, 5, 7);
        pieces[4][7] = new PieceKing(WHITE, 4, 7);
        pieces[3][7] = new PieceQueen(WHITE, 3, 7);
        pieces[2][7] = new PieceBishop(WHITE, 2, 7);
        pieces[1][7] = new PieceKnight(WHITE, 1, 7);
        pieces[0][7] = new PieceRook(WHITE, 0, 7);
        // Pawns
        pieces[7][6] = new PiecePawn(WHITE, 7, 6, true);
        pieces[6][6] = new PiecePawn(WHITE, 6, 6, true);
        pieces[5][6] = new PiecePawn(WHITE, 5, 6, true);
        pieces[4][6] = new PiecePawn(WHITE, 4, 6, true);
        pieces[3][6] = new PiecePawn(WHITE, 3, 6, true);
        pieces[2][6] = new PiecePawn(WHITE, 2, 6, true);
        pieces[1][6] = new PiecePawn(WHITE, 1, 6, true);
        pieces[0][6] = new PiecePawn(WHITE, 0, 6, true);

        // com.stolz.alexander.chessengine.engine.pieces.Empty Pieces
        for (int x = 5; x > 1; x--) {
            for (int j = 0; j < 8; j++) {
                pieces[j][x] = new Empty(j, x);
            }
        }

        // Black Pieces
        pieces[7][0] = new PieceRook(BLACK, 7, 0);
        pieces[6][0] = new PieceKnight(BLACK, 6, 0);
        pieces[5][0] = new PieceBishop(BLACK, 5, 0);
        pieces[4][0] = new PieceKing(BLACK, 4, 0);
        pieces[3][0] = new PieceQueen(BLACK, 3, 0);
        pieces[2][0] = new PieceBishop(BLACK, 2, 0);
        pieces[1][0] = new PieceKnight(BLACK, 1, 0);
        pieces[0][0] = new PieceRook(BLACK, 0, 0);
        // Pawns
        pieces[7][1] = new PiecePawn(BLACK, 7, 1, true);
        pieces[6][1] = new PiecePawn(BLACK, 6, 1, true);
        pieces[5][1] = new PiecePawn(BLACK, 5, 1, true);
        pieces[4][1] = new PiecePawn(BLACK, 4, 1, true);
        pieces[3][1] = new PiecePawn(BLACK, 3, 1, true);
        pieces[2][1] = new PiecePawn(BLACK, 2, 1, true);
        pieces[1][1] = new PiecePawn(BLACK, 1, 1, true);
        pieces[0][1] = new PiecePawn(BLACK, 0, 1, true);

        return pieces;
    }

    public void replacePieceViews(Piece[][] newboard) {
        for (int x = 0; x < 8; x++) {
            System.arraycopy(newboard[x], 0, pieces[x], 0, 8);
        }
    }
}
