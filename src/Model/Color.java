package Model;

public enum Color {

    BLUE(1),
    GREEN(2),
    RED(3),
    YELLOW(4);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    static Color getByValue(int value) {
        for (Color color : Color.values()) {
            if (color.value == value) {
                return color;
            }
        }
        return null;
    }

}
