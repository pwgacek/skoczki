package gui.main_grid_pane_elements;

import bowels.ChangeSequenceCommand;
import bowels.GameEngine;
import bowels.items.Piece;
import bowels.SequenceBuilder;
import bowels.items.Vector2d;
import gui.mechanics.ImageViewSelector;
import gui.pop_up_windows.SequenceError;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import static bowels.Constants.CELL_SIZE;


public class Field extends VBox {
    public boolean selected;
    private final SequenceBuilder sequenceBuilder;
    private Piece occupyingPiece;
    public Field(Vector2d position, GameEngine engine) {
        selected = false;
        this.sequenceBuilder = engine.getSequenceCreator();
        setPrefSize(CELL_SIZE,CELL_SIZE);

        setBackground(new Background(new BackgroundFill(getBackgroundColor(position.x+position.y), CornerRadii.EMPTY, Insets.EMPTY)));


        this.setOnMouseClicked((event)->{
            new ChangeSequenceCommand(sequenceBuilder,engine.getCurrentPlayer(),occupyingPiece,selected,position).execute();
            //setBackGroundColor();
            setBorder();
        });
    }

    public void setImageView(String path){
        removeImageView();
        ImageView imageView = ImageViewSelector.getInstance().getImageView(path);
        imageView.setFitHeight(CELL_SIZE*0.9);
        imageView.setFitWidth(CELL_SIZE*0.9);

        getChildren().add(imageView);
        setAlignment(Pos.CENTER);
    }
    public void removeImageView(){
        getChildren().clear();
    }

    private Color getBackgroundColor(int num){
        if(num % 2 == 1)return Color.rgb(102,68,58);
        return Color.rgb(245,230,191);
    }

    public void setBorder(){
        if(selected) setBorder(new Border(new BorderStroke(Color.MAGENTA, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        else setBorder(null);
    }
    public void restart(){
        selected = false;
        setBorder();
    }



    public void setOccupyingPiece(Piece occupyingPiece) {
        this.occupyingPiece = occupyingPiece;
    }
    public void removePiece(){
        this.occupyingPiece = null;
    }





}
