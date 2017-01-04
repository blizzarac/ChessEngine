package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceKing;
import com.stolz.alexander.chessengine.engine.pieces.PieceQueen;
import com.stolz.alexander.chessengine.parser.DebugParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class CheckvalidatorTest {

    private DebugParser debugParser = new DebugParser();

    /**
     * Initial Board
     * K_______
     * _Q______
     * __Q_____
     * ________
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    @DisplayName("Should give checkmate on cornered King")
    public void shouldGiveCheckmateOnCorneredKing() {
        // Given
        final ChessBoard chessBoard = new ChessBoard();
        chessBoard.initEmpty();

        final CheckValidator sut = new CheckValidator();

        chessBoard.pieces[1][1] = new PieceQueen(PieceColor.WHITE, 1,1);
        chessBoard.pieces[2][2] = new PieceQueen(PieceColor.WHITE, 2,2);
        chessBoard.pieces[0][0] = new PieceKing(PieceColor.BLACK, 0, 0);

        // When
        System.out.print(debugParser.printBoard(chessBoard));
        final boolean result = sut.isCheckMate(PieceColor.WHITE, chessBoard.pieces);

        // Then
        Assertions.assertTrue(result);
    }

    /**
     * Initial Board
     * K_______
     * ________
     * Q_Q_____
     * ________
     * ________
     * ________
     * ________
     * ________
     *
     */
    @Test
    @DisplayName("Should give not Checkmate on King With Move Left")
    public void shouldGiveNotCheckMateOnKingWithMoveLeft() {
        // Given
        final ChessBoard chessBoard = new ChessBoard();
        chessBoard.initEmpty();

        final CheckValidator sut = new CheckValidator();

        chessBoard.pieces[0][2] = new PieceQueen(PieceColor.WHITE, 0,2);
        chessBoard.pieces[2][2] = new PieceQueen(PieceColor.WHITE, 2,2);
        chessBoard.pieces[0][0] = new PieceKing(PieceColor.BLACK, 0, 0);

        // When
        System.out.print(debugParser.printBoard(chessBoard));
        final boolean result = sut.isCheckMate(PieceColor.BLACK, chessBoard.pieces);

        // Then
        Assertions.assertFalse(result);
    }
}
