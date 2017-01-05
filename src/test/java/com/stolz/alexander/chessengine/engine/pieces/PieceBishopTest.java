package com.stolz.alexander.chessengine.engine.pieces;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.parser.DebugParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class PieceBishopTest {
    private DebugParser debugParser = new DebugParser();

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
        sut.move(sut.pieces[3][6], new PiecePosition(3,5));
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
        //System.out.print(debugParser.printBoard(sut));
        //System.out.print(sut.pieces[2][7].getType());
        sut.move(sut.pieces[3][6], new PiecePosition(3,5));
        sut.move(sut.pieces[6][1], new PiecePosition(6,3));
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
        // System.out.print(debugParser.printBoard(sut));
        sut.move(sut.pieces[2][7], new PiecePosition(3,3));
        final List<PiecePosition> result = sut.pieces[3][3].findValidMoves(sut.pieces);

        // Then
        Assertions.assertEquals(8, result.size());
    }

    /**
     * Initial Board
     * ________
     * ________
     * ________
     * ___N____
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


        sut.pieces[4][3] = new PieceBishop(PieceColor.WHITE, 4,3);
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
     * ___N____
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


        sut.pieces[4][3] = new PieceBishop(PieceColor.WHITE, 4,3);
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


        sut.pieces[4][3] = new PieceBishop(PieceColor.WHITE, 4,3);
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


        sut.pieces[4][3] = new PieceBishop(PieceColor.WHITE, 4,3);
        sut.pieces[2][1] = new PieceKing(PieceColor.BLACK, 2, 1);

        // When
        System.out.print(debugParser.printBoard(sut));
        final boolean result = sut.pieces[4][3].isCheck(sut.pieces, PieceColor.WHITE);

        // Then
        Assertions.assertTrue(result);
    }
}
