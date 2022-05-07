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

//class Sides implementation by: Brandon Tsuchiya

public class Sides
{
    Stage window = new Stage();

    //Menu items
    static ItemAndPrice chili = new ItemAndPrice("Chili", 4.99);
    static ItemAndPrice fries = new ItemAndPrice("French Fries", 4.99);
    static ItemAndPrice macaroni = new ItemAndPrice("Macaroni and Cheese", 4.99);
    static ItemAndPrice greenBeans = new ItemAndPrice("Green beans", 3.99);

    public void seeSides(Table reference)
    {
        window.setOnCloseRequest(Event::consume);

        OrderScreen orderScreenOb = new OrderScreen();
        LinkedList<ItemAndPrice> order = new LinkedList<>();

        //Fries button
        Button friesButton = new Button("French Fries $4.99");
        friesButton.setMinSize(200, 25);
        friesButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + fries.item + "?");
            if (add == true)
            {
                order.add(fries);
                reference.notes.add("");
            }
        });

        //Macaroni button
        Button macaroniButton = new Button("Macaroni and Cheese $4.99");
        macaroniButton.setMinSize(200, 25);
        macaroniButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + macaroni.item + "?");
            if (add == true)
            {
                order.add(macaroni);
                reference.notes.add("");
            }
        } );

        //Chili button
        Button chiliButton = new Button("Chili $4.99");
        chiliButton.setMinSize(200, 25);
        chiliButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + chili.item + "?");
            if (add == true)
            {
                order.add(chili);
                reference.notes.add("");
            }
        });

        //Green beans button
        Button greenBeansButton = new Button("Green Beans $3.99");
        greenBeansButton.setMinSize(200, 25);
        greenBeansButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + greenBeans.item + "?");
            if (add == true)
            {
                order.add(greenBeans);
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
        VBox menuItems = new VBox(10, friesButton, macaroniButton, greenBeansButton, chiliButton);
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
