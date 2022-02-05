package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainGridPane mainGridPane = new MainGridPane();
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


}
