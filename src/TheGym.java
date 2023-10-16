import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheGym {
    Path fromCustomersFilePath = Paths.get("src/customers.txt");
    Path toPTFilePath = Paths.get("src/ptinfo.txt");
    List<Customer> customerList = new ArrayList<>();

    public TheGym() {

    }

    public boolean currentMemberCheck(Customer customer) {
        LocalDate membershipDate = LocalDate.parse(customer.getDateOfMembership());
        if (membershipDate.isAfter(LocalDate.now().minusYears(1)) ||
                membershipDate.isEqual(LocalDate.now().minusYears(1))) {
            return true;
        } else return false;
    }

    public boolean findCustomerInList(String userInput, List<Customer> cList) {
        boolean isCustomer = false;
        for (Customer c : cList) {
            if (c.getName().equalsIgnoreCase(userInput)) isCustomer = true;
            else if (c.getPersonalNumber().equals(userInput)) isCustomer = true;
        }
        return isCustomer;
    }
    public Customer getCustomerFromList(String userInput, List<Customer> cList) {
        for (Customer c : cList) {
            if (c.getName().equalsIgnoreCase(userInput) ||
                    c.getPersonalNumber().equals(userInput)) return c;
        }
        return null;
    }

    public void printToPTInfo(Customer customer, Path outFilePath) {

        try (PrintWriter printToFile = new PrintWriter(Files.newBufferedWriter(
                outFilePath, StandardOpenOption.APPEND))) {
            if (!Files.exists(outFilePath)) {
                Files.createFile(outFilePath);
            }
            printToFile.write(customer.getPersonalNumber() + " " + customer.getName()
                    + ", tränade " + LocalDate.now() + "\n");
        } catch (IOException e) {
            System.out.println("Unable to create file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public List<Customer> addToCustomerList(Scanner fileScanner) {
        String firstLine;
        String secondLine = "";
        List<Customer> cList = new ArrayList<>();
        String[] customerInfoFirstLine;
        while (fileScanner.hasNext()) {
            firstLine = fileScanner.nextLine().trim();
            customerInfoFirstLine = firstLine.split(",");
            if (fileScanner.hasNext()) {
                secondLine = fileScanner.nextLine().trim();
            }
            Customer customer = new Customer(customerInfoFirstLine[0].trim(), customerInfoFirstLine[1].trim(), secondLine);
            cList.add(customer);
        }
        return cList;
    }

    public void mainProgram() {
        boolean isInList;
        boolean isActivemember;
        String userInputString;
        Customer currentCustomer;

        System.out.println("Welcome to Best Gym Ever!");
        try (Scanner fileScanner = new Scanner(fromCustomersFilePath);
        Scanner userInput = new Scanner(System.in)) {
            customerList = addToCustomerList(fileScanner);
            System.out.println("Skriv namn eller personnummer:");
            userInputString = userInput.nextLine().trim();
            isInList = findCustomerInList(userInputString, customerList);
            if (isInList){}

            //printToPTInfo(customerList.get(0), toPTFilePath);
            //System.out.println("Personen är aktiv medlem.");
            //System.out.println("Personens medlemskap har gått ut.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Unable to write to file.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        TheGym theGym = new TheGym();
        theGym.mainProgram();
    }

}