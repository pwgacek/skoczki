package gui.pop_up_windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SequenceError {
    private final Stage dialogStage;
    public SequenceError(){
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setResizable(false);
        Button okBtn = new Button("Ok");
        okBtn.setMinWidth(50);
        VBox vbox = new VBox(new Text("Sequence is not correct!"), okBtn);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
        okBtn.setOnMouseClicked((event -> dialogStage.close()));

    }



}
