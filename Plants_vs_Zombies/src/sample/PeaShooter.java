package sample;


import javafx.scene.image.ImageView;

public class PeaShooter extends Plants {

    public PeaShooter(ImageView img)
    {
        super(img,50,75,100 , 50);
    }

    public boolean isAvailable () {

        return false;
    }
    public void attack()
    {

    }
}
