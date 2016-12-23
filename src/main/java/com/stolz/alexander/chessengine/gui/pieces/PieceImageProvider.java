package com.stolz.alexander.chessengine.gui.pieces;

import com.stolz.alexander.chessengine.engine.pieces.Piece;
import com.stolz.alexander.chessengine.engine.pieces.PieceColor;
import com.stolz.alexander.chessengine.engine.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.HashMap;

/**
 * Created by alexanderstolz on 12/11/16.
 */
public enum PieceImageProvider {
    INSTANCE;

    private HashMap<PieceKind, Image> imageMap = new HashMap<>();

    class PieceKind{
        PieceType type;
        PieceColor color;

        public PieceKind(PieceType type, PieceColor color) {
            this.type = type;
            this.color = color;
        }

        @Override
        public boolean equals(Object obj) {
            PieceKind other = (PieceKind) obj;
            return this.type == other.type && this.color == other.color;
        }

        @Override
        public int hashCode() {
            return this.type.hashCode() + this.color.hashCode();
        }
    }

    PieceImageProvider() {
        imageMap.put(new PieceKind(PieceType.PAWN, PieceColor.WHITE), new Image("whitepawn.png"));
        imageMap.put(new PieceKind(PieceType.PAWN, PieceColor.BLACK), new Image("blackpawn.png"));

        imageMap.put(new PieceKind(PieceType.KNIGHT, PieceColor.WHITE), new Image("whiteknight.png"));
        imageMap.put(new PieceKind(PieceType.KNIGHT, PieceColor.BLACK), new Image("blackknight.png"));

        imageMap.put(new PieceKind(PieceType.BISHOP, PieceColor.WHITE), new Image("whitebishop.png"));
        imageMap.put(new PieceKind(PieceType.BISHOP, PieceColor.BLACK), new Image("blackbishop.png"));

        imageMap.put(new PieceKind(PieceType.ROOK, PieceColor.WHITE), new Image("whiterook.png"));
        imageMap.put(new PieceKind(PieceType.ROOK, PieceColor.BLACK), new Image("blackrook.png"));

        imageMap.put(new PieceKind(PieceType.QUEEN, PieceColor.WHITE), new Image("whitequeen.png"));
        imageMap.put(new PieceKind(PieceType.QUEEN, PieceColor.BLACK), new Image("blackqueen.png"));

        imageMap.put(new PieceKind(PieceType.KING, PieceColor.WHITE), new Image("whiteking.png"));
        imageMap.put(new PieceKind(PieceType.KING, PieceColor.BLACK), new Image("blackking.png"));

        imageMap.put(null, new Image("empty.png"));
    }

    public Image getImageForPiece(PieceType type, PieceColor color) {
        return imageMap.get(new PieceKind(type, color));
    }

    public Image getImageForPiece(Piece selectedpiece) {
        return getImageForPiece(selectedpiece.getType(), selectedpiece.getColor());
    }
}
