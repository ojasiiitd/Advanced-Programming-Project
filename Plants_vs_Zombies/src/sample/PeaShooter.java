package sample;


import javafx.scene.image.ImageView;

public class PeaShooter extends Plants {

    public ImageView pea;

    public PeaShooter(ImageView img , ImageView peaImg)
    {
        super(img,50,5,100 , 50);
        pea = peaImg;
    }

    public boolean isAvailable () {

        return false;
    }
    public void attack()
    {

    }
}
