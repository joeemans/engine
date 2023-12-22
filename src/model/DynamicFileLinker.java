package model;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class DynamicFileLinker {
    private static final String SHAPES_DESTINATION_PACKAGE = "model.shapes.factories";
    private static final List <ShapeLoader> userControlledShapes = new ArrayList<>();
    private static final List <ShapeLoader> fallingObjects = new ArrayList<>();
    private static final List <ShapeLoader> detonatingObjectShape = new ArrayList<>();

    public static void shapesLoader() {
        File packageDirectory = new File("src\\model\\shapes\\factories");
        File[] files = packageDirectory.listFiles((dir, name) -> name.endsWith(".java"));

        if (files != null){
            for (File indexedFile : files){
                String className = SHAPES_DESTINATION_PACKAGE + "." + indexedFile.getName().replace(".java", "");
                try {
                    Class <?> classLoader = Class.forName(className);
                    Object objectLoader = classLoader.getDeclaredConstructor().newInstance();

                    if (objectLoader instanceof ShapeLoader && !((ShapeLoader)objectLoader).getControllable()){
                        Object sample = ((ShapeLoader) objectLoader).loadShape(0,0);
                        if (sample instanceof Detonator){
                            detonatingObjectShape.add((ShapeLoader) objectLoader);
                        } else if (sample instanceof Faller) {
                            fallingObjects.add((ShapeLoader) objectLoader);
                        }
                    }
                    else if (objectLoader instanceof ShapeLoader && ((ShapeLoader)objectLoader).getControllable()){
                        userControlledShapes.add((ShapeLoader) objectLoader);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ShapeLoader getRandomUserControlledShapeFactory(){
        int size = userControlledShapes.size();
        int randomizedIndex = (int) (Math.random() * size);
        return userControlledShapes.get(randomizedIndex);
    }

    public static ShapeLoader getRandomFallingObjectFactory(){
        int size = fallingObjects.size();
        int randomizedIndex = (int) (Math.random() * size);
        return fallingObjects.get(randomizedIndex);
    }

    public static ShapeLoader getRandomDetonatingObjectFactory(){
        int size = detonatingObjectShape.size();
        int randomizedIndex = (int) (Math.random() * size);
        return detonatingObjectShape.get(randomizedIndex);
    }

}
