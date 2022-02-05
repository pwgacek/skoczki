package bowels;

import bowels.items.Board;
import bowels.items.Vector2d;
import gui.main_grid_pane_elements.Field;
import gui.mechanics.VisualSequenceCreator;

import java.util.ArrayList;


public class SequenceCreator {
    private final ArrayList<Vector2d> sequence;
    private final Board board;


    private final VisualSequenceCreator visualSequenceCreator;
    public SequenceCreator(Field[][]fields,Board board){
        sequence = new ArrayList<>();
        this.board = board;

        visualSequenceCreator = new VisualSequenceCreator(fields);
    }

    public void restartSequence(){
        visualSequenceCreator.restartSequence(sequence);
        sequence.clear();
    }

    public  void add(Vector2d position){
        sequence.add(position);
        visualSequenceCreator.add(position);

    }

    public void remove(Vector2d position) {
        sequence.remove(position);
        visualSequenceCreator.remove(position);
    }

    public boolean canAdd(Vector2d position){
        if(sequence.size() == 1){
            return board.canJump(sequence.get(0), position) || board.canMove(sequence.get(0), position);
        }

        else{
            if(sequence.size() == 2 && board.canMove(sequence.get(0),sequence.get(1))){
                return false;
            }
            return board.canJump(getLast(), position);
        }
    }


    public Vector2d getLast(){

        return sequence.get(sequence.size()-1);
    }

    public boolean isEmpty(){
        return sequence.size() == 0;
    }

    public ArrayList<Vector2d> getSequence() {
        return sequence;
    }
}
