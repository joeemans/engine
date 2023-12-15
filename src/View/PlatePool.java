package View;

import Model.Plate;
import Model.PlateCatcher;
import Model.Type;

import java.util.Stack;

class PlatePool {

    private static final int POOL_SIZE = 5;
    private final Stack<Plate> plates = new Stack<>();
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

    Plate borrowPlate(int x, int y, int type){

        int randomX = (int) (Math.random() * circus.getWidth());
        int randomY = (int) (Math.random() * circus.getHeight()) / 5;
        int randomType = (int) (Math.random() * 4) + 1;

        if(!plates.isEmpty()){
            Plate plate = plates.pop();
            plate.setX(randomX);
            plate.setY(randomY);
            return plate;
        }
        else{
            return new Plate(randomX, randomY, Type.getByValue(randomType));
        }

    }

    void returnPlate(Plate plate){
        plates.push(plate);
    }

}
