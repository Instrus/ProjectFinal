import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.LinkedList;

//class Drinks implementation by: Brandon Tsuchiya

public class Drinks
{
    Stage window = new Stage();

    //Menu items
    static ItemAndPrice pepsi = new ItemAndPrice("Pepsi", 3.00);
    static ItemAndPrice coke = new ItemAndPrice("Coke", 3.00);
    static ItemAndPrice sprite = new ItemAndPrice("Sprite", 3.00);
    static ItemAndPrice orangeJuice = new ItemAndPrice("Orange Juice", 3.00);

    public void seeShots(Table reference)
    {
        window.setOnCloseRequest(Event::consume);

        OrderScreen orderScreenOb = new OrderScreen();
        LinkedList<ItemAndPrice> order = new LinkedList<>();

        Button pepsiButton = new Button("Pepsi $3.00");
        pepsiButton.setMinSize(200, 25);
        pepsiButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + pepsi.item + "?");
            if (add == true)
            {
                order.add(pepsi);
                reference.notes.add("");
            }
        });

        Button spriteButton = new Button("Sprite $3.00");
        spriteButton.setMinSize(200, 25);
        spriteButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + sprite.item + "?");
            if (add == true)
            {
                order.add(sprite);
                reference.notes.add("");
            }
        } );

        Button cokeButton = new Button("Coke $3.00");
        cokeButton.setMinSize(200, 25);
        cokeButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + coke.item + "?");
            if (add == true)
            {
                order.add(coke);
                reference.notes.add("");
            }
        });

        Button OJButton = new Button("Orange Juice $3.00");
        OJButton.setMinSize(200, 25);
        OJButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + orangeJuice.item + "?");
            if (add == true)
            {
                order.add(orangeJuice);
                reference.notes.add("");
            }
        } ); //----------------------------------------------------------------------


        //SUBMIT - Finalizes order from selection and adds to tables order
        Button submit = new Button("Submit");
        submit.setOnAction(e ->
        {
            for(int i = 0; i < order.size(); i++){
                reference.orders.add(order.get(i));
            }
            window.close();
            orderScreenOb.seeOrderScreen(reference);
        } );

        //Layout
        Label entreeLabel = new Label("Entrees");
        Line line = new Line(0, 0, 350, 0);
        line.setStrokeWidth(2.0);
        VBox title = new VBox(10, entreeLabel, line);
        title.setAlignment(Pos.CENTER);

        BorderPane screen = new BorderPane();
        VBox menuItems = new VBox(10, pepsiButton, spriteButton, OJButton, cokeButton);
        menuItems.setPadding(new Insets(20,10,10,10));
        menuItems.setAlignment(Pos.TOP_CENTER);
        //Set / Padding
        screen.setTop(title);
        screen.setCenter(menuItems);
        screen.setBottom(submit);
        screen.setAlignment(submit, Pos.CENTER);
        screen.setPadding(new Insets(20,0,20,0));
        //Scene
        Scene scene = new Scene(screen, 350, 550);
        window.setScene(scene);
        window.show();
    }

}
