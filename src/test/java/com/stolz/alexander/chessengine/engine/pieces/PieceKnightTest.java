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
}
