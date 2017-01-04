package com.stolz.alexander.chessengine.engine.logic;

import com.google.inject.Inject;
import com.stolz.alexander.chessengine.engine.pieces.*;
import com.stolz.alexander.chessengine.gui.controls.chessboard.ChessBoardFields;

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
    public Piece[][] pieces;
    public PieceColor currentPlayer;
    public boolean winner = false;
    private int endGame = 0;
    public String initialFen;
    public int moveNumber;
    public int initialMoveNumber;
    private String fen;

    @Inject
    public ChessBoard() {
        currentPlayer = WHITE;
        pieces = new Piece[ChessBoardFields.boardWidth][ChessBoardFields.boardHeight];
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
        // White Pieces
        pieces[7][7] = new PieceRook(WHITE, 7, 7);
        pieces[6][7] = new PieceKnight(WHITE, 6, 7);
        pieces[5][7] = new PieceBishop(WHITE, 5, 7);
        pieces[4][7] = new PieceKing(WHITE, 4, 7);
        pieces[3][7] = new PieceQueen(WHITE, 3, 7);
        pieces[2][7] = new PieceBishop(WHITE, 2, 7);
        pieces[1][7] = new PieceKnight(WHITE, 1, 7);
        pieces[0][7] = new PieceRook(WHITE, 0, 7);
        // White Pawns
        pieces[7][6] = new PiecePawn(WHITE, 7, 6, true);
        pieces[6][6] = new PiecePawn(WHITE, 6, 6, true);
        pieces[5][6] = new PiecePawn(WHITE, 5, 6, true);
        pieces[4][6] = new PiecePawn(WHITE, 4, 6, true);
        pieces[3][6] = new PiecePawn(WHITE, 3, 6, true);
        pieces[2][6] = new PiecePawn(WHITE, 2, 6, true);
        pieces[1][6] = new PiecePawn(WHITE, 1, 6, true);
        pieces[0][6] = new PiecePawn(WHITE, 0, 6, true);

        // Empty Pieces
        for (int x = 5; x > 1; x--) {
            for (int j = 0; j < 8; j++) {
                pieces[j][x] = new Empty(j, x);
            }
        }

        // Black Pieces
        pieces[7][0] = new PieceRook(BLACK, 7, 0);
        pieces[6][0] = new PieceKnight(BLACK, 6, 0);
        pieces[5][0] = new PieceBishop(BLACK, 5, 0);
        pieces[4][0] = new PieceKing(BLACK, 4, 0);
        pieces[3][0] = new PieceQueen(BLACK, 3, 0);
        pieces[2][0] = new PieceBishop(BLACK, 2, 0);
        pieces[1][0] = new PieceKnight(BLACK, 1, 0);
        pieces[0][0] = new PieceRook(BLACK, 0, 0);
        // Pawns
        pieces[7][1] = new PiecePawn(BLACK, 7, 1, true);
        pieces[6][1] = new PiecePawn(BLACK, 6, 1, true);
        pieces[5][1] = new PiecePawn(BLACK, 5, 1, true);
        pieces[4][1] = new PiecePawn(BLACK, 4, 1, true);
        pieces[3][1] = new PiecePawn(BLACK, 3, 1, true);
        pieces[2][1] = new PiecePawn(BLACK, 2, 1, true);
        pieces[1][1] = new PiecePawn(BLACK, 1, 1, true);
        pieces[0][1] = new PiecePawn(BLACK, 0, 1, true);
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

    @FunctionalInterface
    interface Function2 <A, B, R> {
        //R is like Return, but doesn't have to be last in the list nor named R.
        R apply (A a, B b);
    }

    public String printBoard() {
        StringBuilder builder = new StringBuilder();

        Function2<Piece, String, String> blackWhite =
                (Piece piece, String sym) -> piece.getColor()==BLACK?sym.toLowerCase():sym;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces[y][x].getType() == PieceType.NOTYPE) {
                    builder.append("_");
                } else if (pieces[y][x].getType() == PieceType.PAWN) {
                     builder.append(blackWhite.apply(pieces[y][x], "P"));
                } else if (pieces[y][x].getType() == PieceType.KING) {
                    builder.append(blackWhite.apply(pieces[y][x], "K"));
                } else if (pieces[y][x].getType() == PieceType.QUEEN) {
                    builder.append(blackWhite.apply(pieces[y][x], "Q"));
                } else if (pieces[y][x].getType() == PieceType.BISHOP) {
                    builder.append(blackWhite.apply(pieces[y][x], "B"));
                } else if (pieces[y][x].getType() == PieceType.KNIGHT) {
                    builder.append(blackWhite.apply(pieces[y][x], "N"));
                } else if (pieces[y][x].getType() == PieceType.ROOK) {
                    builder.append(blackWhite.apply(pieces[y][x], "R"));
                }
            }
            builder.append("\n");
        }

        return builder.toString();
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
