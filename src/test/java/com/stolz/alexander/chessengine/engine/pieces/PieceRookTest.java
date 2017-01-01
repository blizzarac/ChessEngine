package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by alexanderstolz on 1/1/17.
 */
public class PieceRookTest {

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___R____
     * ________
     * ___K____
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnLowerKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceRook(PieceColor.WHITE, 4,3);
        sut.pieces[4][5] = new PieceKing(PieceColor.BLACK, 4, 5);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ___K____
     * ________
     * ___R____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnUpperKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();

        sut.pieces[4][3] = new PieceRook(PieceColor.WHITE, 4,3);
        sut.pieces[4][2] = new PieceKing(PieceColor.BLACK, 4, 2);

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
     * _K_R____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnLeftKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceRook(PieceColor.WHITE, 4,3);
        sut.pieces[2][3] = new PieceKing(PieceColor.BLACK, 2, 3);

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
     * ___R_K__
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnRightKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceRook(PieceColor.WHITE, 4,3);
        sut.pieces[6][3] = new PieceKing(PieceColor.BLACK, 6, 3);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }
}
