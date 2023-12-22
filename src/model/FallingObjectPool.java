package model;

import java.util.Stack;

public class FallingObjectPool {

    private static final int POOL_SIZE = 5;
    private final Stack<Faller> fallingObjects = new Stack<>();
    private final Stack<Detonator> detonators = new Stack<>();
    Circus circus;

    public FallingObjectPool(Circus circus){
        this.circus = circus;
        initPool();
    }

    private void initPool() {

        for(int i=0; i<POOL_SIZE; i++){
            int randomX = (int) (Math.random() * circus.getWidth());
            int randomY = (int) (Math.random() * circus.getHeight()) / 5;
            ShapeLoader randomFallingObjectFactory = DynamicFileLinker.getRandomFallingObjectFactory();
            circus.getMovableObjects().add(randomFallingObjectFactory.loadShape(randomX, randomY));
        }
    }

    public Faller borrowFallingObject(){

        int randomX = (int) (Math.random() * circus.getWidth());
        int randomY = (int) (Math.random() * circus.getHeight()) / 5;

        if(!fallingObjects.isEmpty()){
            Faller fallingObject = fallingObjects.pop();
            fallingObject.setX(randomX);
            fallingObject.setY(randomY);
            return fallingObject;
        }
        else{
            ShapeLoader randomFallingObjectFactory = DynamicFileLinker.getRandomFallingObjectFactory();
            Faller fallingObject = (Faller)randomFallingObjectFactory.loadShape(randomX, randomY);
            circus.getMovableObjects().add(fallingObject);
            return fallingObject;
        }
    }

    public Detonator borrowDetonator(){

        int randomX = (int) (Math.random() * circus.getWidth());
        int randomY = (int) (Math.random() * circus.getHeight()) / 5;

        if(!detonators.isEmpty()){
            Detonator detonator = detonators.pop();
            detonator.setX(randomX);
            detonator.setY(randomY);
            return detonator;
        }
        else{
            ShapeLoader randomDetonatingObjectFactory = DynamicFileLinker.getRandomDetonatingObjectFactory();
            Detonator detonator = (Detonator)randomDetonatingObjectFactory.loadShape(randomX, randomY);
            circus.getMovableObjects().add(detonator);
            return detonator;
        }
    }

    public void returnFallingObject(Faller fallingObject){
        fallingObjects.push(fallingObject);
    }
    public void returnDetonator(Detonator detonator){
        detonators.push(detonator);
    }


}
