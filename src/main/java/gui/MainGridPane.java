package gui;

import bowels.GameEngine;
import bowels.items.Player;
import gui.main_grid_pane_elements.BoardVisualizer;
import gui.main_grid_pane_elements.PerformSequenceButton;
import gui.main_grid_pane_elements.PlayerInfoLabel;
import gui.pop_up_windows.EndGameOptions;
import gui.pop_up_windows.SequenceError;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static bowels.Constants.BOARD_SIZE;
import static bowels.Constants.CELL_SIZE;

public class MainGridPane extends GridPane {

    private GameEngine engine;
    MainGridPane() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/resources/bg.png"));
        setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));

        initialize();



    }

    private void initialize() {
        engine = new GameEngine();
        BoardVisualizer boardVisualizer = new BoardVisualizer(engine.getBoard().getPieces(), engine);
        PerformSequenceButton psBtn = new PerformSequenceButton();
        PlayerInfoLabel playerInfoLabel = new PlayerInfoLabel();
        engine.setPlayerInfoLabel(playerInfoLabel);
        engine.setBoardVisualizer(boardVisualizer);
        engine.setPerformSequenceButton(psBtn);
        engine.setMainGridPane(this);

        setHeight(boardVisualizer.getHeight()+3*CELL_SIZE);
        setWidth(boardVisualizer.getWidth()+CELL_SIZE);

        getRowConstraints().add(new RowConstraints(CELL_SIZE*0.5));
        getColumnConstraints().add(new ColumnConstraints(CELL_SIZE*0.5));

        for(int i=0;i<BOARD_SIZE;i++){
            getRowConstraints().add(new RowConstraints(CELL_SIZE));
            getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        getRowConstraints().add(new RowConstraints(CELL_SIZE*0.5));
        getColumnConstraints().add(new ColumnConstraints(CELL_SIZE*0.5));
        boardVisualizer.setAlignment(Pos.CENTER);
        add(boardVisualizer,1,1,BOARD_SIZE,BOARD_SIZE);


        getRowConstraints().add(new RowConstraints(CELL_SIZE*2));
        add(playerInfoLabel,1,BOARD_SIZE+1,4,BOARD_SIZE+2);
        add(psBtn,BOARD_SIZE-1,BOARD_SIZE+1,3,BOARD_SIZE+2);

        engine.start();
        psBtn.setOnMouseClicked((event -> engine.resumeMe()));
    }

    public void signalWrongSequence(){
        new SequenceError();
    }
    public void showEndGameOptions(Player player){
        new EndGameOptions(player,this);
    }
    public void terminateEngine(){
        engine.terminate();
    }
    public void restart(){
        getChildren().clear();
        getColumnConstraints().clear();
        getRowConstraints().clear();
        initialize();
    }


}
