package sample;

import javafx.scene.image.ImageView;

public class LawnMover extends Plants {
    public LawnMover(ImageView img)
    {
    super(img , 1000,1000,0,0);
    }

    public boolean isAvailable () {

        return false;
    }
    public void attack()
    {

    }
}
