package gui;

import logic.GameEngine;
import gui.grid_pane.MainGridPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class App extends Application {
    private GameEngine engine;
    private MainGridPane mainGridPane;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        initialize();
    }
    public void initialize(){
        engine = new GameEngine();
        mainGridPane = new MainGridPane(engine,this);
        engine.setMainGridPane(mainGridPane);
        Scene scene = new Scene(mainGridPane,mainGridPane.getWidth(),mainGridPane.getHeight());
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setOnCloseRequest(t -> {
            mainGridPane.terminateEngine();
            Platform.exit();
            System.exit(0);


        });
    }

    public void restart(){
        initialize();
    }



}
