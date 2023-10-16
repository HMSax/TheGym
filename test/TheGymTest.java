import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TheGymTest {
    Path fromCustomersTestFilePath = Paths.get("test/customerTest.txt");
    Path toPTinfoTestFilePath = Paths.get("test/ptinfoTest.txt");
    List<Customer> testList = new ArrayList<>();
    TheGym testGym = new TheGym();
    Customer c1 = new Customer("1234567878", "Test Testsson", LocalDate.now().minusMonths(6).toString());
    Customer c2 = new Customer("2424242424", "Prova Provasdotter", LocalDate.now().minusMonths(13).toString());
    Customer c3 = new Customer("4324321111", "Enannan Ytterligare", LocalDate.now().minusMonths(12).toString());

    @Test
    public void getCustomerFromList(){
        testList.add(c1);
        testList.add(c2);
        testList.add(c3);
        assertTrue(testGym.getCustomerFromList("Test Testsson", testList) == c1);
        assertTrue(testGym.getCustomerFromList("2424242424", testList) == c2);
        assertTrue(testGym.getCustomerFromList("4324321111", testList) != c1);

    }
    @Test
    public void currentMemberCheckTest() {
        assertTrue(testGym.currentMemberCheck(c1));
        assertFalse(testGym.currentMemberCheck(c2));
        assertTrue(testGym.currentMemberCheck(c3));
    }

    @Test
    public void findCustomerInListTest() {
        testList.add(c1);
        testList.add(c2);
        assertTrue(testGym.findCustomerInList("1234567878", testList));
        assertTrue(testGym.findCustomerInList("Test Testsson", testList));
        assertTrue(testGym.findCustomerInList("Prova Provasdotter", testList));
        assertTrue(testGym.findCustomerInList("2424242424", testList));
        assertFalse(testGym.findCustomerInList("1111111111", testList));
        assertFalse(testGym.findCustomerInList("Errol Error", testList));

    }

    @Test
    public void printToPTInfoTest() {
        testList.add(c1);
        testList.add(c2);
        try {
            Files.deleteIfExists(toPTinfoTestFilePath);
            Files.createFile(toPTinfoTestFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        testGym.printToPTInfo(testList.get(0), toPTinfoTestFilePath);
        testGym.printToPTInfo(testList.get(1), toPTinfoTestFilePath);

        try (Scanner fileScanner = new Scanner(toPTinfoTestFilePath)) {
            String testScan;
            testScan = fileScanner.nextLine();
            assertTrue(testScan.equals("1234567878 Test Testsson, tränade " + LocalDate.now()));
            testScan = fileScanner.nextLine();
            assertTrue(testScan.equals("2424242424 Prova Provasdotter, tränade " + LocalDate.now()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void addToCustomerListTest() {
        try (Scanner fileScanner = new Scanner(fromCustomersTestFilePath)) {
            testList = testGym.addToCustomerList(fileScanner);
            assertTrue(testList.size() == 3);
            assertFalse(testList.size() == 2);
            assertTrue(testList.get(0).getName().equals("Alhambra Aromes"));
            assertTrue(testList.get(1).getPersonalNumber().equals("8204021234"));
            assertTrue(testList.get(2).getDateOfMembership().equals("2018-03-12"));

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
}