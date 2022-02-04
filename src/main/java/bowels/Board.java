package bowels;

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
            pieces[i][0] = new Piece(i, Player.WHITE,new Vector2d(i,0));
            pieces[i][1] = new Piece(i*2, Player.WHITE,new Vector2d(i,1));

            pieces[i][Constants.BOARD_SIZE-1] = new Piece(i, Player.BLACK,new Vector2d(i,Constants.BOARD_SIZE-1));
            pieces[i][Constants.BOARD_SIZE-2] = new Piece(i*2, Player.BLACK,new Vector2d(i,Constants.BOARD_SIZE-2));
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public boolean canJump(Vector2d start,Vector2d end){
        if(jumps.contains(end.subtract(start)) && pieces[end.x][end.y] == null && pieces[start.x + (end.subtract(start).x)/2][start.y +(end.subtract(start).y)/2] !=null){
            return true;
        }
        return false;
    }

    public boolean canMove(Vector2d start,Vector2d end){
        if(pieces[end.x][end.y] == null && moves.contains(end.subtract(start))) return true;
        return false;
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

    public boolean sequenceIsCorrect(ArrayList<Vector2d> sequence){
        if(sequence.size() == 0 || sequence.size() == 1) return false;
        if(sequence.size() == 2 && canMove(sequence.get(0),sequence.get(1))) return true;
        for(int i =0;i<sequence.size()-1;i++){
            if(!canJump(sequence.get(i),sequence.get(i+1))) return false;
        }
        return true;
    }




}
