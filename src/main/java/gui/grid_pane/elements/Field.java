package gui.grid_pane.elements;

import gui.mechanics.commands.ChangeSequenceCommand;
import logic.GameEngine;
import logic.items.Piece;
import logic.SequenceBuilder;
import logic.items.Vector2d;
import gui.mechanics.ImageViewSelector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.util.concurrent.atomic.AtomicBoolean;

import static logic.Constants.CELL_SIZE;


public class Field extends VBox {
    public AtomicBoolean selected;
    private final SequenceBuilder sequenceBuilder;
    private Piece occupyingPiece;


    private final Vector2d position;

    public Field(Vector2d position, GameEngine engine) {
        this.selected = new AtomicBoolean(false);
        this.sequenceBuilder = engine.getSequenceBuilder();
        this.position = position;
        setPrefSize(CELL_SIZE, CELL_SIZE);

        setBackground(new Background(new BackgroundFill(getBackgroundColor(position.x + position.y), CornerRadii.EMPTY, Insets.EMPTY)));


        this.setOnMouseClicked((event) -> {
            new ChangeSequenceCommand(sequenceBuilder, engine.getCurrentPlayer(), engine.getFields(), this.position).execute();
            setBorder();
        });
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setImageView(String path) {
        removeImageView();
        ImageView imageView = ImageViewSelector.getInstance().getImageView(path);
        imageView.setFitHeight(CELL_SIZE * 0.9);
        imageView.setFitWidth(CELL_SIZE * 0.9);

        getChildren().add(imageView);
        setAlignment(Pos.CENTER);
    }

    public void removeImageView() {
        getChildren().clear();
    }

    private Color getBackgroundColor(int num) {
        if (num % 2 == 1) return Color.rgb(102, 68, 58);
        return Color.rgb(245, 230, 191);
    }

    public void setBorder() {
        if (selected.get())
            setBorder(new Border(new BorderStroke(Color.MAGENTA, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        else setBorder(null);
    }

    public void restart() {
        selected.set(false);
        setBorder();
    }


    public void setOccupyingPiece(Piece occupyingPiece) {
        this.occupyingPiece = occupyingPiece;
    }

    public void removePiece() {
        this.occupyingPiece = null;
    }


}
