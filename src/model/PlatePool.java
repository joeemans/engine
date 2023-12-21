package model;

import model.shapes.Plate;

import java.util.Stack;

class PlatePool {

    private static final int POOL_SIZE = 5;
    private final Stack<Faller> plates = new Stack<>();
    Circus circus;

    PlatePool(Circus circus){
        this.circus = circus;
        initPool();
    }

    private void initPool() {

        for(int i=0; i<POOL_SIZE; i++){
            int randomX = (int) (Math.random() * circus.getWidth());
            int randomY = (int) (Math.random() * circus.getHeight()) / 5;
            int randomType = (int) (Math.random() * 4) + 1;
            circus.getMovableObjects().add(new Plate(randomX, randomY, Type.getByValue(randomType)));
        }

    }

    Faller borrowPlate(int x, int y, int type){

        int randomX = (int) (Math.random() * circus.getWidth());
        int randomY = (int) (Math.random() * circus.getHeight()) / 5;
        int randomType = (int) (Math.random() * 4) + 1;

        if(!plates.isEmpty()){
            Faller plate = plates.pop();
            plate.setX(randomX);
            plate.setY(randomY);
            return plate;
        }
        else{
            return new Plate(randomX, randomY, Type.getByValue(randomType));
        }

    }

    void returnPlate(Faller plate){
        plates.push(plate);
    }

}
