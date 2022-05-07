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

//Class Entrees implementation by: Brandon Tsuchiya

public class Entrees
{
    Stage window = new Stage();


    //Menu items
    static ItemAndPrice burger = new ItemAndPrice("Cheeseburger", 9.99);
    static ItemAndPrice spaghetti = new ItemAndPrice("Spaghetti and Meatballs", 12.99);
    static ItemAndPrice shepardsPie = new ItemAndPrice("Shepard's Pie", 11.99);
    static ItemAndPrice salad = new ItemAndPrice("Grilled Chicken Salad", 12.99);

    public void seeEntrees(Table reference)
    {
        window.setOnCloseRequest(Event::consume);

        OrderScreen orderScreenOb = new OrderScreen();
        LinkedList<ItemAndPrice> order = new LinkedList<>();

        //Burger button
        Button burgerButton = new Button("Cheeseburger $9.99");
        burgerButton.setMinSize(200, 25);
        burgerButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + burger.item + "?");
            if (add == true)
            {
                order.add(burger);
                reference.notes.add("");
            }
        });

        //Spaghetti button
        Button spaghettiButton = new Button("Spaghetti and Meatballs $12.99");
        spaghettiButton.setMinSize(200, 25);
        spaghettiButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + spaghetti.item + "?");
            if (add == true)
            {
                order.add(spaghetti);
                reference.notes.add("");
            }
        } );

        //Shepard's Pie button
        Button shepardsPieButton = new Button("Shepard's Pie $11.99");
        shepardsPieButton.setMinSize(200, 25);
        shepardsPieButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + shepardsPie.item + "?");
            if (add == true)
            {
                order.add(shepardsPie);
                reference.notes.add("");
            }
        });

        //Salad Button
        Button saladButton = new Button("Grilled Chicken Salad 12.99");
        saladButton.setMinSize(200, 25);
        saladButton.setOnAction(e ->
        {
            Boolean add = ConfirmBox.display("Add " + salad.item + "?");
            if (add == true)
            {
                order.add(salad);
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
        VBox menuItems = new VBox(10, burgerButton, spaghettiButton, saladButton, shepardsPieButton);
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
