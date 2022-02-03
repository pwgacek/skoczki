package gui;

import bowels.Board;
import bowels.GameEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameEngine engine = new GameEngine();
        Scene scene = new Scene(engine.getBoardVisualizer(),700,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
