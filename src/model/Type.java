package model;

public enum Type {
    CLOWN(0, "clown"),
    BLUE_PLATE(1, "plate-1"),
    GREEN_PLATE(2, "plate-2"),
    RED_PLATE(3, "plate-3"),
    YELLOW_PLATE(4, "plate-4"),
    WHITE_PLATE(5,"plate-5"),
    BOMB(5, "bomb"),
    BACKGROUND(6, "background"),
    EXPLOSION(7, "explosion"),
    HEART(8,"heart"),
    EMPTY_HEART(9,"empty-heart");

    private final int value;
    private final String name;

    Type(int value, String name) {
        this.value = value;
        this.name = name;
    }

    int getValue(){
        return this.value;
    }

    public String getName(){
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

    public Color getColor(){
        return Color.getByValue(this.value);
    }

}
