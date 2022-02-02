package gui;

import bowels.Piece;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static bowels.Constants.BOARD_SIZE;
import static bowels.Constants.CELL_SIZE;

public class BoardVisualizer extends GridPane {
    private final Field[][] fields;
    BoardVisualizer(Piece[][] pieces){

        for(int i=0;i<BOARD_SIZE;i++){
          getRowConstraints().add(new RowConstraints(CELL_SIZE));
          getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        fields = new Field[BOARD_SIZE][BOARD_SIZE];
        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                fields[x][y] = new Field(x+y);

               add(fields[x][y],x,y);
            }
        }
        renderView(pieces);
        setGridLinesVisible(true);
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
