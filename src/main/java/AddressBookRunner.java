import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookRunner {
  public static final String ADD_CONTACT = "Add a contact";
  public static final String LOOKUP_CONTACT = "Look up a contact";
  public static final String QUIT_TEXT = "Quit";

  public static void main(String[] args) {
    List<String> options = new ArrayList<String>();
    options.add(ADD_CONTACT);
    options.add(LOOKUP_CONTACT);
    options.add(QUIT_TEXT);

    Scanner inputScanner = new Scanner(System.in);
    String menuChoice = "";

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
        } else if (menuChoice == LOOKUP_CONTACT) {
          // your code here
        }
      } catch(IndexOutOfBoundsException err) {
        System.out.println("Please make a valid selection!");
      }
    }
  }
}
