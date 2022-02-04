package bowels;

import gui.BoardVisualizer;
import gui.Field;

public class GameEngine {
    private final Board board;
    private final Field[][] fields;
    private final SequenceCreator sequenceCreator;
    private final BoardVisualizer boardVisualizer;
    private Player currentPlayer;

    public GameEngine() {
        this.board = new Board();
        this.fields = new Field[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        this.sequenceCreator = new SequenceCreator(fields);
        this.boardVisualizer = new BoardVisualizer(board.getPieces(),this);
        currentPlayer = Player.WHITE;

    }

    public void start(){
        while(!board.won(currentPlayer)){



            //zmien gracza
            changePlayer();
        }
        System.out.println("wygrał: "+ currentPlayer.toString() );
    }
    private void changePlayer(){
        if(currentPlayer == Player.BLACK)currentPlayer = Player.WHITE;
        else currentPlayer = Player.BLACK;
    }


    public BoardVisualizer getBoardVisualizer() {
        return boardVisualizer;
    }

    public Field[][] getFields() {
        return fields;
    }

    public SequenceCreator getSequenceCreator() {
        return sequenceCreator;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
