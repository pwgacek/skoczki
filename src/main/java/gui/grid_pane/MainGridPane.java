package gui.grid_pane;

import gui.App;
import logic.GameEngine;
import gui.mechanics.commands.OnButtonClickedCommand;
import logic.items.Board;
import logic.items.Player;
import gui.mechanics.BoardVisualizer;
import gui.grid_pane.elements.PerformSequenceButton;
import gui.grid_pane.elements.PlayerInfoLabel;
import gui.mechanics.ImageViewSelector;
import gui.pop_up_windows.EndGameOptions;
import gui.pop_up_windows.SequenceError;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static logic.Constants.BOARD_SIZE;
import static logic.Constants.CELL_SIZE;

public class MainGridPane extends GridPane {

    private final GameEngine engine;
    private final BoardVisualizer boardVisualizer;

    private final PerformSequenceButton psBtn = new PerformSequenceButton();
    private final PlayerInfoLabel playerInfoLabel = new PlayerInfoLabel();
    private final Board board;
    private final App app;

    public MainGridPane(GameEngine engine, App app) {
        this.engine = engine;
        boardVisualizer = new BoardVisualizer(engine);
        board = engine.getBoard();
        this.app = app;
        initialize();
    }

    private void initialize() {
        ImageView image = ImageViewSelector.getInstance().getImageView("src/main/resources/bg.png");
        setBackground(new Background(new BackgroundImage(image.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        renderView();

        setHeight(boardVisualizer.getHeight() + 3 * CELL_SIZE);
        setWidth(boardVisualizer.getWidth() + CELL_SIZE);

        getRowConstraints().add(new RowConstraints(CELL_SIZE * 0.5));
        getColumnConstraints().add(new ColumnConstraints(CELL_SIZE * 0.5));

        for (int i = 0; i < BOARD_SIZE; i++) {
            getRowConstraints().add(new RowConstraints(CELL_SIZE));
            getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        getRowConstraints().add(new RowConstraints(CELL_SIZE * 0.5));
        getColumnConstraints().add(new ColumnConstraints(CELL_SIZE * 0.5));
        boardVisualizer.setAlignment(Pos.CENTER);
        add(boardVisualizer, 1, 1, BOARD_SIZE, BOARD_SIZE);


        getRowConstraints().add(new RowConstraints(CELL_SIZE * 2));
        add(playerInfoLabel, 1, BOARD_SIZE + 1, 4, BOARD_SIZE + 2);
        add(psBtn, BOARD_SIZE - 1, BOARD_SIZE + 1, 3, BOARD_SIZE + 2);

        engine.start();
        psBtn.setOnMouseClicked(event -> new OnButtonClickedCommand(engine).execute());

    }

    public void signalWrongSequence() {
        new SequenceError();
    }

    public void showEndGameOptions(Player player) {
        new EndGameOptions(player, this);
    }

    public void terminateEngine() {
        engine.terminate();
    }

    public void restart() {
        app.restart();
    }

    public void renderView() {
        this.boardVisualizer.renderView(board);
    }

    public void changePlayerInfoLabel(Player player) {
        playerInfoLabel.setPlayer(player);
    }

    public void disablePerformSequenceButton(boolean value) {
        psBtn.setDisable(value);
    }


}
