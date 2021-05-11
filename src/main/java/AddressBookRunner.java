import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
  public static final String LIST_ALL_CONTACTS = "List all contacts";
  public static final String CLEAR_ALL_CONTACTS = "Clear all contacts";
  public static final String SAVE_CONTACTS = "Save contacts";
  public static final String LOAD_ADDRESS_BOOK = "Load address book";
  public static final String QUIT_TEXT = "Quit";

  public static void main(String[] args) {
    List<String> options = new ArrayList<>();
    options.add(ADD_CONTACT);
    options.add(LOOKUP_CONTACT);
    options.add(LIST_ALL_CONTACTS);
    options.add(CLEAR_ALL_CONTACTS);
    options.add(SAVE_CONTACTS);
    options.add(LOAD_ADDRESS_BOOK);
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
      System.out.println("Total number of contacts: " + addressBook.size());

      System.out.println("\nWhat would you like to do? Select a number.");
      try {
        int selectedOption = inputScanner.nextInt();
        menuChoice = options.get(selectedOption - 1);

        if (menuChoice == ADD_CONTACT) {
          // your code here
          inputScanner.nextLine();
          String name = "";
          String email = "";
          String overwrite;
          while(name.isBlank()) {
            System.out.println("Enter the contact's name:");
            name = inputScanner.nextLine();
            if(addressBook.containsKey(name)) {
              System.out.println("Contact already exists! Confirm to overwrite y/n:");
              overwrite = inputScanner.nextLine();
              if(overwrite.equals("y")) {
                System.out.println("Inside if");
                while(email.isBlank()) {
                  System.out.println("Enter the contact's email:");
                  email = inputScanner.nextLine();
                }
              }
            } else {
              while(email.isBlank()) {
                System.out.println("Enter the contact's email:");
                email = inputScanner.nextLine();
              }
            }
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
        } else if(menuChoice == CLEAR_ALL_CONTACTS) {
          inputScanner.nextLine();
          String overwrite;
          System.out.println("Confirm to clear all existing contacts y/n:");
          overwrite = inputScanner.nextLine();
          if(overwrite.equals("y")) {
            addressBook.clear();
          }
        } else if(menuChoice == SAVE_CONTACTS) {
          inputScanner.nextLine();
          String fileName = "";
          System.out.println("Enter a name for your file:");
          while(fileName.length() <= 2) {
            fileName = inputScanner.nextLine();
            System.out.println("File name must be longer than 2 characters. Please try again:");
          }
          File newFile = new File("./" + fileName + ".csv");
          FileWriter writer = new FileWriter(newFile);
          Set<String> names = addressBook.keySet();
          int counter = 0;
          StringBuilder builder = new StringBuilder();
          for(String name : names) {
            counter++;
            writer.write(name + ", " + addressBook.get(name));
            if(counter < addressBook.size()) {
              writer.write("\n");
            }
          }
          writer.close();
        } else if(menuChoice == LOAD_ADDRESS_BOOK) {
          inputScanner.nextLine();
          String fileName;
          System.out.println("Enter the name of the address book file:");
          fileName = inputScanner.nextLine();
          File existingFile = new File("./" + fileName + ".csv");
          if(existingFile.exists()) {
            addressBook.clear();
            Scanner fileScanner = new Scanner(existingFile);
            fileScanner.useDelimiter("[,\n]");
            while(fileScanner.hasNextLine()) {
              String name = fileScanner.next();
              String email = fileScanner.next().trim();
              addressBook.put(name, email);
            }
            fileScanner.close();
          }
        }
      } catch(IndexOutOfBoundsException | IOException err) {
        inputScanner.close();
        System.out.println("Please make a valid selection!");
      }
    }
  }
}
