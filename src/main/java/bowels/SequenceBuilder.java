package bowels;

import bowels.items.Board;
import bowels.items.Vector2d;
import gui.main_grid_pane_elements.Field;
import gui.mechanics.SequenceVizualizer;

import java.util.ArrayList;
import java.util.Stack;


public class SequenceBuilder {
    private final Stack<Vector2d> sequence;
    private final Board board;

    private final SequenceVizualizer sequenceVizualizer;
    public SequenceBuilder(Board board, Field[][]fields){
        sequence = new Stack<>();
        this.board = board;
        sequenceVizualizer = new SequenceVizualizer(fields);
    }

    public void restart(){
        sequenceVizualizer.restartSequence(sequence);
        sequence.clear();
    }

    public  void addMove(Vector2d position){
        sequence.push(position);
        sequenceVizualizer.add(position);

    }



    public void remove() {
        Vector2d v = sequence.pop();
        sequenceVizualizer.remove(v);
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
        return sequence.lastElement();
    }

    public ArrayList<Vector2d> getSequence() {
        return new ArrayList<>(sequence);
    }

    public boolean isEmpty(){
        return sequence.isEmpty();
    }

}
