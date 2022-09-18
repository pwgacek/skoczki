package gui.mechanics.commands;

import logic.GameEngine;
import logic.items.Vector2d;
import gui.grid_pane.elements.Field;

import java.util.ArrayList;

public class OnButtonClickedCommand implements Command {
    private GameEngine engine;
    private Field[][] fields;
    private ArrayList<Vector2d> sequence;

    public OnButtonClickedCommand(GameEngine engine) {
        this.engine = engine;
        this.fields = engine.getFields();
        this.sequence = engine.getSequenceBuilder().getSequence();
    }

    @Override
    public void execute() {
        engine.resumeMe();
        sequence.forEach(v-> fields[v.x][v.y].restart());
    }
}
