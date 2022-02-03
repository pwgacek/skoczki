package bowels;

import gui.BoardVisualizer;
import gui.Field;

public class GameEngine {
    private final Board board;
    private final SequenceCreator sequenceCreator;
    private final BoardVisualizer boardVisualizer;
    private Player winner;
    private Player currentPlayer;

    public GameEngine() {
        this.board = new Board();

        this.boardVisualizer = new BoardVisualizer(board.getPieces(),new Field[Constants.BOARD_SIZE][Constants.BOARD_SIZE]);
        this.sequenceCreator = new SequenceCreator(this.boardVisualizer.getFields());
        currentPlayer = Player.WHITE;


    }

    public void start(){
        while(winner == null){

        }
    }


    public BoardVisualizer getBoardVisualizer() {
        return boardVisualizer;
    }
}
