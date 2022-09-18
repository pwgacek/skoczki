package gui.mechanics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ImageViewSelector {
    private final Map<String, Image> imageMap;
    private static ImageViewSelector instance;

    public static ImageViewSelector getInstance() {
        if (instance == null) {
            instance = new ImageViewSelector();
        }
        return instance;
    }

    private ImageViewSelector() {
        imageMap = new HashMap<>();
        String[] paths = new String[]{"src/main/resources/white.png", "src/main/resources/black.png", "src/main/resources/bg.png"};

        for (String path : paths) {
            try {
                Image image = new Image(new FileInputStream(path));
                imageMap.put(path, image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public ImageView getImageView(String path) {
        return new ImageView(imageMap.get(path));
    }

}
