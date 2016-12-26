package com.stolz.alexander.chessengine.gui.controls.main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {
    private Timeline timeline;
    private Timeline timeline2;
    private Duration time1 = Duration.minutes(15);
    private Duration Time2 = Duration.seconds(900);
    private int type;

    private SimpleDoubleProperty TimeSeconds = new SimpleDoubleProperty();
    private SimpleDoubleProperty timeSeconds = new SimpleDoubleProperty();

    @FXML
    private Label timerwhite;

    @FXML
    private Label timerblack;

    @FXML
    public void Button1Action(ActionEvent actionEvent) {
        if (timeline != null && type == 1) {
            timeSeconds.set(Duration.minutes(15).toMinutes());
            timerwhite.textProperty().bind(timeSeconds.asString("%.2f"));
        } else {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(1),
                            t -> {
                                Duration duration = ((KeyFrame) t.getSource()).getTime();
                                if (Time2.greaterThan(Duration.ZERO)) {
                                    time1 = time1.subtract(duration);
                                } else {
                                    time1 = Duration.ZERO;
                                }
                                timeSeconds.set(time1.toMinutes());

                            })
            );

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    @FXML
    public void Button2Action(ActionEvent actionEvent) {
        timeline.stop();
    }

    @FXML
    public void Button3Action(ActionEvent actionEvent) {
        if (timeline2 != null && type == 2) {
            TimeSeconds.set(Duration.minutes(15).toMinutes());
            timerblack.textProperty().bind(TimeSeconds.asString("%.2f"));
        } else {
            timeline2 = new Timeline(
                    new KeyFrame(Duration.millis(1),
                            event -> {
                                Duration duration = ((KeyFrame) event.getSource()).getTime();
                                if (Time2.greaterThan(Duration.ZERO)) {
                                    Time2 = Time2.subtract(duration);
                                } else {
                                    Time2 = Duration.ZERO;
                                }
                                TimeSeconds.set(Time2.toMinutes());
                            })
            );

            timeline2.setCycleCount(Timeline.INDEFINITE);
            timeline2.play();
            //event.consume();
        }
    }

    @FXML
    public void Button4Action(ActionEvent actionEvent) {
        timeline2.stop();
    }

    // Returns timer for white
    public double getTimerWhite() {
        double wt = Double.parseDouble(timerwhite.getText());
        return wt;
    }

    // Returns timer for black
    public double getTimerBlack() {
        double bt = Double.parseDouble(timerblack.getText());
        return bt;
    }
}
