package com.stolz.alexander.chessengine.engine.logic;

import com.stolz.alexander.chessengine.parser.DebugParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by alexanderstolz on 12/31/16.
 */
public class ChessBoardTest {

    private DebugParser debugParser = new DebugParser();

    @Test
    public void shouldBuildCorrectInitialBoard() {
        // Given
        final ChessBoard sut = new ChessBoard();

        // When
        sut.init();

        // Then
        Assertions.assertEquals(
                "rnbqkbnr\n" +
                        "pppppppp\n" +
                        "________\n" +
                        "________\n" +
                        "________\n" +
                        "________\n" +
                        "PPPPPPPP\n" +
                        "RNBQKBNR\n",
                debugParser.printBoard(sut));
    }
}
