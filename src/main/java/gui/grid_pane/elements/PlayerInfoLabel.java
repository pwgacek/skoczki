package gui.grid_pane.elements;

import logic.Constants;
import logic.items.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerInfoLabel extends Label {
    public PlayerInfoLabel() {
        setPlayer(Player.WHITE);
        setFont(new Font(24));
        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.4), new CornerRadii(10), Insets.EMPTY)));
        setMinSize(4 * Constants.CELL_SIZE, Constants.CELL_SIZE);
        setAlignment(Pos.CENTER);
    }

    public void setPlayer(Player player) {
        setText(player.toString() + "'S TURN");
    }


}
