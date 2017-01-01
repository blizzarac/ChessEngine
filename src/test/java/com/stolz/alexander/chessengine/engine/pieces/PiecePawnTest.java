package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class PiecePawnTest {

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPPPPP
     * ________
     * ________
     * ________
     * ______P_
     * PPPPPP_P
     * RNBQKBNR
     *
     * the pawn should have two possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnFirstMove() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        final List<PiecePosition> result = sut.pieces[6][6].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(result.size(), 2);
    }

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPPPPP
     * ________
     * ________
     * ________
     * ______P_
     * PPPPPP_P
     * RNBQKBNR
     *
     * the pawn should only have one possible move
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnNextMove() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        sut.pieces[6][6].move(sut.pieces, new PiecePosition(6,5));
        final List<PiecePosition> result = sut.pieces[6][5].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(result.size(), 1);
    }

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPP_PP
     * ________
     * _____P__
     * ______P_
     * ________
     * PPPPPP_P
     * RNBQKBNR
     *
     * the pawn should have two possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnPossibleTake() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        sut.pieces[6][6].move(sut.pieces, new PiecePosition(6,4));
        sut.pieces[5][1].move(sut.pieces, new PiecePosition(5,3));
        //System.out.print(sut.printBoard());
        final List<PiecePosition> result = sut.pieces[6][4].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(result.size(), 2);
    }

    /**
     * Initial Board
     * ________
     * ________
     * __K_____
     * ___P____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnLeftDialogalKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[3][3] = new PiecePawn(PieceColor.WHITE, 3,3, false);
        sut.pieces[2][2] = new PieceKing(PieceColor.BLACK, 2, 2);

        // When
//        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[3][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ____K___
     * ___P____
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldGiveCheckOnRightDialogalKing() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[3][3] = new PiecePawn(PieceColor.WHITE, 3,3, false);
        sut.pieces[4][2] = new PieceKing(PieceColor.BLACK, 4, 2);

        // When
//        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[3][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ____K___
     * ___P____
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


        sut.pieces[4][3] = new PiecePawn(PieceColor.WHITE, 4,3, false);
        sut.pieces[4][2] = new PieceKing(PieceColor.BLACK, 4, 2);

        // When
//        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertFalse(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * P_______
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldNotCrashCheckCalculationOnLeftBorder() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[0][3] = new PiecePawn(PieceColor.WHITE, 0,3, false);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[0][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertFalse(result);
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * _______P
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    public void shouldNotCrashCheckCalculationOnRightBorder() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.initEmpty();


        sut.pieces[7][3] = new PiecePawn(PieceColor.WHITE, 7,3, false);

        // When
        System.out.print(sut.printBoard());
        final boolean result = sut.pieces[7][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertFalse(result);
    }
}
