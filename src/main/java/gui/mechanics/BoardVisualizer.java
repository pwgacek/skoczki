package gui.mechanics;

import logic.GameEngine;
import logic.items.Board;
import logic.items.Piece;

import logic.items.Vector2d;
import gui.grid_pane.elements.Field;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static logic.Constants.BOARD_SIZE;
import static logic.Constants.CELL_SIZE;

public class BoardVisualizer extends GridPane {
    private final Field[][] fields;

    public BoardVisualizer(GameEngine engine) {
        this.fields = engine.getFields();

        buildBoard(engine);
    }

    private void buildBoard(GameEngine engine) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            getRowConstraints().add(new RowConstraints(CELL_SIZE));
            getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        this.setWidth(CELL_SIZE * BOARD_SIZE);
        this.setHeight(CELL_SIZE * BOARD_SIZE);
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                fields[x][y] = new Field(new Vector2d(x, y), engine);

                add(fields[x][y], x, y);
            }
        }
    }


    public void renderView(Board board) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Piece piece = board.getPieceAt(new Vector2d(x, y));
                if (piece != null) {
                    fields[x][y].setImageView(piece.getImgPath());
                    fields[x][y].setOccupyingPiece(piece);

                } else {
                    fields[x][y].removeImageView();
                    fields[x][y].removePiece();
                }
            }
        }
    }

}
