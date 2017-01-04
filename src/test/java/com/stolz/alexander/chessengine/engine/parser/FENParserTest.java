package com.stolz.alexander.chessengine.engine.parser;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.parser.FENParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 * Created by astolz on 03.01.2017.
 */
public class FENParserTest {

    @Test
    @DisplayName("should create board from FEN for starting Position")
    void test() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

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
                result.printBoard());
    }

    @Test
    @DisplayName("should create board from FEN for starting Position and 1. e4")
    void test1() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        assertEquals(
                "rnbqkbnr\n" +
                        "pppppppp\n" +
                        "________\n" +
                        "________\n" +
                        "____P___\n" +
                        "________\n" +
                        "PPPP_PPP\n" +
                        "RNBQKBNR\n",
                result.printBoard());
    }
}
