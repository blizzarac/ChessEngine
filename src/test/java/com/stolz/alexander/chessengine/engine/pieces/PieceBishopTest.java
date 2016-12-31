package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class PieceBishopTest {
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
     * the Bishop should have 0 possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnCleanBoard() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        final List<PiecePosition> result = sut.pieces[2][7].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(0, result.size());
    }

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPPPPP
     * ________
     * ________
     * ________
     * ___P____
     * PPP_PPPP
     * RNBQKBNR
     *
     * the Bishop should have 5 possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnPawnOpeningBoard() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        sut.pieces[3][6].move(sut.pieces, new PiecePosition(3,5));
        final List<PiecePosition> result = sut.pieces[2][7].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(5, result.size());
    }

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPPP_P
     * ________
     * ______P_
     * ________
     * ___P____
     * PPP_PPPP
     * RNBQKBNR
     *
     * the Bishop should have 4 possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnPawnOpeningAndBlockedLine() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        //System.out.print(sut.printBoard());
        //System.out.print(sut.pieces[2][7].getType());
        sut.pieces[3][6].move(sut.pieces, new PiecePosition(3,5));
        sut.pieces[6][1].move(sut.pieces, new PiecePosition(6,3));
        final List<PiecePosition> result = sut.pieces[2][7].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(4, result.size());
    }

    /**
     * Initial Board
     * RNBQKBNR
     * PPPPPPPP
     * ________
     * ___B____
     * ________
     * ________
     * PPPPPPPP
     * RN_QKBNR
     *
     * the Bishop should have 8 possible moves
     *
     */
    @Test
    public void shouldGiveCorrectValidMovesOnFourOpenLines() {
        // Given
        final ChessBoard sut = new ChessBoard();
        sut.init();

        // When
        //System.out.print(sut.pieces[2][7].getType());
        // System.out.print(sut.printBoard());
        sut.pieces[2][7].move(sut.pieces, new PiecePosition(3,3));
        final List<PiecePosition> result = sut.pieces[3][3].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(8, result.size());
    }
}
