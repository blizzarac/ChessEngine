package com.stolz.alexander.chessengine.engine.logic;

import com.google.inject.Inject;
import com.stolz.alexander.chessengine.engine.pieces.*;
import com.stolz.alexander.chessengine.gui.controls.chessboard.ChessBoardFields;
import com.stolz.alexander.chessengine.gui.controls.main.Main;
import com.stolz.alexander.chessengine.parser.FENParser;

import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

/**
 * Created by alexanderstolz on 12/9/16.
 */
public class ChessBoard {
    public static String initialFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public Piece[][] pieces;
    public PieceColor currentPlayer;
    public boolean winner = false;
    private int endGame = 0;

    public int moveNumber;
    public int initialMoveNumber;
    private String fen;

    private Stack<String> fenHistory;

    @Inject
    public ChessBoard() {
        fenHistory = new Stack<>();
        currentPlayer = WHITE;
        pieces = new Piece[ChessBoardFields.boardWidth][ChessBoardFields.boardHeight];

        fenHistory.push(initialFen);
    }

    public void initEmpty() {
        // Empty Pieces
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                pieces[j][x] = new Empty(j, x);
            }
        }
    }

    /**
     * initialize the inital layout of piecesw
     *
     * @return
     */
    public void init() {
        final FENParser fenParser = Main.injector.getInstance(FENParser.class);
        final ChessBoard parseResult = fenParser.parse(initialFen);

        this.pieces = parseResult.pieces;
        this.currentPlayer = parseResult.currentPlayer;
    }

    public void replacePieces(Piece[][] newboard) {
        for (int x = 0; x < 8; x++) {
            System.arraycopy(newboard[x], 0, pieces[x], 0, 8);
        }
    }

    public Piece[][] backupPieces(Piece[][] boardstate) {
        final Piece[][] backup = new Piece[8][8];
        for (int x = 0; x < 8; x++) {
            System.arraycopy(boardstate[x], 0, backup[x], 0, 8);
        }
        return backup;
    }



    public int isEndGame() {
        return endGame;
    }

    public char[] getSanMove(int i) {
        return null;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }
}
