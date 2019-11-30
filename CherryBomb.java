package sample;

import javafx.scene.image.ImageView;

public class CherryBomb extends Plants {

    public CherryBomb(ImageView img){
    super(img , 100,50,5,125);
//       this.cost = 125;
 }

    public boolean isAvailable () {

        return false;
    }
    public void attack()
    {

    }
}




