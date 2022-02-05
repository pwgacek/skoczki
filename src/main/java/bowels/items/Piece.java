package bowels.items;

public class Piece {

    private final Player player;

    public Piece(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getImgPath(){
        return this.getPlayer() == Player.WHITE ? "src/main/resources/white.png" : "src/main/resources/black.png";
    }



}
