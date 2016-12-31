package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PiecePosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class CheckvalidatorTest {

    /**
     * Initial Board
     * RNBQKBNR
     * PPP_PPPP
     * ___P____
     * _B______
     * ________
     * ____P___
     * PPPP_PPP
     * RNBQK_NR
     * the should be in check
     *
     */
    @Test
    public void shouldGiveCorrectPositionOnSimpleCheckForBishop() {
        // Given
        final ChessBoard chessBoard = new ChessBoard();
        chessBoard.init();
        final CheckValidator sut = new CheckValidator();

        // When
        chessBoard.pieces[4][6].move(chessBoard.pieces, new PiecePosition(4,5));
        chessBoard.pieces[3][1].move(chessBoard.pieces, new PiecePosition(3,2));
        chessBoard.pieces[5][7].move(chessBoard.pieces, new PiecePosition(1,3));
        System.out.print(chessBoard.printBoard());


        final PiecePosition result = sut.check4check(PieceColor.WHITE, chessBoard.pieces);

        // Then
        Assertions.assertTrue(new PiecePosition(4, 0).equals(result));
    }
}
