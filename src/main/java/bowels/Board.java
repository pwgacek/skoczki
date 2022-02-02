package bowels;

public class Board {
    private final Piece[][] pieces;

    public Board(){
        pieces = new Piece[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        fillBoard();
    }

    private void fillBoard(){
        for(int i=0;i<Constants.BOARD_SIZE;i++){
            pieces[i][0] = new Piece(i,Color.WHITE,new Vector2d(i,0));
            pieces[i][1] = new Piece(i*2,Color.WHITE,new Vector2d(i,1));

            pieces[i][Constants.BOARD_SIZE-1] = new Piece(i,Color.BLACK,new Vector2d(i,Constants.BOARD_SIZE-1));
            pieces[i][Constants.BOARD_SIZE-2] = new Piece(i*2,Color.BLACK,new Vector2d(i,Constants.BOARD_SIZE-2));
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }
}
