package gui.mechanics.commands;

import logic.SequenceBuilder;
import logic.items.Player;
import logic.items.Vector2d;
import gui.grid_pane.elements.Field;
import gui.pop_up_windows.SequenceError;

public class ChangeSequenceCommand implements Command {
    private final SequenceBuilder sequenceBuilder;
    private final Player currentPlayer;
    private final Field[][] fields;
    private final Field callingField;

    public ChangeSequenceCommand(SequenceBuilder sequenceBuilder, Player currentPlayer, Field[][] fields, Vector2d position) {
        this.sequenceBuilder = sequenceBuilder;
        this.currentPlayer = currentPlayer;

        this.fields = fields;
        this.callingField = fields[position.x][position.y];
    }


    @Override
    public void execute() {
        if (currentPlayer != null) {
            if (callingField.getOccupyingPiece() != null && currentPlayer == callingField.getOccupyingPiece().getPlayer()) {
                sequenceBuilder.getSequence().forEach(p -> fields[p.x][p.y].restart());

                sequenceBuilder.restart();

                if (!callingField.selected.get()) {
                    sequenceBuilder.addMove(callingField.getPosition());
                    callingField.selected.set(true);
                }
            } else if (callingField.getOccupyingPiece() == null) {
                if (!sequenceBuilder.isEmpty()) {
                    if (!callingField.selected.get()) {
                        if (sequenceBuilder.canAdd(callingField.getPosition())) {
                            sequenceBuilder.addMove(callingField.getPosition());
                            callingField.selected.set(true);

                        } else new SequenceError();
                    } else if (sequenceBuilder.getLast().equals(callingField.getPosition())) {
                        sequenceBuilder.remove();
                        callingField.selected.set(false);

                    }

                }
            }
        }


    }
}
