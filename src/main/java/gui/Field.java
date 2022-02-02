package gui;

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
    ImageViewSelector imageViewSelector;
    ImageView imageView;
    Field(int num) {
        imageViewSelector = new ImageViewSelector();
        setPrefSize(CELL_SIZE,CELL_SIZE);

        if (num % 2 == 1) setBackground(new Background(new BackgroundFill(Color.ROSYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
        else setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
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




}
