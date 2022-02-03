package gui;

import bowels.Piece;
import bowels.SequenceCreator;
import bowels.Vector2d;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static bowels.Constants.BOARD_SIZE;
import static bowels.Constants.CELL_SIZE;

public class BoardVisualizer extends GridPane {
    private final Field[][] fields;

    public BoardVisualizer(Piece[][] pieces,Field[][] fields){

        for(int i=0;i<BOARD_SIZE;i++){
          getRowConstraints().add(new RowConstraints(CELL_SIZE));
          getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        this.fields = fields;
        SequenceCreator sequenceCreator = new SequenceCreator(fields);
        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                fields[x][y] = new Field(new Vector2d(x,y),sequenceCreator);

               add(fields[x][y],x,y);
            }
        }
        renderView(pieces);
        setGridLinesVisible(true);
    }

    public Field[][] getFields() {
        return fields;
    }

    public void renderView(Piece[][] pieces){
        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                if(pieces[x][y] == null) fields[x][y].removeImageView();
                else fields[x][y].setImageView(pieces[x][y].getImgPath());
            }
        }
    }

}
