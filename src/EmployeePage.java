import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.LinkedList;

//class EmployeePage implementation by: Eric Tsuchiya

public class EmployeePage
{
    static LinkedList<ButtonNode> employees = new LinkedList<>(); //List of ButtonNodes
    static VBox employeeView = new VBox(5); //employeeView
    static ToggleGroup tGroup = new ToggleGroup();
    int ID = -1;

    //createButtons() - calls newButton
    public void createButtons()
    {
        int size = Home.IDs.size();

        for(int i = 0; i < size; i++) //creates however many buttons depending how many people clocked in.
        {
            int ID = Home.IDs.get(i); //get ID (for button creation)
            newButtons(ID, i);
        }
    }

    //newButtons() - creates buttons for employeeView
    public void newButtons(int ID, int index)
    {
        ButtonNode ob = new ButtonNode(); //object
        ToggleButton newButton = new ToggleButton(String.valueOf(ID)); //create new button
        newButton.setMinSize(130, 30); //size of button
        newButton.setOnAction(event -> handleEvent(newButton) ); //button action
        tGroup.getToggles().add(newButton); //add to ToggleGroup
        ob.Node(newButton, index, ID); //create new Node
        employees.add(ob); //add node to employees
        employeeView.getChildren().add(newButton); //add to employeeView
    }

    //clearEmployeeView - simply clears the employeeView
    public void clearEmployeeView()
    {
        employeeView.getChildren().clear();
    }

    //setID() - set ID
    public void setID(int ID)
    {
        this.ID = ID;
    }

    //getID() - return ID
    public int getID()
    {
        return ID;
    }

    //empScreen() - main employee screen
    public void empScreen()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //blocks user from leaving window
        window.setOnCloseRequest(Event::consume); //prevents from closing out without hitting submit or close.

        createButtons();

        //BUTTONS::

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(event ->
        {
            employees.clear();
            clearEmployeeView();
            window.close();
        });

        //Cancel button
        Button cancel = new Button("Cancel");
        cancel.setOnAction(event ->
        {
            setID(-2);
            employees.clear();
            clearEmployeeView();
            window.close();
        });

        //LAYOUT::

        //Title
        Label empLabel = new Label("Select your ID");
        Line line = new Line(0, 0, 350, 0); line.setStrokeWidth(2.0);
        //V/HBoxes
        HBox back_cancel = new HBox(10, submit, cancel);
        VBox title = new VBox(10, empLabel, line);
        //ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(employeeView);
        //Main screen
        BorderPane screen = new BorderPane();
        //Sets
        screen.setTop(title);
        screen.setCenter(scrollPane);
        screen.setBottom(back_cancel);
        //Alignments/Padding
        employeeView.setAlignment(Pos.TOP_CENTER);
        back_cancel.setAlignment(Pos.CENTER);
        title.setAlignment(Pos.CENTER);
        screen.setPadding(new Insets(20,0,20,0));
        scrollPane.setPadding(new Insets(35, 100,35,100));
        //scene
        Scene scene = new Scene(screen, 350, 550);
        window.setScene(scene);
        window.showAndWait();
    }

    //handleEvent() - Handles buttons
    public int handleEvent(ToggleButton button)
    {
        for(int i = 0; i < employees.size(); i++)
        {
            if(button.equals(employees.get(i).tButton))
            {
                System.out.println(employees.get(i).index  + ": " + employees.get(i).ID);
                setID(employees.get(i).ID);
            }
        }
        return ID;
    }

}
