import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheGym {
    Path fromCustomersFilePath = Paths.get("src/customers.txt");
    Path toPTFilePath = Paths.get("src/ptinfo.txt");
    List<Customer> customerList = new ArrayList<>();
    Customer currentCustomer;

    public TheGym() {

    }

    // Läser från filen (customers.txt) och skapar upp samt returnerar en Customer-lista.
    public List<Customer> addToCustomerList(Path fromFile) throws IOException {
        String firstLine;
        String secondLine = "";
        List<Customer> cList = new ArrayList<>();
        String[] customerInfoFirstLine;
        try (Scanner fileScanner = new Scanner(fromFile)) {
            while (fileScanner.hasNext()) {
                firstLine = fileScanner.nextLine().trim();
                customerInfoFirstLine = firstLine.split(",");
                if (fileScanner.hasNext()) {
                    secondLine = fileScanner.nextLine().trim();
                }
                Customer customer = new Customer(customerInfoFirstLine[0].trim(), customerInfoFirstLine[1].trim(), secondLine);
                cList.add(customer);
            }
        }
        return cList;
    }

    //itererar över listan och returnerar true om namn eller personnummer matchar Customer-objekt i listan.
    public boolean findCustomerInList(String userInput, List<Customer> cList) {
        boolean isCustomer = false;
        for (Customer c : cList) {
            if (c.getName().equalsIgnoreCase(userInput)) isCustomer = true;
            else if (c.getPersonalNumber().equals(userInput)) isCustomer = true;
        }
        return isCustomer;
    }

    //returnerar aktuell Customer från listan.
    public Customer getCustomerFromList(String userInput, List<Customer> cList) {
        for (Customer c : cList) {
            if (c.getName().equalsIgnoreCase(userInput) ||
                    c.getPersonalNumber().equals(userInput)) return c;
        }
        return null;
    }

    //returnerar true om aktuell Customer har aktivt medlemsskap.
    public boolean currentMemberCheck(Customer customer) {
        LocalDate membershipDate = LocalDate.parse(customer.getDateOfMembership());
        if (membershipDate.isAfter(LocalDate.now().minusYears(1)) ||
                membershipDate.isEqual(LocalDate.now().minusYears(1))) {
            return true;
        } else return false;
    }

    //skriver aktuell Customer, träningsdatum (dagens datum) och starttid till PT-filen.
    public void printToPTInfo(Customer customer, Path outFilePath) throws IOException {
        if (!Files.exists(outFilePath)) {
            Files.createFile(outFilePath);
        }
        try (PrintWriter printToFile = new PrintWriter(Files.newBufferedWriter(
                outFilePath, StandardOpenOption.APPEND))) {

            printToFile.write(customer.getPersonalNumber() + " " + customer.getName()
                    + ", tränade " + LocalDate.now() + " klockan " + LocalTime.now().getHour()
                    + ":" + LocalTime.now().getMinute() + "\n");
        }
    }

    //huvudprogrammet innehållande det användaren ser.
    public void mainProgram() {
        String userInputString;
        boolean programLoop = true;

        System.out.println("Välkommen till Best Gym Ever!");

        try (Scanner userScan = new Scanner(System.in)) {
            customerList = addToCustomerList(fromCustomersFilePath);
            while (programLoop) {
                System.out.println("Skriv ett fullständigt namn eller personnummer:");
                userInputString = userScan.nextLine().trim();

                if (!findCustomerInList(userInputString, customerList)) {
                    System.out.println("Personen har aldrig varit medlem på Best Gym Ever.");
                } else {
                    currentCustomer = getCustomerFromList(userInputString, customerList);

                    if (!currentMemberCheck(currentCustomer)) {
                        System.out.println("Personen har inte ett aktivt medlemskap. \n" +
                                currentCustomer.getName() + " blev medlem "
                                + currentCustomer.getDateOfMembership());
                    } else {
                        System.out.println(currentCustomer.getName() + " med personnummer " +
                                currentCustomer.getPersonalNumber() + " är aktiv medlem på Best Gym Ever. Välkommen!");
                        printToPTInfo(currentCustomer, toPTFilePath);
                    }
                }
                System.out.println("Vill du fortsätta? [Ja/Nej]");
                if (!userScan.nextLine().trim().equalsIgnoreCase("ja")) {
                    programLoop = false;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops. File not found.");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error while trying to read from, or write to, file.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    //bryter den statiska kontexten
    public static void main(String[] args) {
        TheGym theGym = new TheGym();
        theGym.mainProgram();
    }

}
//Presentationsdata:
//7911061234 Fritjoff Flacon
//7608021234 Diamanda Djedi