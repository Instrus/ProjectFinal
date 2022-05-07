import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.LinkedList;

//class Server implementation by: Eric Tsuchiya

//Server class - This is the main screen servers will see.
public class Server
{
    Stage window = new Stage();

    static LinkedList<Table> linkedTables = new LinkedList<>(); //A LinkedList of tables

    //Tables - each table will hold guest size, ID of server, and orders.
    static Table tableOne = new Table();
    static Table tableTwo = new Table();
    static Table tableThree = new Table();
    static Table tableFour = new Table();
    static Table tableFive = new Table();
    static Table tableSix = new Table();
    static Table tableSeven = new Table();
    static Table tableEight = new Table();
    static Table tableNine = new Table();

    //Object used in this class:
    OrderScreen orderScreenObject = new OrderScreen();
    EmployeePage epOb = new EmployeePage();

    public void home()
    {
        //Tables (Buttons)

        Button table1 = new Button("Table 1"); //Table 1
        table1.setOnAction(e -> handleEvent(tableOne));

        Button table2 = new Button("Table 2"); //Table 2
        table2.setOnAction(event -> handleEvent(tableTwo));

        Button table3 = new Button("Table 3"); //Table 3
        table3.setOnAction(event -> handleEvent(tableThree));

        Button table4 = new Button("Table 4"); //Table 4
        table4.setOnAction(event -> handleEvent(tableFour));

        Button table5 = new Button("Table 5"); //Table 5
        table5.setOnAction(event -> handleEvent(tableFive));

        Button table6 = new Button("Table 6"); //Table 6
        table6.setOnAction(event -> handleEvent(tableSix));

        Button table7 = new Button("Table 7"); //Table 7
        table7.setOnAction(event -> handleEvent(tableSeven));

        Button table8 = new Button("Table 8"); //Table 8
        table8.setOnAction(event -> handleEvent(tableEight));

        Button table9 = new Button("Table 9"); //Table 9
        table9.setOnAction(event -> handleEvent(tableNine));

        //BUTTONS::

        //clockIn button
        Button clockIn= new Button("Clock in");
        clockIn.setMinSize(140,70);
        clockIn.setOnAction(event -> handleClockIn());

        //clockOut button
        Button clockOut = new Button("Clock out");
        clockOut.setMinSize(140, 70);
        clockOut.setOnAction(event -> handleClockOut());

        //employeeButton button
        Button employeesButton = new Button("Employees");
        employeesButton.setMinSize(140, 70);
        employeesButton.setOnAction(event ->
        {
            EmployeePage pg = new EmployeePage();
            pg.empScreen();
        });

        // LAYOUT::

        // title
        Label screenLabel = new Label("Home Screen");
        Line line = new Line(0, 0, 1000, 0); line.setStrokeWidth(2.0);
        VBox title = new VBox(10, screenLabel, line);
        // V/HBoxes
        VBox signBox = new VBox(5, clockIn, clockOut, employeesButton);
        HBox tableRow1 = new HBox(80, table1, table2, table3);
        HBox tableRow2 = new HBox(80, table4, table5, table6);
        HBox tableRow3 = new HBox(80, table7, table8, table9);
        VBox tablesCombine = new VBox(80, tableRow1, tableRow2, tableRow3);
        // Main screen
        BorderPane screen = new BorderPane();
        BorderPane tables = new BorderPane(tablesCombine);
        // sets
        screen.setTop(title);
        screen.setRight(tables);
        screen.setLeft(signBox);
        // Alignment/Padding
        title.setAlignment(Pos.CENTER);
        signBox.setPadding(new Insets(150,0,0,0));
        tables.setPadding(new Insets(150,100,100,100));
        screen.setPadding(new Insets(30,0,30,0));
        // scene
        Scene serverScreen = new Scene(screen, 1000, 600);
        window.setScene(serverScreen);
        window.show();
    }


    //handles Table buttons
    public void handleEvent(Table reference)
    {

        while(reference.employeeID < 0){
            epOb.empScreen(); //shows employees signed in
            reference.employeeID = epOb.getID(); //gets the new ID (from button)

            if (reference.employeeID == -2) //Used for cancel
            {
                reference.employeeID = -1; //reset to empty (default).
                return;// kick us out of handle
            }
        }

        while(reference.numOfGuests < 1)
        {
            reference.numOfGuests = GetNumber.display("Enter number of guests");
            if(reference.numOfGuests == -2) //cancel option
                return;
            if(reference.numOfGuests < 1){
                Notification.display("Please enter a number greater than 0");
            }
        }

        orderScreenObject.seeOrderScreen(reference);
        window.close();
    }

    //handles Sign in button
    public void handleClockIn()
    {
        int ID = GetNumber.display("Enter employee ID.");

        if (ID < 1)
        {
            Notification.display("Sign in unsuccessful.");
            return;
        }

        for(int i = 0; i < Home.IDs.size(); i++) //For loop prevents duplicate ID
        {
            if(ID == Home.IDs.get(i)) //checks for every single clock in before adding.
            {
                Notification.display("Already clocked in.");
                return;
            }
        }
        Notification.display("ID: " + ID + " clocked in.");
        Home.IDs.add(ID);
    }

    public void handleClockOut()
    {
        int ID = GetNumber.display("Enter employee ID.");

        if (ID < 1)
        {
            Notification.display("Sign out unsuccessful.");
            return;
        }

        for(int i = 0; i < Home.IDs.size(); i++)
        {
            if(ID == Home.IDs.get(i))
            {
                Home.IDs.remove(i);
                Notification.display("ID: " + ID + " clocked out.");
                return;
            }
        }
        Notification.display("Sign out unsuccessful.");
    }

}
