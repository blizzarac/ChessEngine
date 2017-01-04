package com.stolz.alexander.chessengine.parser;

import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.engine.pieces.*;

import java.net.Inet4Address;

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
                    System.out.print(board.printBoard() + "\n");
                }

                col += 1;
            }

            row += 1;
            col = 0;
        }

        return board;
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

    public String export(ChessBoard board) {
        return null;
    }

}
