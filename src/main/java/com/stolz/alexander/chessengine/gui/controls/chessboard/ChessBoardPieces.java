package com.stolz.alexander.chessengine.gui.controls.chessboard;

import com.google.inject.Inject;
import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.gui.controls.main.Main;
import com.stolz.alexander.chessengine.gui.pieces.PieceImageProvider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by alexanderstolz on 12/30/16.
 */
public class ChessBoardPieces {
    public ImageView[][] getImageviews() {
        return imageviews;
    }

    private ImageView[][] imageviews;
    private final ChessBoard chessBoard;
    private final ChessBoardFields chessBoardFields;

    @Inject
    public ChessBoardPieces(ChessBoard chessBoard, ChessBoardFields chessBoardFields) {
        this.chessBoard = chessBoard;
        this.chessBoardFields = chessBoardFields;
    }

    public void init() {
        // Viewers for each getImage
        imageviews = new ImageView[8][8];

        // Initializes imageviewers and windows
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                imageviews[x][y] = new ImageView();
            }
        }

        // Puts images into imageviewers
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Image pieceImage = PieceImageProvider.INSTANCE.getImageForPiece(chessBoard.pieces[x][y]);
                imageviews[x][y].setImage(pieceImage);
                imageviews[x][y].setFitWidth(50);
                imageviews[x][y].setFitHeight(80);
                imageviews[x][y].setPreserveRatio(true);
                imageviews[x][y].setSmooth(true);
                imageviews[x][y].setCache(true);
                imageviews[x][y].setTranslateX(chessBoardFields.fields[x][y].getWidth() / 8);
            }
        }
    }

    public void resize(double cell_width, double cell_height) {
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                imageviews[i][j].resize(cell_width / 8, cell_height / 8);
                imageviews[i][j].relocate(i * cell_width, j * cell_height);
                imageviews[i][j].setFitWidth(cell_width / 1.25);
                imageviews[i][j].setFitHeight(cell_height / 1.25);
                imageviews[i][j].setTranslateX(chessBoardFields.fields[i][j].getWidth() / 8);
            }
        }
    }
}
