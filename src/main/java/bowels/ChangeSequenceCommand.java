package bowels;

import bowels.items.Piece;
import bowels.items.Player;
import bowels.items.Vector2d;
import gui.pop_up_windows.SequenceError;

public class ChangeSequenceCommand implements Command{
    private SequenceBuilder sequenceBuilder;
    private Player currentPlayer;
    private Piece occupyingPiece;
    private boolean selected;
    private Vector2d position;

    public ChangeSequenceCommand(SequenceBuilder sequenceBuilder, Player currentPlayer, Piece occupyingPiece, boolean selected, Vector2d position) {
        this.sequenceBuilder = sequenceBuilder;
        this.currentPlayer = currentPlayer;
        this.occupyingPiece = occupyingPiece;
        this.selected = selected;
        this.position = position;
    }


    @Override
    public void execute() {

        if(occupyingPiece != null && currentPlayer == occupyingPiece.getPlayer()){
            sequenceBuilder.restart();
            if(!selected){
                sequenceBuilder.addMove(position);
            }
        }
        else if(occupyingPiece == null){
            if(!sequenceBuilder.isEmpty()){
                if(!selected){
                    if(sequenceBuilder.canAdd(position)) sequenceBuilder.addMove(position);
                    else new SequenceError();
                }
                else if(sequenceBuilder.getLast().equals(position)){
                    sequenceBuilder.remove();
                }

            }
        }



    }
}
