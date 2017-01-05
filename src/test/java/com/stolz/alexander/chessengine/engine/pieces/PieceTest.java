package com.stolz.alexander.chessengine.engine.pieces;


import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.gui.controls.main.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    void moveShouldWork() {
        // given
        PiecePawn pawn = new PiecePawn(PieceColor.BLACK, 0, 2, true);
        ChessBoard chessBoard = Main.injector.getInstance(ChessBoard.class);
        chessBoard.initEmpty();

        chessBoard.pieces[pawn.x()][pawn.y()] = pawn;

        // when
        chessBoard.move(pawn, new PiecePosition(2,3));

        // then
        Assertions.assertTrue(chessBoard.pieces[0][2] instanceof Empty, "");
        Assertions.assertTrue(chessBoard.pieces[2][3] instanceof PiecePawn, "");
    }

    @Test
    void moveShouldNotWorkOutOfBounds() {
        // given
        PiecePawn pawn = new PiecePawn(PieceColor.BLACK, 0, 2, true);
        ChessBoard chessBoard = Main.injector.getInstance(ChessBoard.class);
        chessBoard.initEmpty();

        chessBoard.pieces[pawn.x()][pawn.y()] = pawn;

        // when/then
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> chessBoard.move(pawn, new PiecePosition(-1,3)));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> chessBoard.move(pawn, new PiecePosition(9,3)));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> chessBoard.move(pawn, new PiecePosition(2,-1)));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> chessBoard.move(pawn, new PiecePosition(2,9)));
    }

    @Test
    void moveShouldUpdatePiecePosition() {
        // given
        PiecePawn pawn = new PiecePawn(PieceColor.BLACK, 0, 2, true);
        ChessBoard chessBoard = Main.injector.getInstance(ChessBoard.class);
        chessBoard.initEmpty();

        chessBoard.pieces[pawn.x()][pawn.y()] = pawn;

        Assertions.assertTrue(chessBoard.pieces[0][2].getPiecePosition().x == 0, "");
        Assertions.assertTrue(chessBoard.pieces[0][2].getPiecePosition().y == 2, "");
        Assertions.assertTrue(chessBoard.pieces[2][3].getPiecePosition().x == 2, "");
        Assertions.assertTrue(chessBoard.pieces[2][3].getPiecePosition().y == 3, "");

        // when
        chessBoard.move(pawn, new PiecePosition(2,3));

        // then
        Assertions.assertTrue(chessBoard.pieces[0][2].getPiecePosition().x == 0, "");
        Assertions.assertTrue(chessBoard.pieces[0][2].getPiecePosition().y == 2, "");
        Assertions.assertTrue(chessBoard.pieces[2][3].getPiecePosition().x == 2, "");
        Assertions.assertTrue(chessBoard.pieces[2][3].getPiecePosition().y == 3, "");
    }

    @Test
    void moveShouldShouldOverwritePieceOnTake() {
        // given
        ChessBoard chessBoard = Main.injector.getInstance(ChessBoard.class);
        chessBoard.initEmpty();

        chessBoard.pieces[3][3] = new PiecePawn(PieceColor.WHITE, 3, 3, true);
        chessBoard.pieces[2][2] = new PiecePawn(PieceColor.BLACK, 2, 2, true);

        // when
        chessBoard.move(chessBoard.pieces[3][3], new PiecePosition(2, 2));

        // then
        Assertions.assertEquals(chessBoard.pieces[3][3].getType(), PieceType.NOTYPE);
        Assertions.assertEquals(chessBoard.pieces[2][2].getType(), PieceType.PAWN);
    }
}
