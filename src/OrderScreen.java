import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.LinkedList;


//class OrdersScreen implementation by: Brandon Tsuchiya

//OrderScreen - main order screen
public class OrderScreen
{
    Stage window = new Stage();

    //Object(s) used in this class:
    Entrees entreeObject = new Entrees();
    Sides sidesObject = new Sides();
    Drinks drinksOb = new Drinks();

    LinkedList<ButtonNode> orderButtons = new LinkedList<>();

    static VBox orderView = new VBox(5);
    ToggleGroup tGroup = new ToggleGroup();
    static Button totalButton = new Button();
    static double total = 0.00;

    //createButtons() calls newButton()
    public void createButtons(Table reference)
    {
        int size = reference.orders.size();
        for(int i = 0; i < size; i++)
        {
            String menuItemName = reference.orders.get(i).item;
            newButton(menuItemName, i, reference);
        }
    }

    //newButtons() - creates buttons for orderView
    public void newButton(String menuItem, int index, Table reference)
    {
        ButtonNode ob = new ButtonNode(); //ButtonNode object
        ToggleButton newButton = new ToggleButton(String.valueOf(menuItem)); //create button
        newButton.setMinSize(200, 30); //set button size
        newButton.setOnAction(event -> handleEvent(newButton, reference) ); //button action
        tGroup.getToggles().add(newButton); //add to toggle group
        ob.Node(newButton, index); //create new Node
        orderButtons.add(ob); //add to orderButtons linked List.
        orderView.getChildren().add(newButton); //adds to orderView
    }

    //updateOrderEntrees() updates order from the Entree class
    public void updateOrderEntrees(Table reference)
    {
        entreeObject.seeEntrees(reference);
    }


    //updateOrderSides() updates order from the Sides class
    public void updateOrderSides(Table reference)
    {
        sidesObject.seeSides(reference);
    }

    //updateOrderShots() updates order from the shots class
    public void updateOrderShots(Table reference)
    {
        drinksOb.seeShots(reference);
    }

    //clearOrder() - clears orderView
    public void clearOrderView()
    {
        orderView.getChildren().clear();
    }

    //updatePriceView() - updates price view
    public void updatePriceView(Table reference)
    {
        for(int i = 0; i < reference.orders.size(); i++)
            total += reference.orders.get(i).price;

        String format = String.valueOf(total); //format string to hold only 2 decimal places
        format = format.format("%.2f", total);
        String totalString = ("Total: " + format );
        totalButton.setText(totalString);
        totalButton.setMinSize(130,50);
    }

    //clearTotal() - clears total
    public void clearTotal()
    {
        total = 0.00;
    }




    //seeOrderScreen() - displays order screen
    public void seeOrderScreen(Table reference)
    {
        Server serverOb = new Server();
        createButtons(reference);
        updatePriceView(reference);
        clearTotal();

        //MENU TABS::

        //entrees button
        Button entrees = new Button("Entrees");
        entrees.setMinSize(120,50);
        entrees.setOnAction(e ->
        {
            window.close();
            clearOrderView();
            updateOrderEntrees(reference);
        });

        //sides button
        Button sides = new Button("Sides"); //sides
        sides.setMinSize(120,50);
        sides.setOnAction(e ->
        {
            window.close();
            clearOrderView();
            updateOrderSides(reference);
        });

        //shots button
        Button shots = new Button("Drinks");//shots
        shots.setMinSize(120,50);
        shots.setOnAction(e ->
        {
            window.close();
            clearOrderView();
            updateOrderShots(reference);
        });

        //OTHER BUTTONS::

        //back button - returns to home().
        Button home = new Button("Home");
        home.setMinSize(130,50);
        home.setOnAction(event ->
        {
            clearOrderView();
            window.close();
            serverOb.home();
        });

        //editOrder button - allows removal of items.
        Button editOrder = new Button("Remove Items");
        editOrder.setMinSize(130,50);
        editOrder.setOnAction(event ->
        {
            clearOrderView();
            EditOrder editOrderOb = new EditOrder();
            editOrderOb.editOrder(reference);
            window.close();
        });

        //closeCheck button - clears everything from table.
        Button closeCheck = new Button("Close Check");
        closeCheck.setMinSize(130,50);
        closeCheck.setOnAction(event ->
        {
            boolean answer = ConfirmBox.display("Close Check?");
            if(answer == true)
            {
                for(int i = 0; i < Server.linkedTables.size(); i++)
                {
                    if (reference == Server.linkedTables.get(i))
                    {
                        reference.employeeID = -1; //default employee ID (-1 == null)
                        reference.orders.clear(); //clear orders
                        reference.numOfGuests = 0; //set guests back to 0
                        clearOrderView(); //clear orderView
                        serverOb.home(); //return home
                        window.close();
                        return;
                    }
                }
            }
        });

        //sendOrder button - sends order to kitchen staff (not really)
        Button sendOrder = new Button("Send Order");
        sendOrder.setMinSize(130,50);
        sendOrder.setOnAction(event -> Notification.display("Order sent to kitchen"));

        //LAYOUT::

        //Labels
        Label menuTabLabel = new Label("Menu Navigation"); //menuTabLabel
        Label orderViewLabel = new Label("Orders:");
        //Title
        Label screenLabel = new Label("Order Screen");
        Line line = new Line(0, 0, 1000, 0); line.setStrokeWidth(2.0);
        VBox title = new VBox(10, screenLabel, line);
        //Main screen
        BorderPane screen = new BorderPane(); //main screen
        BorderPane tabHolder = new BorderPane(); //holds menu tabs
        //ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(orderView);
        //V/HBoxes
        VBox totalAndBack = new VBox(10, totalButton, home);
        HBox options = new HBox(120, totalAndBack, editOrder, closeCheck, sendOrder);
        VBox orderViewHolder = new VBox(10, orderViewLabel, scrollPane);
        HBox menuTabs = new HBox(5, entrees, sides, shots); //row 1
        VBox menuTabsAndLabel = new VBox(10, menuTabLabel, menuTabs);
        //sets
        screen.setTop(title);
        screen.setLeft(orderViewHolder);
        screen.setRight(tabHolder);
        screen.setBottom(options);
        tabHolder.setCenter(menuTabsAndLabel);
        //Alignment/Padding
        title.setAlignment(Pos.CENTER);
        menuTabsAndLabel.setAlignment(Pos.TOP_CENTER);
        orderViewHolder.setPadding(new Insets(70,0,100,70));
        options.setPadding(new Insets(0,70,10,70));
        screen.setPadding(new Insets(30,0,30,0));
        tabHolder.setPadding(new Insets(100,70,100,70));
        //scene
        Scene scene = new Scene(screen, 1000, 750);
        window.setScene(scene);
        window.show();
    }

    //handleEvent() - handles button click
    public void handleEvent(ToggleButton button, Table ref )
    {
        NoteMaker ob = new NoteMaker();
        for(int i = 0; i < orderButtons.size(); i++)
        {
            if( button.equals(orderButtons.get(i).tButton) )
            {
                ob.makeNote(ref, i);
            }
        }
    }

}

