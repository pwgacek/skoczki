package gui.grid_pane.elements;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class PerformSequenceButton extends Button {
    public PerformSequenceButton() {
        setText("GO!");
        setMinWidth(100);
        setMinHeight(50);
        setFont(new Font(24));
        setStyle("-fx-background-radius: 10px;");
    }

}
