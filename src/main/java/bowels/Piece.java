package bowels;

public class Piece {
    private final int id;
    private final Color color;
    private final Vector2d position;

    public Piece(int id, Color color, Vector2d position) {
        this.id = id;
        this.color = color;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String getImgPath(){
        return this.getColor() == Color.WHITE ? "src/main/resources/white.png" : "src/main/resources/black.png";
    }

}
