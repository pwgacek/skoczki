package bowels;

public class Piece {
    private final int id;
    private final Player player;
    private final Vector2d position;

    public Piece(int id, Player player, Vector2d position) {
        this.id = id;
        this.player = player;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String getImgPath(){
        return this.getPlayer() == Player.WHITE ? "src/main/resources/white.png" : "src/main/resources/black.png";
    }

}
