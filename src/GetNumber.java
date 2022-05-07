import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//class GetNumber implementation by: Eric Tsuchiya

//Gets a number from the user. Must be int.
public class GetNumber
{
    static int num;

    //display() - displays a message and returns an integer value
    public static int display(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setOnCloseRequest(Event::consume);

        //textField.
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);

        //BUTTONS::

        //confirm button
        Button confirm = new Button("Ok");
        confirm.setOnAction (e ->
        {
            num = Integer.parseInt(textField.getText());
            window.close();
        });

        //cancel button
        Button cancel = new Button("Cancel");
        cancel.setOnAction(event ->
        {
            num = -2; //cancel value.
            window.close();
        });

        //LAYOUT::

        //Label
        Label label = new Label(message);
        //V/HBoxes
        HBox confirmAndCancel = new HBox(10, confirm, cancel);
        VBox layout = new VBox(20, label, textField, confirmAndCancel);
        //Main screen
        BorderPane screen = new BorderPane();
        //Sets
        screen.setCenter(layout);
        //Alignment/Padding
        screen.setPadding(new Insets(10,10,10,10));
        confirmAndCancel.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        //scene
        Scene scene = new Scene(screen, 350, 150);
        window.setScene(scene);
        window.showAndWait();

        return num;
    }
}