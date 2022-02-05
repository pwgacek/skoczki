package gui.mechanics;

import bowels.items.Vector2d;
import gui.main_grid_pane_elements.Field;

import java.util.ArrayList;

public class VisualSequenceCreator {
    private final Field[][] fields;
    public VisualSequenceCreator(Field[][] fields){
        this.fields = fields;
    }

    public void restartSequence(ArrayList<Vector2d> sequence){
        sequence.forEach(p ->fields[p.x][p.y].selected = false);
        sequence.forEach(p ->fields[p.x][p.y].setBackGroundColor());
        sequence.forEach(p ->fields[p.x][p.y].setBorder());
    }

    public  void add(Vector2d position){
        fields[position.x][position.y].selected = true;
    }

    public void remove(Vector2d position) {
        fields[position.x][position.y].selected = false;
    }
}
