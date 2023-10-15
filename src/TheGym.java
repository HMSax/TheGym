import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheGym {
    Path fromCustomersFilePath = Paths.get("src/customers.txt");
    Path toPTFilePath = Paths.get("src/ptinfo.txt");
    List<Customer> customerList = new ArrayList<>();

    public TheGym() {

    }

    public List<Customer> addToCustomerList(Scanner fileScanner) {
        String firstLine;
        String secondLine = "";
        List<Customer> cList = new ArrayList<>();
        String[] customerInfoFirstLine = new String[2];
        while (fileScanner.hasNext()) {
            firstLine = fileScanner.nextLine();
            customerInfoFirstLine = firstLine.split(",");
            if (fileScanner.hasNext()) {
                secondLine = fileScanner.nextLine();
            }
            Customer customer = new Customer(customerInfoFirstLine[0], customerInfoFirstLine[1], secondLine);
            cList.add(customer);
        }
        return cList;

    }

    public void mainProgram() {
        try (Scanner fileScanner = new Scanner(fromCustomersFilePath);
             PrintWriter printToFile = new PrintWriter(Files.newBufferedWriter(toPTFilePath))) {
            customerList = addToCustomerList(fileScanner);
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

        System.out.println("Welcome to Best Gym Ever!");
        TheGym theGym = new TheGym();
        theGym.mainProgram();

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}