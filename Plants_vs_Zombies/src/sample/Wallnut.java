package sample;

import javafx.scene.image.ImageView;

public class Wallnut  extends Plants{

    public Wallnut(ImageView img){
    super(img ,0,100,5,50);
    }


    public boolean isAvailable () {

        return false;
    }
    public void attack(){

    }
}



