package logic;

import logic.items.Board;
import logic.items.Piece;
import logic.items.Player;
import logic.items.Vector2d;
import gui.grid_pane.MainGridPane;
import gui.grid_pane.elements.Field;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameEngine extends Thread {
    private final Board board;
    private final Field[][] fields;
    private final SequenceBuilder sequenceBuilder;

    private Player currentPlayer;
    private final AtomicBoolean loopRunning;
    private MainGridPane mainGridPane;
    private boolean terminated;


    public GameEngine() {
        this.board = new Board();
        this.fields = new Field[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        this.sequenceBuilder = new SequenceBuilder(board);
        currentPlayer = Player.WHITE;
        loopRunning = new AtomicBoolean(false);
        terminated = false;

    }

    @Override
    public void run() {
        while (!terminated) {

            if (!loopRunning.get()) {
                suspendMe();
            }

            if (!sequenceBuilder.sequenceExists()) {
                Platform.runLater(() -> mainGridPane.signalWrongSequence());
            } else {
                Platform.runLater(() -> mainGridPane.disablePerformSequenceButton(true));
                movePiece(sequenceBuilder.getSequence());
                Platform.runLater(() -> mainGridPane.disablePerformSequenceButton(false));

                if (board.finalSetting(currentPlayer)) {
                    break;
                }
                changePlayer();
            }
            sequenceBuilder.restart();
            loopRunning.set(false);

        }

        Player won = currentPlayer;
        Platform.runLater(() -> mainGridPane.showEndGameOptions(won));
        Platform.runLater(() -> mainGridPane.disablePerformSequenceButton(true));
        currentPlayer = null;

    }

    private void movePiece(ArrayList<Vector2d> sequence) {
        Player tmp = currentPlayer;
        currentPlayer = null;
        Piece usedPiece = board.getPieceAt(sequence.get(0));
        for (int i = 1; i < sequence.size(); i++) {
            usedPiece.move(sequence.get(i));
            Platform.runLater(() -> mainGridPane.renderView());
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        currentPlayer = tmp;
    }

    private void changePlayer() {
        if (currentPlayer == Player.BLACK) currentPlayer = Player.WHITE;
        else currentPlayer = Player.BLACK;
        Platform.runLater(() -> mainGridPane.changePlayerInfoLabel(currentPlayer));

    }


    public Field[][] getFields() {
        return fields;
    }

    public SequenceBuilder getSequenceBuilder() {
        return sequenceBuilder;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    synchronized protected void suspendMe() {
        try {
            while (!loopRunning.get()) {
                wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized public void resumeMe() {
        loopRunning.set(true);
        notifyAll();
    }

    public void terminate() {
        terminated = true;
    }

    public Board getBoard() {
        return board;
    }

    public void setMainGridPane(MainGridPane mainGridPane) {
        this.mainGridPane = mainGridPane;
    }


}
