package bowels.items;

import java.util.ArrayList;
import java.util.Arrays;

public class Piece {
    private final Player player;
    private Vector2d position;

    public final static ArrayList<Vector2d> moves = new ArrayList<>(
            Arrays.asList(
                    new Vector2d(0,1),
                    new Vector2d(0,-1),
                    new Vector2d(1,0),
                    new Vector2d(-1,0)));
    public final static ArrayList<Vector2d>  jumps = new ArrayList<>(
            Arrays.asList(
                    new Vector2d(2, 0),
                    new Vector2d(-2, 0),
                    new Vector2d(0, 2),
                    new Vector2d(0, -2),
                    new Vector2d(2, 2),
                    new Vector2d(2, -2),
                    new Vector2d(-2, 2),
                    new Vector2d(-2, -2)));



    public Piece(Player player,Vector2d position) {
        this.player = player;
        this.position = position;
    }


    public Vector2d getPosition() {
        return position;
    }
    public void move(Vector2d position){
        this.position = position;
    }



    public Player getPlayer() {
        return player;
    }


    public String getImgPath(){
        return this.getPlayer() == Player.WHITE ? "src/main/resources/white.png" : "src/main/resources/black.png";
    }



}
