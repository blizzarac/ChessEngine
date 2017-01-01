package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class PieceKnightTest {

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPPPPP
     * ________
     * ________
     * ________
     * ________
     * PPPPPPPP
     * RNBQKBNR
     *
     * the Knight should have two possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnFirstMove() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        //System.out.print(sut.printBoard());
        //System.out.print(sut.pieces[1][7].getType());
        final List<PiecePosition> result = sut.pieces[1][7].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(result.size(), 2);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ___K____
     * ___N____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveNotCheckOnFrontKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceKnight(PieceColor.WHITE, 4,3);
        sut.pieces[4][2] = new PieceKing(PieceColor.BLACK, 4, 2);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertFalse(result);
    }

    /**
     * Initial Board
     * ________
     * __K_____
     * ________
     * ___N____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnLeftUpperKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceKnight(PieceColor.WHITE, 4,3);
        sut.pieces[3][1] = new PieceKing(PieceColor.BLACK, 3, 1);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ____K___
     * ________
     * ___N____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnRightUpperKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceKnight(PieceColor.WHITE, 4,3);
        sut.pieces[5][1] = new PieceKing(PieceColor.BLACK, 5, 1);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___N____
     * ________
     * __K_____
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnLeftLowerKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceKnight(PieceColor.WHITE, 4,3);
        sut.pieces[3][5] = new PieceKing(PieceColor.BLACK, 3, 5);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___N____
     * ________
     * ____K___
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnRightLowerKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceKnight(PieceColor.WHITE, 4,3);
        sut.pieces[5][5] = new PieceKing(PieceColor.BLACK, 5, 5);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }
}
