package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.stolz.alexander.chessengine.gui.pieces.PieceView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import static com.stolz.alexander.chessengine.engine.pieces.PieceColor.WHITE;

/**
 * Created by alexanderstolz on 12/8/16.
 */
public class ChessBoard {
    public static final int boardWidth = 8;
    public static final int boardHeight = 8;

    private Rectangle[][] board;

    public Rectangle[][] getBoard() {
        return board;
    }
    public void setBoard(Rectangle[][] board) {
        this.board = board;
    }

    /**
     * Creates a new board
     *
     * @return
     */
    public Rectangle[][] initializeNewBoard() {
        final Rectangle [][] board = new Rectangle[boardWidth][boardHeight];

        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                board[x][j] = new Rectangle();
                board[x][j].setWidth(50);
                board[x][j].setHeight(50);
                board[x][j].setStroke(Color.TRANSPARENT);
                board[x][j].setStrokeType(StrokeType.INSIDE);
                board[x][j].setStrokeWidth(1);
            }
        }

        // Generates colours for the chessboard backgrounds
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                if (x % 2 == 0 && j % 2 == 1) {
                    board[x][j].setFill(Color.YELLOW);
                } else if (x % 2 == 0 && j % 2 == 0) {
                    board[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 1) {
                    board[x][j].setFill(Color.BROWN);
                } else if (x % 2 == 1 && j % 2 == 0) {
                    board[x][j].setFill(Color.YELLOW);
                }
            }
        }

        return board;
    }

    public void setFieldHightlightColor(int x, int y, Color color) {
        board[x][y].setStroke(color);
    }

    public void resize(double cell_width, double cell_height) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].resize(i * cell_width, j * cell_height);
                board[i][j].relocate(i * cell_width, j * cell_height);
                board[i][j].setStrokeWidth(cell_width / 16);
                board[i][j].setWidth(cell_width);
                board[i][j].setHeight(cell_height);
            }
        }
    }
}
