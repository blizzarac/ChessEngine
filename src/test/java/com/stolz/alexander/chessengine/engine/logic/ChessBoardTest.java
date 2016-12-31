package com.stolz.alexander.chessengine.engine.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class ChessBoardTest {

    @Test
    public void shouldBuildCorrectInitialBoard() {
        // Given
        final ChessBoard sut = new ChessBoard();

        // When
        sut.init();

        // Then
        Assertions.assertEquals(sut.printBoard(),
                "RNBQKBNR\n" +
                        "PPPPPPPP\n" +
                        "________\n" +
                        "________\n" +
                        "________\n" +
                        "________\n" +
                        "PPPPPPPP\n" +
                        "RNBQKBNR\n");
    }
}