package Model;
public class Clown extends Component{

    private final String name = Type.CLOWN.getName();
    private final String fileName = "resources/" + name + ".png";
    Clown(int x, int y, String fileName, int type) {
        super(x, y, fileName, Type.CLOWN);
    }



}
