package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.parser.DebugParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by alexanderstolz on 1/1/17.
 */
public class PieceQueenTest {
    DebugParser debugParser = new DebugParser();

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___Q____
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


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[4][5] = new PieceKing(PieceColor.BLACK, 4, 5);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ___K____
     * ________
     * ___Q____
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

        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[4][2] = new PieceKing(PieceColor.BLACK, 4, 2);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * _K_Q____
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


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[2][3] = new PieceKing(PieceColor.BLACK, 2, 3);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___Q_K__
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


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[6][3] = new PieceKing(PieceColor.BLACK, 6, 3);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___Q____
     * ________
     * _K______
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnLeftLowerKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[2][5] = new PieceKing(PieceColor.BLACK, 2, 5);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___Q____
     * ________
     * _____K__
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnRightLowerKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[6][5] = new PieceKing(PieceColor.BLACK, 6, 5);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * _____K__
     * ________
     * ___Q____
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


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[6][1] = new PieceKing(PieceColor.BLACK, 6, 1);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * _K______
     * ________
     * ___Q____
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


        sut.pieces[4][3] = new PieceQueen(PieceColor.WHITE, 4,3);
        sut.pieces[2][1] = new PieceKing(PieceColor.BLACK, 2, 1);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }
}
