package com.stolz.alexander.chessengine.parser;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.engine.pieces.*;

import java.net.Inet4Address;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.BLACK;

/**
 * Created by astolz on 03.01.2017.
 */
public class FENParser {

    public ChessBoard parse(String fen){
        final ChessBoard board = new ChessBoard();
        board.initEmpty();

        String[] parts = fen.split("/");
        int row = 0;
        int col = 0;

        for (int idx = 0; idx < 8; idx++) {
            for (int idx2 = 0; idx2 < 8 && col < 8; idx2++) {
                char pos = parts[idx].toCharArray()[idx2];


                if (String.valueOf(pos).matches("-?\\d+(\\.\\d+)?")){
                    int numberOfEmpties = Integer.parseInt(String.valueOf(pos));
                    col += numberOfEmpties - 1;
                }

                Piece createdPiece = symToPiece(pos);
                if (createdPiece != null) {
                    board.pieces[col][row] = createdPiece;
                }

                col += 1;
            }

            row += 1;
            col = 0;
        }

        parseGameInformation(parts[7].substring(9), board);

        return board;
    }

    private void parseGameInformation(String info, ChessBoard board) {
        String [] infoParts = info.split(" ");
        board.currentPlayer = infoParts[0].equals("w")?PieceColor.WHITE:PieceColor.BLACK;
    }

    private Piece symToPiece(char sym) {
        Piece result = null;
        PieceColor color = Character.isUpperCase(sym)?PieceColor.WHITE:PieceColor.BLACK;
        switch (Character.toLowerCase(sym)){
            case 'r':
                result = new PieceRook(color, 0,0);
                break;
            case 'n':
                result = new PieceKnight(color, 0,0);
                break;
            case 'b':
                result = new PieceBishop(color, 0,0);
                break;
            case 'q':
                result = new PieceQueen(color, 0,0);
                break;
            case 'k':
                result = new PieceKing(color, 0,0);
                break;
            case 'p':
                result = new PiecePawn(color, 0,0, true);
                break;
        }

        return result;
    }

    @FunctionalInterface
    interface Function2 <A, B, R> {
        //R is like Return, but doesn't have to be last in the list nor named R.
        R apply (A a, B b);
    }

    public String export(ChessBoard board) {
        Piece [][] pieces = board.pieces;
        StringBuilder builder = new StringBuilder();
        int emptyCounter = 0;

        Function2<Piece, String, String> blackWhite =
                (Piece piece, String sym) -> piece.getColor()==BLACK?sym.toLowerCase():sym;

        final Consumer<Integer> emptyCheck = (Integer counter) -> {if(counter>0){builder.append(counter);}};

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces[y][x].getType() == PieceType.NOTYPE) {
                    emptyCounter++;
                } else if (pieces[y][x].getType() == PieceType.PAWN) {
                    emptyCheck.accept(emptyCounter);
                    emptyCounter = 0;
                    builder.append(blackWhite.apply(pieces[y][x], "P"));
                } else if (pieces[y][x].getType() == PieceType.KING) {
                    emptyCheck.accept(emptyCounter);
                    emptyCounter = 0;
                    builder.append(blackWhite.apply(pieces[y][x], "K"));
                } else if (pieces[y][x].getType() == PieceType.QUEEN) {
                    emptyCheck.accept(emptyCounter);
                    emptyCounter = 0;
                    builder.append(blackWhite.apply(pieces[y][x], "Q"));
                } else if (pieces[y][x].getType() == PieceType.BISHOP) {
                    emptyCheck.accept(emptyCounter);
                    emptyCounter = 0;
                    builder.append(blackWhite.apply(pieces[y][x], "B"));
                } else if (pieces[y][x].getType() == PieceType.KNIGHT) {
                    emptyCheck.accept(emptyCounter);
                    emptyCounter = 0;
                    builder.append(blackWhite.apply(pieces[y][x], "N"));
                } else if (pieces[y][x].getType() == PieceType.ROOK) {
                    emptyCheck.accept(emptyCounter);
                    emptyCounter = 0;
                    builder.append(blackWhite.apply(pieces[y][x], "R"));
                }
            }
            emptyCheck.accept(emptyCounter);
            emptyCounter = 0;
            if (x < 7) builder.append("/");
        }

        return builder.toString();
    }

}
