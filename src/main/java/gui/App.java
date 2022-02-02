package gui;

import bowels.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Board board = new Board();
        BoardVisualizer boardVisualizer = new BoardVisualizer(board.getPieces());
        Scene scene = new Scene(boardVisualizer,700,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
