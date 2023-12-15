package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class DynamicImageLoader implements ImageLoader {

    @Override
    public BufferedImage loadImage(String fileName) {
        try {
            //dynamic loading of images
            //use of ImageIO.read allows reading an image file at runtime based on the provided file name
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
