package Model;
import eg.edu.alexu.csd.oop.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Image implements GameObject {

    //store an array of sprite images to allow creating animations with an image object
    private static final int MAX_STATE = 1;
    private final BufferedImage[] spriteImages = new BufferedImage[MAX_STATE];
    private int x;
    private int y;
    private int type;
    private boolean isVisible;

    //convenience constructor that defaults the "type" to 0, when type isn't specified
    Image(int x, int y, String fileName){
        this(x, y, fileName, 0);
    }

    //constructor that allows specifying the "type"
    Image(int x, int y, String fileName, int type){
        this.x = x;
        this.y = y;
        this.type = type;
        this.isVisible = true;

        try {
            //dynamic loading of images
            //use of ImageIO.read allows reading an image file at runtime based on the provided file name
            spriteImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //getters and setters for the instance variables
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible){
        this.isVisible = isVisible;
    }

    public int getType(){
        return this.type;
    }

    public void setType(int type){
        this.type = type;
    }

}
