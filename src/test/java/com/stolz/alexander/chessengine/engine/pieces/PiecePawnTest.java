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

}
