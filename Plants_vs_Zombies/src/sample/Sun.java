package sample;

import javafx.scene.image.ImageView;

public class Sun extends Plants {
    public Sun(ImageView img)
    {
        super(img , 25,0,5,0);

    }

    public boolean isAvailable () {

        return false;
    }


}
