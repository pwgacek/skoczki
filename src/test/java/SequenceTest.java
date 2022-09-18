import logic.items.Board;
import logic.items.Piece;
import logic.items.Player;
import logic.items.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SequenceTest {
    @Test
    void jumpTest(){
        Board board = new Board();
        board.getPieces().add(new Piece(Player.WHITE,new Vector2d(2,5)));
        board.getPieces().add(new Piece(Player.WHITE,new Vector2d(4,4)));
        board.getPieces().add(new Piece(Player.WHITE,new Vector2d(4,3)));
        board.getPieces().add(new Piece(Player.WHITE,new Vector2d(2,3)));

        assertTrue(board.canJump(new Vector2d(1,6),new Vector2d(3,4)));
        assertTrue(board.canJump(new Vector2d(3,4),new Vector2d(5,4)));
        assertTrue(board.canJump(new Vector2d(5,4),new Vector2d(3,2)));
        assertTrue(board.canJump(new Vector2d(3,2),new Vector2d(1,4)));
        assertFalse(board.canJump(new Vector2d(1,4),new Vector2d(3,6)));


    }
    @Test
    void moveTest(){
        Board board = new Board();
        assertTrue(board.canMove(new Vector2d(0,6),new Vector2d(0,5)));
        assertFalse(board.canMove(new Vector2d(0,7),new Vector2d(0,6)));
    }


}
