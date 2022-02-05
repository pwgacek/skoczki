package bowels.items;

public enum Player {
    WHITE,
    BLACK;

    @Override
    public String toString() {
        if (this == Player.BLACK) {
            return "BLACK";
        }
        return "WHITE";

    }

}



