package gui.mechanics;

import bowels.items.Vector2d;
import gui.main_grid_pane_elements.Field;

import java.util.Stack;

public class SequenceVizualizer {
    private final Field[][] fields;
    public SequenceVizualizer(Field[][] fields){
        this.fields = fields;
    }

    public void restartSequence(Stack<Vector2d> sequence){
        sequence.forEach(p ->fields[p.x][p.y].restart());
    }

    public  void add(Vector2d position){
        fields[position.x][position.y].selected = true;
    }

    public void remove(Vector2d position) {
        fields[position.x][position.y].selected = false;
    }
}
