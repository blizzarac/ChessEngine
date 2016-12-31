package com.stolz.alexander.chessengine;

import com.google.inject.AbstractModule;
import com.stolz.alexander.chessengine.engine.logic.CheckValidator;
import com.stolz.alexander.chessengine.engine.logic.ChessBoard;
import com.stolz.alexander.chessengine.gui.controls.chessboard.ChessBoardFields;
import com.stolz.alexander.chessengine.gui.controls.chessboard.ChessBoardPane;

/**
 * Created by alexanderstolz on 12/30/16.
 */
public class ChessEngineModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ChessBoard.class).asEagerSingleton();
        bind(ChessBoardFields.class).asEagerSingleton();
        bind(CheckValidator.class).asEagerSingleton();
    }
}
