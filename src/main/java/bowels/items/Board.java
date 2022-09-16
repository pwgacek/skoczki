package bowels.items;

import bowels.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final ArrayList<Piece> pieces;
    private final ArrayList<Vector2d> whiteInitialPositions;
    private final ArrayList<Vector2d> blackInitialPositions;


    public Board(){
        whiteInitialPositions = new ArrayList<>(Constants.NO_PIECES/2);
        blackInitialPositions = new ArrayList<>(Constants.NO_PIECES/2);
        pieces = new ArrayList<>(Constants.NO_PIECES);

        generateInitialPositions();
        fillBoard();
    }


    private void fillBoard(){
        for(Vector2d position:whiteInitialPositions){
            pieces.add(new Piece(Player.WHITE,position.copy()));
        }
        for(Vector2d position:blackInitialPositions){
            pieces.add(new Piece(Player.BLACK,position.copy()));
        }

    }

    private void generateInitialPositions(){
        for(int x=0;x<Constants.BOARD_SIZE;x++) {
            whiteInitialPositions.add(new Vector2d(x,0));
            whiteInitialPositions.add(new Vector2d(x,1));
            blackInitialPositions.add(new Vector2d(x,Constants.BOARD_SIZE-2));
            blackInitialPositions.add(new Vector2d(x,Constants.BOARD_SIZE-1));

        }
    }


    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    public Piece getPieceAt(Vector2d position){
        for(Piece piece: pieces){
            if(piece.getPosition().equals(position)) return piece;
        }
        return null;
    }
    public boolean isOccupied(Vector2d position)
    {
        return getPieceAt(position) != null;
    }
    public boolean canJump(Vector2d start,Vector2d end){
        return Piece.jumps.contains(end.subtract(start)) && !isOccupied(end) && isOccupied(start.add(end).divideByTwo());
    }

    public boolean canMove(Vector2d start,Vector2d end){
        return Piece.moves.contains(end.subtract(start)) && !isOccupied(end);
    }


    public boolean finalSetting(Player player){
        if (player == Player.BLACK) {
            for(Vector2d position : whiteInitialPositions){
                if(!isOccupied(position) || getPieceAt(position).getPlayer() == Player.WHITE)
                    return false;
            }
        }
        else{
            for(Vector2d position : blackInitialPositions){
                if(!isOccupied(position) || getPieceAt(position).getPlayer() == Player.BLACK)
                    return false;
            }
        }
        return true;

    }







}
