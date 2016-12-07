package com.stolz.alexander.chessengine.gui.controls;

import com.stolz.alexander.chessengine.pieces.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.stolz.alexander.chessengine.pieces.Piece.PieceColor.BLACK;
import static com.stolz.alexander.chessengine.pieces.Piece.PieceColor.WHITE;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public class MoveValidator {
    public void validMoves(Piece p, Piece [][] pieces, Rectangle[][] board){
        p.drawValidMoves(pieces, board);

        whitePawns(p, pieces, board);
        blackPawns(p, pieces, board);
        whiteRooks(p, pieces, board);
        blackRook(p, pieces, board);
        whiteBishop(p, pieces, board);
        blackBishop(p, pieces, board);
        whiteKnight(p, pieces, board);
        blackKnight(p, pieces, board);
        whiteQueen(p, pieces, board);
        blackQueen(p, pieces, board);
        whiteKing(p, pieces, board);
        blackKing(p, pieces, board);


    }

    private void blackKing(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //_________________________________BLACKKING_____________________________________//
        if(p.toString() == "King" && p.type() == BLACK){
            // Up
            if(p.jcoord()-1 >= 0){
                if(pieces[p.icoord()][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()-1].type()==WHITE){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - right
            if(p.jcoord()-1 >= 0 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-1].type()==WHITE){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - left
            if(p.jcoord()-1 >= 0 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-1].type()==WHITE){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Left
            if(p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()].type()==WHITE){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Right
            if(p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()].type()==WHITE){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Down
            if(p.jcoord()+1 < 8){
                if(pieces[p.icoord()][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()+1].type()==WHITE){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - left
            if(p.jcoord()+1 < 8 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+1].type()==WHITE){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - right
            if(p.jcoord()+1 < 8 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+1].type()==WHITE){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
        }
    }

    private void whiteKing(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //_________________________________WHITEKING_____________________________________//
        if(p.toString() == "King" && p.type() == WHITE){
            // Up
            if(p.jcoord()-1 >= 0){
                if(pieces[p.icoord()][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - right
            if(p.jcoord()-1 >= 0 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - left
            if(p.jcoord()-1 >= 0 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Left
            if(p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()].type() == BLACK){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Right
            if(p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()].type() == BLACK){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Down
            if(p.jcoord()+1 < 8){
                if(pieces[p.icoord()][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()+1].type() == BLACK){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - left
            if(p.jcoord()+1 < 8 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+1].type() == BLACK){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - right
            if(p.jcoord()+1 < 8 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+1].type() == BLACK){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
        }
    }

    private void blackQueen(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //______________________________BLACKQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.type() == BLACK){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=-1;y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=8;y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=8;y=8;
                }
            }

            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type() == BLACK){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type() == BLACK){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type() == BLACK){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type() == BLACK && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void whiteQueen(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //______________________________WHITEQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.type() == WHITE){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8; y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=8; y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=8;y=8;
                }
            }

            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type() == BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type()==WHITE){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type() == BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type()==WHITE){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type() == BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type()==WHITE){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type() == BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type()==WHITE && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void blackKnight(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //_________________________________BLACKKNIGHT_____________________________________//
        if(p.toString() == "Knight" && p.type() == BLACK){
            // Up and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-2].type()==WHITE){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()-1].type()==WHITE){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-2].type()==WHITE){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()-1].type()==WHITE){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+2].type()==WHITE){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()+1].type()==WHITE){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+2].type()==WHITE){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()+1].type()==WHITE){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

        }
    }

    private void whiteKnight(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //_________________________________WHITEKNIGHT_____________________________________//

        // Assuming knights can jump regardless of what pieces are in the way

        if(p.toString() == "Knight" && p.type() == WHITE){
            // Up and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-2].type() == BLACK){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-2].type() == BLACK){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+2].type() == BLACK){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()+1].type() == BLACK){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+2].type() == BLACK){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()+1].type() == BLACK){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
        }
    }

    private void blackBishop(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //__________________________________________BLACKBISHOP_____________________________________//
        if(p.toString() == "Bishop" && p.type() == BLACK){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=8;y = -1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x= -1; y = 8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x = -1; y = 8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==WHITE){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type() == BLACK){
                    // Stop Looking
                    x=8;y=8;
                }
            }
        }
    }

    private void whiteBishop(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //__________________________________________WHITEBISHOP_____________________________________//
        if(p.toString() == "Bishop" && p.type() == WHITE){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8; y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=8; y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=-1; y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type() == BLACK){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type()==WHITE){
                    // Stop Looking
                    x=8;y=8;
                }
            }
        }
    }

    private void blackRook(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //__________________________________________BLACKROOK_____________________________________//

        if(p.toString() == "Rook" && p.type() == BLACK){
            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type() == BLACK){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type() == BLACK){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==WHITE){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type() == BLACK){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==WHITE){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type() == BLACK){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void whiteRooks(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //__________________________________________WHITEROOK_____________________________________//
        if(p.toString() == "Rook" && p.type() == WHITE){
            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type() == BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type()==WHITE){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type() == BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type()==WHITE){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type() == BLACK){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type()==WHITE){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type() == BLACK){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type()==WHITE && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }
    }

    private void blackPawns(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //_____________________________________BLACKPAWNS_____________________________________//
        if(p.toString() == "Pawn" && p.type() == BLACK){
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()+1 < 8){ // Guard for bounds
                if(pieces[p.icoord()][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.icoord()][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty") && pieces[p.icoord()][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()-1][p.jcoord()+1].type() == WHITE){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].type() == WHITE){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}
        }
    }

    private void whitePawns(Piece p, Piece[][] pieces, Rectangle[][] board) {
        //_____________________________________WHITEPAWNS_____________________________________//
        if(p.toString() == "Pawn" && p.type() == WHITE){
            // LOOK ONE SQUARE AHEAD IF CLEAR HIGHLIGHT
            if(p.jcoord()-1 >= 0){ // Guard for bounds
                if(pieces[p.icoord()][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }}

            if(p.firstmove() == true){
                // LOOK TWO SQUARE AHEAD IF CLEAR HIGHLIGHT
                if(pieces[p.icoord()][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty") && pieces[p.icoord()][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }}

            // LOOK ONE SQUARE LEFT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()-1 >= 0 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()+1][p.jcoord()-1].type() == BLACK){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}
        }
    }
}
