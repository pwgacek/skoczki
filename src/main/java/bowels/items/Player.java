package bowels.items;

import java.util.ArrayList;

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



