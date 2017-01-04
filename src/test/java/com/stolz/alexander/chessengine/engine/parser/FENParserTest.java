package com.stolz.alexander.chessengine.engine.parser;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.parser.DebugParser;
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
    DebugParser debugParser = new DebugParser();

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
                debugParser.printBoard(result));
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
                debugParser.printBoard(result));
    }

    @Test
    @DisplayName("should create board from FEN with white to move")
    void test2() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        Assertions.assertEquals(result.currentPlayer, PieceColor.WHITE);
    }

    @Test
    @DisplayName("should create board from FEN with black to move")
    void test3() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        Assertions.assertEquals(result.currentPlayer, PieceColor.BLACK);
    }

    @Test
    @DisplayName("should create board from FEN with correct Castling availability")
    void test4() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        Assertions.assertTrue(false);
    }

    @Test
    @DisplayName("should create board from FEN with correct En passant")
    void test5() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        Assertions.assertTrue(false);
    }

    @Test
    @DisplayName("should create board from FEN with correct Halfmove clock")
    void test6() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        Assertions.assertTrue(false);
    }

    @Test
    @DisplayName("should create board from FEN with correct Fullmove clock")
    void test7() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";

        // When
        final ChessBoard result = sut.parse(startFAN);

        // Then
        Assertions.assertTrue(false);
    }

    @Test
    @DisplayName("should produce correct FEN from Board for starting Position")
    void test8() {
        // Given
        final FENParser sut = new FENParser();
        ChessBoard board = new ChessBoard();
        board.init();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        // When
        final String result = sut.export(board);

        // Then
        Assertions.assertTrue(startFAN.contains(result));
    }

    @Test
    @DisplayName("should produce correct FEN from Board for non starting Position")
    void test9() {
        // Given
        final FENParser sut = new FENParser();
        final String startFAN = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        ChessBoard board = sut.parse(startFAN);

        // When
        final String result = sut.export(board);

        // Then
        Assertions.assertTrue(startFAN.contains(result));
    }

    @Test
    @DisplayName("should produce FEN from Board with correct current player black")
    void test10() {
        // Given
        final FENParser sut = new FENParser();
        ChessBoard board = new ChessBoard();
        board.init();
        board.currentPlayer = PieceColor.BLACK;

        // When
        final String result = sut.export(board);
        final String[] splitResult = result.split(" ");

        // Then
        Assertions.assertTrue(splitResult[1].equals("b"));
    }

    @Test
    @DisplayName("should produce FEN from Board with correct current player white")
    void test11() {
        // Given
        final FENParser sut = new FENParser();
        ChessBoard board = new ChessBoard();
        board.init();

        // When
        final String result = sut.export(board);
        final String[] splitResult = result.split(" ");

        // Then
        Assertions.assertTrue(splitResult[1].equals("w"));
    }


}
