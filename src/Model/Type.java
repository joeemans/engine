package Model;

public enum Type {
    CLOWN(0, "clown"),
    BLUE_PLATE(1, "plate-1"),
    GREEN_PLATE(2, "plate-2"),
    RED_PLATE(3, "plate-3"),
    YELLOW_PLATE(4, "plate-4"),
    BACKGROUND(5, "background"),
    BOMB(6, "bomb"),
    EXPLOSION(7, "explosion");

    private final int value;
    private final String name;

    Type(int value, String name) {
        this.value = value;
        this.name = name;
    }

    int getValue(){
        return this.value;
    }

    String getName(){
        return this.name;
    }

    static int getTypeValue(Type type){
        return type.value;
    }

    public static Type getByValue(int value) {
        for (Type type : Type.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

    Color getColor(){
        return Color.getByValue(this.value);
    }

}
