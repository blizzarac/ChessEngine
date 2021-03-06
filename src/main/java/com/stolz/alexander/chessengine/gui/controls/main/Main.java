package com.stolz.alexander.chessengine.gui.controls.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stolz.alexander.chessengine.ChessEngineModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final Injector injector;

    static {
        injector = Guice.createInjector(new ChessEngineModule());
    }

    // overridden start method
    @Override
    public void start(Stage primaryStage) throws IOException {
        final StackPane sp_mainlayout = FXMLLoader.load(getClass().getResource("/com/stolz/alexander/chessengine/gui/controls/main/main.fxml"));
        // set the title and scene, and show the stage
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(sp_mainlayout, 300, 300));

        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
