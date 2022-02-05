package bowels.items;

import bowels.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final Piece[][] pieces;
    private final ArrayList<Vector2d> jumps;
    private final ArrayList<Vector2d> moves;

    public Board(){
        pieces = new Piece[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        jumps = new ArrayList<>(Arrays.asList(new Vector2d(2,0),new Vector2d(-2,0),new Vector2d(0,2),new Vector2d(0,-2),new Vector2d(2,2),new Vector2d(2,-2),new Vector2d(-2,2),new Vector2d(-2,-2)));
        moves = new ArrayList<>(Arrays.asList(new Vector2d(0,1),new Vector2d(0,-1),new Vector2d(1,0),new Vector2d(-1,0)));
        fillBoard();
    }

    private void fillBoard(){
        for(int i=0;i<Constants.BOARD_SIZE;i++){
            pieces[i][0] = new Piece(Player.WHITE);
            pieces[i][1] = new Piece(Player.WHITE);

            pieces[i][Constants.BOARD_SIZE-1] = new Piece(Player.BLACK);
            pieces[i][Constants.BOARD_SIZE-2] = new Piece(Player.BLACK);
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public boolean canJump(Vector2d start,Vector2d end){
        return jumps.contains(end.subtract(start)) && pieces[end.x][end.y] == null && pieces[start.x + (end.subtract(start).x) / 2][start.y + (end.subtract(start).y) / 2] != null;
    }

    public boolean canMove(Vector2d start,Vector2d end){
        return pieces[end.x][end.y] == null && moves.contains(end.subtract(start));
    }

    public boolean won(Player player){
        if (player == Player.BLACK) {
            for(int i=0;i<Constants.BOARD_SIZE;i++){
                if(pieces[i][0] == null || pieces[i][1] == null || pieces[i][0].getPlayer() == Player.WHITE || pieces[i][1].getPlayer() == Player.WHITE)
                    return false;
            }
        }
        else{
            for(int i=0;i<Constants.BOARD_SIZE;i++){
                if(pieces[i][Constants.BOARD_SIZE-1] == null || pieces[i][Constants.BOARD_SIZE-2] == null || pieces[i][Constants.BOARD_SIZE-1].getPlayer() == Player.BLACK || pieces[i][Constants.BOARD_SIZE-2].getPlayer() == Player.BLACK)
                    return false;
            }
        }
        return true;

    }


    public void performMove(Vector2d start,Vector2d end){
        pieces[end.x][end.y] = pieces[start.x][start.y];
        pieces[start.x][start.y] = null;
    }





}
