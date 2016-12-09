package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.gui.pieces.*;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

/**
 * Created by alexanderstolz on 12/9/16.
 */
public class ChessBoardPiecesView {
    public final PieceView[][] pieceViews;


    public ChessBoardPiecesView() {
        pieceViews = initPiecesViews();
    }



    public PieceView[][] initPiecesViews() {
        //initialize the board: background, data structures, inital layout of pieceViews
        PieceView[][] pieceViews = new PieceView[ChessBoard.boardWidth][ChessBoard.boardHeight];

        // White Pieces
        pieceViews[7][7] = new PieceViewRook(WHITE, 7, 7);
        pieceViews[6][7] = new PieceViewKnight(WHITE, 6, 7);
        pieceViews[5][7] = new PieceViewBishop(WHITE, 5, 7);
        pieceViews[4][7] = new PieceViewKing(WHITE, 4, 7);
        pieceViews[3][7] = new PieceViewQueen(WHITE, 3, 7);
        pieceViews[2][7] = new PieceViewBishop(WHITE, 2, 7);
        pieceViews[1][7] = new PieceViewKnight(WHITE, 1, 7);
        pieceViews[0][7] = new PieceViewRook(WHITE, 0, 7);
        // Pawns
        pieceViews[7][6] = new PieceViewPawn(WHITE, 7, 6, true);
        pieceViews[6][6] = new PieceViewPawn(WHITE, 6, 6, true);
        pieceViews[5][6] = new PieceViewPawn(WHITE, 5, 6, true);
        pieceViews[4][6] = new PieceViewPawn(WHITE, 4, 6, true);
        pieceViews[3][6] = new PieceViewPawn(WHITE, 3, 6, true);
        pieceViews[2][6] = new PieceViewPawn(WHITE, 2, 6, true);
        pieceViews[1][6] = new PieceViewPawn(WHITE, 1, 6, true);
        pieceViews[0][6] = new PieceViewPawn(WHITE, 0, 6, true);

        // com.stolz.alexander.chessengine.gui.pieceViews.Empty Pieces
        for (int x = 5; x > 1; x--) {
            for (int j = 0; j < 8; j++) {
                pieceViews[j][x] = new Empty(j, x);
            }
        }

        // Black Pieces
        pieceViews[7][0] = new PieceViewRook(BLACK, 7, 0);
        pieceViews[6][0] = new PieceViewKnight(BLACK, 6, 0);
        pieceViews[5][0] = new PieceViewBishop(BLACK, 5, 0);
        pieceViews[4][0] = new PieceViewKing(BLACK, 4, 0);
        pieceViews[3][0] = new PieceViewQueen(BLACK, 3, 0);
        pieceViews[2][0] = new PieceViewBishop(BLACK, 2, 0);
        pieceViews[1][0] = new PieceViewKnight(BLACK, 1, 0);
        pieceViews[0][0] = new PieceViewRook(BLACK, 0, 0);
        // Pawns
        pieceViews[7][1] = new PieceViewPawn(BLACK, 7, 1, true);
        pieceViews[6][1] = new PieceViewPawn(BLACK, 6, 1, true);
        pieceViews[5][1] = new PieceViewPawn(BLACK, 5, 1, true);
        pieceViews[4][1] = new PieceViewPawn(BLACK, 4, 1, true);
        pieceViews[3][1] = new PieceViewPawn(BLACK, 3, 1, true);
        pieceViews[2][1] = new PieceViewPawn(BLACK, 2, 1, true);
        pieceViews[1][1] = new PieceViewPawn(BLACK, 1, 1, true);
        pieceViews[0][1] = new PieceViewPawn(BLACK, 0, 1, true);

        return pieceViews;
    }

    public void replacePieceViews(PieceView[][] newboard) {
        for (int x = 0; x < 8; x++) {
            System.arraycopy(newboard[x], 0, pieceViews[x], 0, 8);
        }
    }
}
