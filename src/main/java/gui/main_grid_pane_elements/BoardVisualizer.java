package gui.main_grid_pane_elements;

import bowels.GameEngine;
import bowels.items.Piece;

import bowels.items.Vector2d;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static bowels.Constants.BOARD_SIZE;
import static bowels.Constants.CELL_SIZE;

public class BoardVisualizer extends GridPane {
    private final Field[][] fields;

    public BoardVisualizer(Piece[][] pieces, GameEngine engine){

        for(int i=0;i<BOARD_SIZE;i++){
          getRowConstraints().add(new RowConstraints(CELL_SIZE));
          getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        this.setWidth(CELL_SIZE*BOARD_SIZE);
        this.setHeight(CELL_SIZE*BOARD_SIZE);
        this.fields = engine.getFields();
        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                fields[x][y] = new Field(new Vector2d(x,y),engine);

               add(fields[x][y],x,y);
            }
        }
        renderView(pieces);
        setGridLinesVisible(true);
    }


    public void renderView(Piece[][] pieces){
        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                if(pieces[x][y] == null) {
                    fields[x][y].removeImageView();
                    fields[x][y].removePlayer();
                }
                else{
                    fields[x][y].setImageView(pieces[x][y].getImgPath());
                    fields[x][y].setPlayer(pieces[x][y].getPlayer());
                }
            }
        }
    }

}
