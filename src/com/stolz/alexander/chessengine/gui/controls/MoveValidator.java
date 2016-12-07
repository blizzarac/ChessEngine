package com.stolz.alexander.chessengine.gui.controls;

import com.stolz.alexander.chessengine.pieces.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by alexanderstolz on 12/7/16.
 */
public class MoveValidator {
    public void validMoves(Piece p, Piece [][] pieces, Rectangle[][] board){

        //_____________________________________WHITEPAWNS_____________________________________//
        if(p.toString() == "Pawn" && p.type() == 1){
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
                if(pieces[p.icoord()-1][p.jcoord()-1].type() == 2){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()-1 >= 0){
                if(pieces[p.icoord()+1][p.jcoord()-1].type() == 2){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }}
        }

        //_____________________________________BLACKPAWNS_____________________________________//
        if(p.toString() == "Pawn" && p.type() == 2){
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
                if(pieces[p.icoord()-1][p.jcoord()+1].type() == 1){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}

            // LOOK ONE SQUARE RIGHT DIAGONALLY IF ENEMY PRESENT HIGHLIGHT
            if(p.icoord()+1 < 8 && p.jcoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].type() == 1){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }}
        }

        //__________________________________________WHITEROOK_____________________________________//
        if(p.toString() == "Rook" && p.type() == 1){
            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==2){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type()==1){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==2){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type()==1){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==2){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type()==1){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==2){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type()==1 && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }

        //__________________________________________BLACKROOK_____________________________________//

        if(p.toString() == "Rook" && p.type() == 2){
            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==1){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type()==2){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==1){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type()==2){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==1){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type()==2){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==1){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type()==2){
                    // Stop looking
                    y=8;
                }
            }
        }

        //__________________________________________WHITEBISHOP_____________________________________//
        if(p.toString() == "Bishop" && p.type() == 1){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8; y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=8; y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=-1; y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=8;y=8;
                }
            }
        }
        //__________________________________________BLACKBISHOP_____________________________________//
        if(p.toString() == "Bishop" && p.type() == 2){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=8;y = -1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x= -1; y = 8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type()==2){
                    // Stop Looking
                    x = -1; y = 8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=8;y=8;
                }
            }
        }

        //_________________________________WHITEKNIGHT_____________________________________//

        // Assuming knights can jump regardless of what pieces are in the way

        if(p.toString() == "Knight" && p.type() == 1){
            // Up and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-2].type()==2){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()-1].type()==2){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-2].type()==2){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()-1].type()==2){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+2].type()==2){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()+1].type()==2){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+2].type()==2){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()+1].type()==2){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
        }

        //_________________________________BLACKKNIGHT_____________________________________//
        if(p.toString() == "Knight" && p.type() == 2){
            // Up and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-2].type()==1){
                    board[p.icoord()-1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()-1].type()==1){
                    board[p.icoord()-2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()-2 >= 0){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()-2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-2].type()==1){
                    board[p.icoord()+1][p.jcoord()-2].setStroke(Color.AQUAMARINE);
                }
            }

            // Up and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()-1 >= 0){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()-1].type()==1){
                    board[p.icoord()+2][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (first)
            if(p.icoord()+1 < 8 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()+1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+2].type()==1){
                    board[p.icoord()+1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and right (second)
            if(p.icoord()+2 < 8 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()+2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+2][p.jcoord()+1].type()==1){
                    board[p.icoord()+2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (first)
            if(p.icoord()-1 >= 0 && p.jcoord()+2 < 8){				// Bound check
                if(pieces[p.icoord()-1][p.jcoord()+2].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+2].type()==1){
                    board[p.icoord()-1][p.jcoord()+2].setStroke(Color.AQUAMARINE);
                }
            }

            // Bottom and left (second)
            if(p.icoord()-2 >= 0 && p.jcoord()+1 < 8){				// Bound check
                if(pieces[p.icoord()-2][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-2][p.jcoord()+1].type()==1){
                    board[p.icoord()-2][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

        }

        //______________________________WHITEQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.type() == 1){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1; y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=-1; y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8; y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=8; y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==2){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type()==1){
                    // Stop Looking
                    x=8;y=8;
                }
            }

            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==2){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type()==1){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==2){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type()==1){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==2){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type()==1){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==2){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type()==1 && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }

        //______________________________BLACKQUEEN_____________________________________//
        if(p.toString() == "Queen" && p.type() == 2){
            // Look up .. (left)
            for(int y=p.jcoord()-1, x=p.icoord()-1; y >= 0 && x >= 0; y--,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=-1;
                }
                if(x!=-1 && y!=-1 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=-1;y=-1;
                }
            }

            // Look up .. (right)
            for(int y=p.jcoord()-1, x=p.icoord()+1; y >= 0 && x < 8; y--,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=-1;
                }
                if(x!=8 && y!=-1 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=8;y=-1;
                }
            }

            // Look down .. (left)
            for(int y=p.jcoord()+1, x=p.icoord()-1; y < 8 && x >= 0; y++,x--){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=-1;y=8;
                }
                if(x!=-1 && y!=8 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=-1;y=8;
                }
            }

            // Look down .. (right)
            for(int y=p.jcoord()+1, x=p.icoord()+1; y < 8 && x < 8; y++,x++){
                if(pieces[x][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][y].type()==1){
                    board[x][y].setStroke(Color.AQUAMARINE);
                    // Stop Looking
                    x=8;y=8;
                }
                if(x!=8 && y!=8 && pieces[x][y].type()==2){
                    // Stop Looking
                    x=8;y=8;
                }
            }

            // Look Up ..
            for(int y = p.jcoord()-1; y >= 0; y--){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==1){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=-1;
                }
                if(y!=-1 && pieces[p.icoord()][y].type()==2){
                    // Stop looking
                    y=-1;
                }
            }

            // Look Right ..
            for(int x = p.icoord()+1; x < 8; x++){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==1){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=8;
                }
                if(x!=8 && pieces[x][p.jcoord()].type()==2){
                    // Stop looking
                    x=8;
                }
            }

            // Look Left ..
            for(int x = p.icoord()-1; x >= 0; x--){
                if(pieces[x][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[x][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[x][p.jcoord()].type()==1){
                    board[x][p.jcoord()].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    x=-1;
                }
                if(x!=-1 && pieces[x][p.jcoord()].type()==2){
                    // Stop looking
                    x=-1;
                }
            }

            // Look Down ..
            for(int y = p.jcoord()+1; y < 8; y++){
                if(pieces[p.icoord()][y].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][y].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][y].type()==1){
                    board[p.icoord()][y].setStroke(Color.AQUAMARINE);
                    // Stop looking
                    y=8;
                }
                if(y!=8 && pieces[p.icoord()][y].type()==2 && y!=8){
                    // Stop looking
                    y=8;
                }
            }
        }

        //_________________________________WHITEKING_____________________________________//
        if(p.toString() == "King" && p.type() == 1){
            // Up
            if(p.jcoord()-1 >= 0){
                if(pieces[p.icoord()][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()-1].type()==2){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - right
            if(p.jcoord()-1 >= 0 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-1].type()==2){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - left
            if(p.jcoord()-1 >= 0 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-1].type()==2){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Left
            if(p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()].type()==2){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Right
            if(p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()].type()==2){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Down
            if(p.jcoord()+1 < 8){
                if(pieces[p.icoord()][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()+1].type()==2){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - left
            if(p.jcoord()+1 < 8 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+1].type()==2){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - right
            if(p.jcoord()+1 < 8 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+1].type()==2){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
        }

        //_________________________________BLACKKING_____________________________________//
        if(p.toString() == "King" && p.type() == 2){
            // Up
            if(p.jcoord()-1 >= 0){
                if(pieces[p.icoord()][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()-1].type()==1){
                    board[p.icoord()][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - right
            if(p.jcoord()-1 >= 0 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()-1].type()==1){
                    board[p.icoord()+1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Up - left
            if(p.jcoord()-1 >= 0 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()-1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()-1].type()==1){
                    board[p.icoord()-1][p.jcoord()-1].setStroke(Color.AQUAMARINE);
                }
            }

            // Left
            if(p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()].type()==1){
                    board[p.icoord()-1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Right
            if(p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()].type()==1){
                    board[p.icoord()+1][p.jcoord()].setStroke(Color.AQUAMARINE);
                }
            }

            // Down
            if(p.jcoord()+1 < 8){
                if(pieces[p.icoord()][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()][p.jcoord()+1].type()==1){
                    board[p.icoord()][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - left
            if(p.jcoord()+1 < 8 && p.icoord()-1 >= 0){
                if(pieces[p.icoord()-1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()-1][p.jcoord()+1].type()==1){
                    board[p.icoord()-1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }

            // Down - right
            if(p.jcoord()+1 < 8 && p.icoord()+1 < 8){
                if(pieces[p.icoord()+1][p.jcoord()+1].toString().equals("com.stolz.alexander.chessengine.pieces.Empty")){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.CORNFLOWERBLUE);
                }
                if(pieces[p.icoord()+1][p.jcoord()+1].type()==1){
                    board[p.icoord()+1][p.jcoord()+1].setStroke(Color.AQUAMARINE);
                }
            }
        }
    }
}
