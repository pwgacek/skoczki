package bowels;

import bowels.items.Board;
import bowels.items.Piece;
import bowels.items.Player;
import bowels.items.Vector2d;
import gui.*;
import gui.main_grid_pane_elements.BoardVisualizer;
import gui.main_grid_pane_elements.Field;
import gui.main_grid_pane_elements.PerformSequenceButton;
import gui.main_grid_pane_elements.PlayerInfoLabel;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameEngine extends Thread {
    private final Board board;
    private final Field[][] fields;
    private final SequenceBuilder sequenceBuilder;
    private BoardVisualizer boardVisualizer;
    private PlayerInfoLabel playerInfoLabel;
    private PerformSequenceButton performSequenceButton;
    private Player currentPlayer;
    private final AtomicBoolean performSequence;
    private MainGridPane mainGridPane;
    private boolean terminated;


    public GameEngine() {
        this.board = new Board();
        this.fields = new Field[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        this.sequenceBuilder = new SequenceBuilder(board, fields);
        currentPlayer = Player.WHITE;
        performSequence = new AtomicBoolean(false);
        terminated = false;

    }
    @Override
    public void run(){
        while(!terminated){

            if(!performSequence.get()){
                suspendMe();
            }


            if(sequenceBuilder.getSequence().size() < 2){
                Platform.runLater(() ->mainGridPane.signalWrongSequence() );
                sequenceBuilder.restart();
                performSequence.set(false);

            }
            else{

                Player tmp = currentPlayer;
                currentPlayer = null;
                Platform.runLater(() ->performSequenceButton.setDisable(true));
//                for(int i=0;i<sequenceCreator.getSequence().size()-1;i++){
//
//                    board.performMove(sequenceCreator.getSequence().get(i),sequenceCreator.getSequence().get(i+1));
//                    Platform.runLater(() ->boardVisualizer.renderView(board.getPieces()) );
//                    try {
//                        sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
                ArrayList<Vector2d> sequence =  sequenceBuilder.getSequence();
                Piece usedPiece = board.getPieceAt(sequence.get(0));
                for(int i=1;i<sequence.size();i++){
                    usedPiece.move(sequence.get(i));
                    Platform.runLater(() ->boardVisualizer.renderView(board) );
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                currentPlayer = tmp;
                Platform.runLater(() ->performSequenceButton.setDisable(false));

                sequenceBuilder.restart();
                performSequence.set(false);
                if(board.finalSetting(currentPlayer)) {
                    break;
                }
                changePlayer();
                Platform.runLater(()->playerInfoLabel.setPlayer(currentPlayer));
            }




        }

        Player won = currentPlayer;
        Platform.runLater(() -> mainGridPane.showEndGameOptions(won));
        Platform.runLater(() ->performSequenceButton.setDisable(true));
        currentPlayer = null;

    }
    private void changePlayer(){
        if(currentPlayer == Player.BLACK)currentPlayer = Player.WHITE;
        else currentPlayer = Player.BLACK;
    }


    public Field[][] getFields() {
        return fields;
    }

    public SequenceBuilder getSequenceCreator() {
        return sequenceBuilder;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }





    synchronized protected void suspendMe(){
        try {
            while(!performSequence.get()){

                wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized public void resumeMe(){
        performSequence.set(true);
        notifyAll();
    }

    public void terminate(){
        terminated = true;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoardVisualizer(BoardVisualizer boardVisualizer) {
        this.boardVisualizer = boardVisualizer;
    }

    public void setPlayerInfoLabel(PlayerInfoLabel playerInfoLabel) {
        this.playerInfoLabel = playerInfoLabel;
    }

    public void setMainGridPane(MainGridPane mainGridPane) {
        this.mainGridPane = mainGridPane;
    }

    public void setPerformSequenceButton(PerformSequenceButton performSequenceButton) {
        this.performSequenceButton = performSequenceButton;
    }
}
