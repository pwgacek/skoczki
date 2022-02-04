package gui;

import bowels.GameEngine;
import bowels.Player;
import bowels.SequenceCreator;
import bowels.Vector2d;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import static bowels.Constants.CELL_SIZE;


public class Field extends VBox {
    private final ImageViewSelector imageViewSelector;
    private ImageView imageView;
    public boolean selected;
    private final Vector2d position;
    private final SequenceCreator sequenceCreator;
    private Player player;
    Field(Vector2d position, GameEngine engine) {
        selected = false;
        imageViewSelector = new ImageViewSelector();
        this.position = position;
        this.sequenceCreator = engine.getSequenceCreator();
        setPrefSize(CELL_SIZE,CELL_SIZE);

        setBackground(new Background(new BackgroundFill(getBackgroundColor(position.x+position.y), CornerRadii.EMPTY, Insets.EMPTY)));


        this.setOnMouseClicked((event)->{
            if(player != null && engine.getCurrentPlayer() == player){
                if(selected){
                    sequenceCreator.restartSequence();
                }
                else{
                    sequenceCreator.restartSequence();
                    sequenceCreator.add(position);

                }
            }
            else if(player == null){
                if(!sequenceCreator.isEmpty()){
                    if(!selected){
                        sequenceCreator.add(position);
                    }
                    else if(sequenceCreator.getLast().equals(position)){
                        sequenceCreator.remove(position);
                    }


                }
            }
            setBackGroundColor();
        });
    }

    public void setImageView(String path){
        removeImageView();
        imageView = imageViewSelector.getImageView(path);
        imageView.setFitHeight(CELL_SIZE*0.9);
        imageView.setFitWidth(CELL_SIZE*0.9);

        getChildren().add(imageView);
        setAlignment(Pos.CENTER);
    }
    public void removeImageView(){
        this.imageView = null;
        getChildren().clear();
    }

    private Color getBackgroundColor(int num){
        if(selected) return Color.MAGENTA;
        if(num % 2 == 1)return Color.ROSYBROWN;
        return Color.SADDLEBROWN;
    }

    public void setBackGroundColor(){
        setBackground(new Background(new BackgroundFill(getBackgroundColor(position.x+position.y), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void removePlayer(){
        this.player = null;
    }





}
