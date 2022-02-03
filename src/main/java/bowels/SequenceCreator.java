package bowels;

import gui.Field;
import gui.VisualSequenceCreator;

import java.util.ArrayList;

public class SequenceCreator {
    private final ArrayList<Vector2d> sequence;
    private final Field[][] fields;
    private final VisualSequenceCreator visualSequenceCreator;
    public SequenceCreator(Field[][]fields){
        sequence = new ArrayList<>();
        this.fields = fields;
        visualSequenceCreator = new VisualSequenceCreator(fields);
    }

    public void restartSequence(){
        visualSequenceCreator.restartSequence(sequence);
        sequence.clear();
        System.out.println("usuwam wszystko!");
        System.out.println("stan: "+ sequence);
    }

    public  void add(Vector2d position){
        sequence.add(position);
        visualSequenceCreator.add(position);
        System.out.println("dodaję ");
        System.out.println("stan: "+ sequence);
    }

    public void remove(Vector2d position) {
        sequence.remove(position);
        visualSequenceCreator.remove(position);
        System.out.println("usuwam ");
        System.out.println("stan: "+ sequence);
    }

    public Vector2d getLast(){

        return sequence.get(sequence.size()-1);
    }

    public boolean isEmpty(){
        return sequence.size() == 0;
    }




}
