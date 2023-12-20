package Model;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class DynamicFileLinker {
    private static final String SHAPES_DESTINATION_PACKAGE = "Model.Shapes.Factories";
    private static final List <ShapeLoader> userControlledShapes = new ArrayList<>();
    private static final List <ShapeLoader> fallingObjects = new ArrayList<>();

    public static void shapesLoader() {
        File packageDirectory = new File("C:\\Users\\youss\\IdeaProjects\\engine1\\src\\Model\\Shapes\\Factories");
        File[] files = packageDirectory.listFiles((dir, name) -> name.endsWith(".java"));
        System.out.println(packageDirectory);

        if (files != null){
            for (File indexedFile : files){
                String className = SHAPES_DESTINATION_PACKAGE + "." + indexedFile.getName().replace(".java", "");
                System.out.println(className);
                try {
                    Class <?> classLoader = Class.forName(className);
                    Object objectLoader = classLoader.getDeclaredConstructor().newInstance();

                    if (objectLoader instanceof ShapeLoader && !((ShapeLoader)objectLoader).getControllable()){
                        System.out.println(1);
                        fallingObjects.add((ShapeLoader) objectLoader);
                    }
                    else if (objectLoader instanceof ShapeLoader && ((ShapeLoader)objectLoader).getControllable()){
                        System.out.println(2);
                        userControlledShapes.add((ShapeLoader) objectLoader);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ShapeLoader getRandomUserControlledShapeFactory(){
        int size = userControlledShapes.size() - 1;
        int randomizedIndex = (int) (Math.random() * size);
        return userControlledShapes.get(randomizedIndex );
    }
    public static ShapeLoader getRandomFallingShapeFactory(){
        int size = fallingObjects.size() - 1;
        int randomizedIndex = (int) (Math.random() * size);
        return fallingObjects.get(randomizedIndex);
    }
}
