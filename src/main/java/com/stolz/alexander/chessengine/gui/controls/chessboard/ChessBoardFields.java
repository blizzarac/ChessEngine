package com.stolz.alexander.chessengine.gui.controls.chessboard;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Created by alexanderstolz on 12/8/16.
 */
public class ChessBoardFields {
    public static final int boardWidth = 8;
    public static final int boardHeight = 8;

    public final Rectangle[][] fields;
    private final Color first;
    private final Color second;


    public ChessBoardFields(Color first, Color second) {
        this.first = first;
        this.second = second;
        this.fields = new Rectangle[boardWidth][boardHeight];

        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                fields[x][j] = new Rectangle();
                fields[x][j].setWidth(50);
                fields[x][j].setHeight(50);
                fields[x][j].setStroke(Color.TRANSPARENT);
                fields[x][j].setStrokeType(StrokeType.INSIDE);
                fields[x][j].setStrokeWidth(1);
            }
        }

        // Generates colours for the chessboard backgrounds
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                if (x % 2 == 0 && j % 2 == 1) {
                    fields[x][j].setFill(first);
                } else if (x % 2 == 0 && j % 2 == 0) {
                    fields[x][j].setFill(second);
                } else if (x % 2 == 1 && j % 2 == 1) {
                    fields[x][j].setFill(second);
                } else if (x % 2 == 1 && j % 2 == 0) {
                    fields[x][j].setFill(first);
                }
            }
        }
    }


    public void setFieldHightlightColor(int x, int y, Color color) {
        fields[x][y].setStroke(color);
    }

    public void resize(double cell_width, double cell_height) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fields[i][j].resize(i * cell_width, j * cell_height);
                fields[i][j].relocate(i * cell_width, j * cell_height);
                fields[i][j].setStrokeWidth(cell_width / 16);
                fields[i][j].setWidth(cell_width);
                fields[i][j].setHeight(cell_height);
            }
        }
    }

    void resetColorsOnBoard() {
        for (int x = 0; x < 8; x++) {
            for (int j = 0; j < 8; j++) {
                resetColorsOnField(x, j);
            }
        }
    }

    void resetColorsOnField(int i, int j) {
        fields[i][j].setStroke(Color.TRANSPARENT);

        if (i % 2 == 0 && j % 2 == 1) {
            fields[i][j].setFill(this.first);
        } else if (i % 2 == 0 && j % 2 == 0) {
            fields[i][j].setFill(this.second);
        } else if (i % 2 == 1 && j % 2 == 1) {
            fields[i][j].setFill(this.second);
        } else if (i % 2 == 1 && j % 2 == 0) {
            fields[i][j].setFill(this.first);
        }
    }
}
