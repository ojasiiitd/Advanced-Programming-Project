import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PvZ extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage mainStage)
    {
        mainStage.setTitle("Plants -- Zombies");
        Button btn = new Button("Play");
        Scene scene = new Scene(btn , 500 , 500);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
