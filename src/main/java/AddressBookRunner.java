import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AddressBookRunner {
  public static final String ADD_CONTACT = "Add a contact";
  public static final String LOOKUP_CONTACT = "Look up a contact";
  public static final String LIST_ALL_CONTACTS = "List All Contacts";
  public static final String QUIT_TEXT = "Quit";

  public static void main(String[] args) {
    List<String> options = new ArrayList<String>();
    options.add(ADD_CONTACT);
    options.add(LOOKUP_CONTACT);
    options.add(LIST_ALL_CONTACTS);
    options.add(QUIT_TEXT);

    Scanner inputScanner = new Scanner(System.in);
    String menuChoice = "";

    Map<String, String> addressBook = new HashMap<>();

    while(menuChoice != QUIT_TEXT) {
      int menuIndex = 1;
      for(String option : options) {
        System.out.println(menuIndex + ". " + option);
        menuIndex++;
      }

      System.out.println("\nWhat would you like to do? Select a number.");
      try {
        int selectedOption = inputScanner.nextInt();
        menuChoice = options.get(selectedOption - 1);

        if (menuChoice == ADD_CONTACT) {
          // your code here
          inputScanner.nextLine();
          String name = "";
          String email = "";
          while(name.isBlank()) {
            System.out.println("Enter the contact's name:");
            name = inputScanner.nextLine();
          }
          while(email.isBlank()) {
            System.out.println("Enter the contact's email:");
            email = inputScanner.nextLine();
          }
          addressBook.put(name, email);
        } else if (menuChoice == LOOKUP_CONTACT) {
          // your code here
          inputScanner.nextLine();
          String name = "";
          while(name.isBlank()) {
            System.out.println("Enter the contact's name:");
            name = inputScanner.nextLine();
          }
          if(addressBook.get(name) != null) {
            System.out.println(addressBook.get(name));
          } else {
            System.out.println("Sorry entry not found.");
          }
        } else if(menuChoice == LIST_ALL_CONTACTS) {
          Set<String> names = addressBook.keySet();
          int counter = 0;
          StringBuilder builder = new StringBuilder();
          for(String name : names) {
            builder.append("\"").append(name).append("\"").append(" ").append("<")
                .append(addressBook.get(name)).append(">");
            counter++;
            if(counter < names.size()) {
              builder.append(", ");
            }
          }
          System.out.println("Total number of contacts: " + counter);
          System.out.println(builder);
        }
      } catch(IndexOutOfBoundsException err) {
        System.out.println("Please make a valid selection!");
      }
    }
  }
}
