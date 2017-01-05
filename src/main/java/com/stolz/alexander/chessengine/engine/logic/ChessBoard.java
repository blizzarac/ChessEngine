package com.stolz.alexander.chessengine.engine.logic;

import com.google.inject.Inject;
import com.stolz.alexander.chessengine.engine.pieces.*;
import com.stolz.alexander.chessengine.gui.controls.chessboard.ChessBoardFields;
import com.stolz.alexander.chessengine.gui.controls.main.Main;
import com.stolz.alexander.chessengine.parser.FENParser;

import java.util.Arrays;
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
    private final FENParser fenParser = Main.injector.getInstance(FENParser.class);

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
        final ChessBoard parseResult = fenParser.parse(initialFen);

        this.pieces = parseResult.pieces;
        this.currentPlayer = parseResult.currentPlayer;
    }

    /**
     * Move piece on the board. NOT checking correct positioning.
     *
     * @param target Target position
     * @param pieceToMove The piece to move
     * @return
     */
    public void move(Piece pieceToMove, PiecePosition target) {
        int currX = pieceToMove.x();
        int currY = pieceToMove.y();
        int targetX = target.x;
        int targetY = target.y;

        final Piece tmp =  pieces[targetX][targetY]; // Saving target

        // Update target piece
        pieces[targetX][targetY] = pieces[currX][currY];
        pieces[targetX][targetY].piecePosition.x = targetX;
        pieces[targetX][targetY].piecePosition.y = targetY;

        // Update this
        if (tmp.getType() == PieceType.NOTYPE) {
            pieces[currX][currY] = tmp;
            pieces[currX][currY].piecePosition.x = currX;
            pieces[currX][currY].piecePosition.y = currY;
        } else {
            pieces[currX][currY] = new Empty(currX, currY);
        }

        if (pieces[targetX][targetY] instanceof WithFirstmove) {
            WithFirstmove piece = (WithFirstmove)pieces[targetX][targetY];
            if(piece.isFirstmove()){
                piece.setFirstmove(false);
            }
        }

        fenHistory.push(fenParser.export(this));
    }

    public void undoMove() {
        fenHistory.pop();
        String lastMove = fenHistory.peek();

        final FENParser fenParser = Main.injector.getInstance(FENParser.class);
        ChessBoard oldState = fenParser.parse(lastMove);
        this.pieces = oldState.pieces;
        this.currentPlayer = oldState.currentPlayer;
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
