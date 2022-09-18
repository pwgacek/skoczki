package logic;

import logic.items.Board;
import logic.items.Vector2d;

import java.util.ArrayList;
import java.util.Stack;


public class SequenceBuilder {
    private final Stack<Vector2d> sequence;
    private final Board board;

    public SequenceBuilder(Board board) {
        sequence = new Stack<>();
        this.board = board;
    }

    public void restart() {
        sequence.clear();
    }

    public void addMove(Vector2d position) {
        sequence.push(position);
    }

    public void remove() {
        sequence.pop();
    }


    public boolean canAdd(Vector2d position) {
        if (sequence.size() == 1) {
            return board.canJump(sequence.get(0), position) || board.canMove(sequence.get(0), position);
        } else {
            if (sequence.size() == 2 && board.canMove(sequence.get(0), sequence.get(1))) {
                return false;
            }
            return board.canJump(getLast(), position);
        }
    }


    public Vector2d getLast() {
        return sequence.lastElement();
    }

    public ArrayList<Vector2d> getSequence() {
        return new ArrayList<>(sequence);
    }

    public boolean isEmpty() {
        return sequence.isEmpty();
    }

    public boolean sequenceExists() {
        return sequence.size() > 1;
    }

}
