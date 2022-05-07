import java.util.LinkedList;

//class Table implementation by: Brandon Tsuchiya

public class Table {

    int employeeID = -1; //Default (Nobody took table yet)
    int numOfGuests = 0;

    LinkedList<ItemAndPrice> orders = new LinkedList<>(); //Table holds a list of menu items ordered
    LinkedList<String> notes = new LinkedList<>(); //Used in accordance with orders. Optional notes for each item.

    //Table creation
    public Table()
    { }

}
