package sample;

import javafx.scene.image.ImageView;

public class SunFlower extends Plants {
    public SunFlower(ImageView img)
    {
        super(img , 50,50,5,50);

    }

    public boolean isAvailable () {

        return false;
    }
    public void attack()
    {

    }
}
