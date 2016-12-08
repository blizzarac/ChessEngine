package com.stolz.alexander.chessengine.gui.controls.chessboard;//imports

import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

//class definition 
class ChessEngineControlSkin extends SkinBase<ChessEngineControl> implements Skin<ChessEngineControl> {
    public ChessEngineControlSkin(ChessEngineControl cc) {
        //call the super class constructor
        super(cc);
    }
}
