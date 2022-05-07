import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.LinkedList;

//class Home implementation by: Brandon Tsuchiya

public class Home extends Application
{
    //Object used in this class:
    Server serverOb = new Server();

    static LinkedList<Integer> IDs = new LinkedList<>(); //Used to hold clock-ins

    public static void main(String[] args)
    { launch(args); }

    @Override
    public void start(Stage window)
    {

        Button start = new Button("Start");
        start.setMinSize(200,60);
        start.setOnAction (e ->
        {
            window.close();
            serverOb.home();
        });

        //LAYOUT::

        //Title
        Label screenLabel = new Label("Start Screen");
        //V/HBoxes
        HBox options = new HBox(50, start);
        //Main screen
        BorderPane screen = new BorderPane();
        //Sets
        screen.setTop(screenLabel); //top
        screen.setCenter(options); //center
        //Alighment/Padding
        screen.setPadding(new Insets(30,0,30,0));
        screen.setAlignment(screenLabel, Pos.CENTER);
        options.setAlignment(Pos.CENTER);
        //scene
        Scene startScreen = new Scene(screen, 900, 500);
        window.setScene(startScreen);
        window.show();
    }
}
