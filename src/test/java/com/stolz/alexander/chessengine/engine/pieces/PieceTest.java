package com.stolz.alexander.chessengine.engine.pieces;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    void moveShouldWork() {
        // given
        PiecePawn pawn = new PiecePawn(PieceColor.BLACK, 0, 2, true);
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(i, j);
            }
        }

        board[pawn.x()][pawn.y()] = pawn;

        // when
        Piece[][] resultBoard = pawn.move(board, new PiecePosition(2,3));

        // then
        Assertions.assertTrue(resultBoard[0][2] instanceof Empty, "");
        Assertions.assertTrue(resultBoard[2][3] instanceof PiecePawn, "");
    }

    @Test
    void moveShouldNotWorkOutOfBounds() {
        // given
        PiecePawn pawn = new PiecePawn(PieceColor.BLACK, 0, 2, true);
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(i, j);
            }
        }

        board[pawn.x()][pawn.y()] = pawn;

        // when/then
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> pawn.move(board, new PiecePosition(-1,3)));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> pawn.move(board, new PiecePosition(9,3)));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> pawn.move(board, new PiecePosition(2,-1)));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> pawn.move(board, new PiecePosition(2,9)));
    }

    @Test
    void moveShouldCorrectlyUpdatePiecePosition() {
        // given
        PiecePawn pawn = new PiecePawn(PieceColor.BLACK, 0, 2, true);
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(i, j);
            }
        }

        board[pawn.x()][pawn.y()] = pawn;

        Assertions.assertTrue(board[0][2].getPiecePosition().x == 0, "");
        Assertions.assertTrue(board[0][2].getPiecePosition().y == 2, "");
        Assertions.assertTrue(board[2][3].getPiecePosition().x == 2, "");
        Assertions.assertTrue(board[2][3].getPiecePosition().y == 3, "");

        // when
        Piece[][] resultBoard = pawn.move(board, new PiecePosition(2,3));

        // then
        Assertions.assertTrue(resultBoard[0][2].getPiecePosition().x == 0, "");
        Assertions.assertTrue(resultBoard[0][2].getPiecePosition().y == 2, "");
        Assertions.assertTrue(resultBoard[2][3].getPiecePosition().x == 2, "");
        Assertions.assertTrue(resultBoard[2][3].getPiecePosition().y == 3, "");
    }
}
