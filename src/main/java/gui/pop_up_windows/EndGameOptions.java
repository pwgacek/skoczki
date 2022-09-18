package gui.pop_up_windows;

import logic.items.Player;
import gui.grid_pane.MainGridPane;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndGameOptions {
    private final Stage dialogStage;

    public EndGameOptions(Player player, MainGridPane mainGridPane) {
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setResizable(false);
        Button restartBtn = new Button("Once again!");
        Button exitBtn = new Button("Exit");
        restartBtn.setMinWidth(50);
        HBox hbox = new HBox(restartBtn, exitBtn);
        hbox.setSpacing(10);
        VBox vbox = new VBox(new Text(player.toString() + " won!"), hbox);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();


        restartBtn.setOnMouseClicked((event -> {
            mainGridPane.restart();
            dialogStage.close();

        }));

        exitBtn.setOnMouseClicked((event -> {
            Platform.exit();
            System.exit(0);
        }));
    }
}
